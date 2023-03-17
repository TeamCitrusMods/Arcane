package dev.teamcitrus.arcane.item;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.block.GlassJarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CorkItem extends Item {
    public CorkItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if(!level.isClientSide()) {
            if(player.isCrouching() && level.getBlockState(pos).getBlock() instanceof GlassJarBlock) {
                if(!level.getBlockState(pos).getValue(GlassJarBlock.CORKED)) {
                    ArcaneMod.LOGGER.info("A");
                    level.getBlockState(pos).setValue(GlassJarBlock.CORKED, true);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}