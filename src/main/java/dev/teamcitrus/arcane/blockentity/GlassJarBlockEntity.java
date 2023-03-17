package dev.teamcitrus.arcane.blockentity;

import dev.teamcitrus.arcane.block.GlassJarBlock;
import dev.teamcitrus.arcane.registry.ModBlockEntities;
import dev.teamcitrus.arcane.util.InventoryUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nonnull;

public class GlassJarBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }
    };
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation SWING_ANIMATION = RawAnimation.begin().thenLoop("animation.jar.swing");
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.jar.idle");

    public GlassJarBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {
        super(blockEntity, pos, state);
    }

    public GlassJarBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.GLASS_JAR.get(), pos, state);
    }

    public InteractionResult use(Level levelAccessor, BlockPos pos, BlockState state, Player player, InteractionHand hand, ItemStack heldItem) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (!state.getValue(GlassJarBlock.CORKED)) {
                if (!player.isCrouching()) {
                    if (!heldItem.isEmpty()) {
                        ItemStack heldCopy = heldItem.copy();
                        heldCopy.setCount(1);
                        ItemStack restStack = ItemHandlerHelper.insertItem(inventory, heldCopy, false);
                        if (restStack.isEmpty()) {
                            heldItem.shrink(1);
                            setChanged();
                            levelAccessor.sendBlockUpdated(getBlockPos(), state, levelAccessor.getBlockState(pos), 3);
                            return InteractionResult.SUCCESS;
                        }
                    }
                } else {
                    if (heldItem.isEmpty()) {
                        if (!InventoryUtil.isEmpty(inventory)) {
                            ItemStack lastStack = InventoryUtil.getLastStack(inventory);
                            if (!lastStack.isEmpty()) {
                                dropItem(lastStack, 1F);
                                lastStack.shrink(1);
                            }
                            setChanged();
                            levelAccessor.sendBlockUpdated(getBlockPos(), state, levelAccessor.getBlockState(pos), 3);
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    public void onBreak() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            dropItem(stack, 0F);
        }
        setRemoved();
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        inventory.deserializeNBT(nbt.getCompound("InventoryHandler"));
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("InventoryHandler", inventory.serializeNBT());
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryHolder.invalidate();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if(getLevel().getBlockState(getBlockPos()).getBlock() instanceof GlassJarBlock && getLevel().getBlockState(getBlockPos()).getValue(GlassJarBlock.HANGING)) {
                return state.setAndContinue(SWING_ANIMATION);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void dropItem(ItemStack stack, float offsetY) {
        ItemStack copyStack = stack.copy();
        BlockPos pos = getBlockPos();
        ItemEntity itementity = new ItemEntity(this.level, pos.getX() + 0.5, pos.getY() + offsetY, pos.getZ() + 0.5, copyStack);
        itementity.setDefaultPickUpDelay();
        this.level.addFreshEntity(itementity);
    }
}
