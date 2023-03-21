package dev.teamcitrus.concordiaarcana.item;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.block.SpellJarBlock;
import dev.teamcitrus.concordiaarcana.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GlassJarItem extends BlockItem {
    public GlassJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockState state = ModBlocks.SPELL_JAR.get().defaultBlockState();

        if (direction == Direction.DOWN) {
            if(Block.canSupportCenter(level, pos.above(), direction)) {
                level.setBlockAndUpdate(pos.below(), state.setValue(SpellJarBlock.HANGING, true));
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.place(context);
    }
}
