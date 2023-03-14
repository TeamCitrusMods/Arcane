package dev.teamcitrus.arcane.block;

import dev.teamcitrus.arcane.blockentity.GlassJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GlassJarBlock extends Block implements EntityBlock {
    public GlassJarBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GlassJarBlockEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof GlassJarBlockEntity jar) {
                return jar.use(level, pos, state, player, hand, player.getItemInHand(hand));
            }
        }

        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean p_60519_) {
        if(!level.isClientSide) {
            if(level.getBlockEntity(pos) instanceof GlassJarBlockEntity jar) {
                jar.onBreak();
            }
        }

        super.onRemove(oldState, level, pos, newState, p_60519_);
    }
}
