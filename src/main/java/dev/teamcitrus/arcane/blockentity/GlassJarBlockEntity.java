package dev.teamcitrus.arcane.blockentity;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.registry.ModBlockEntities;
import dev.teamcitrus.arcane.util.InventoryUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GlassJarBlockEntity extends BlockEntity {
    public static final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }
    };
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);

    public GlassJarBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {
        super(blockEntity, pos, state);
    }

    public GlassJarBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.GLASS_JAR.get(), pos, state);
    }

    public InteractionResult use(Level levelAccessor, BlockPos pos, BlockState state, Player player, InteractionHand hand, ItemStack heldItem, BlockHitResult hit) {
        if(hand == InteractionHand.MAIN_HAND) {
            if(!heldItem.isEmpty() && !player.isCrouching()) {
                ItemStack heldCopy = heldItem.copy();
                heldCopy.setCount(1);
                ItemStack restStack = ItemHandlerHelper.insertItem(inventory, heldCopy, false);
                if(restStack.isEmpty()) {
                    heldItem.shrink(1);
                    setChanged(levelAccessor, pos, state);
                    levelAccessor.sendBlockUpdated(getBlockPos(), state, levelAccessor.getBlockState(pos), 3);
                    return InteractionResult.SUCCESS;
                }
            } else {
                //Do other stuff here in a bit
            }
        }
        return InteractionResult.PASS;
    }

    public void onBreak(Level level, BlockPos pos) {
        dropAllItems(level, pos);
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
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return super.saveWithoutMetadata();
    }

    public void dropItem(ItemStack stack, float offsetY) {
        ItemStack copyStack = stack.copy();
        BlockPos pos = getBlockPos();
        ItemEntity itementity = new ItemEntity(this.level, pos.getX(), pos.getY() + (double) offsetY, this.worldPosition.getZ(), copyStack);
        itementity.setDefaultPickUpDelay();
        this.level.addFreshEntity(itementity);
    }

    private void dropAllItems(Level levelAccessor, BlockPos pos) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            dropItem(stack, 0F);
        }
    }
}
