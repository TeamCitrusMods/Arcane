package dev.teamcitrus.arcane.client.model;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.block.GlassJarBlock;
import dev.teamcitrus.arcane.blockentity.GlassJarBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class GlassJarModel extends DefaultedBlockGeoModel<GlassJarBlockEntity> {
    private final ResourceLocation BASE_MODEL_OPEN = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_floor_open"));
    private final ResourceLocation BASE_MODEL_CORKED = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_floor_corked"));
    private final ResourceLocation BASE_MODEL_CANDLE = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_floor_candled"));

    private final ResourceLocation HANGING_MODEL_OPEN = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_hanging_open"));
    private final ResourceLocation HANGING_MODEL_CORKED = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_hanging_corked"));

    private final ResourceLocation UNLIT_TEXTURE = buildFormattedTexturePath(new ResourceLocation(ArcaneMod.MODID, "spell_jar_unlit"));
    private final ResourceLocation LIT_TEXTURE = buildFormattedTexturePath(new ResourceLocation(ArcaneMod.MODID, "spell_jar_lit"));

    private final ResourceLocation BASE_MODEL_SWING = buildFormattedAnimationPath(new ResourceLocation(ArcaneMod.MODID, "jar_swing"));

    public GlassJarModel() {
        super(new ResourceLocation(ArcaneMod.MODID, "glass_jar"));
    }

    @Override
    public ResourceLocation getAnimationResource(GlassJarBlockEntity animatable) {
        return BASE_MODEL_SWING;
    }

    @Override
    public ResourceLocation getModelResource(GlassJarBlockEntity animatable) {
        if(animatable.getLevel().getBlockState(animatable.getBlockPos()).getBlock() instanceof GlassJarBlock) {
            if(!isHanging(animatable)) {
                if(isCandled(animatable)) {
                    return BASE_MODEL_CANDLE;
                }
                if(isCorked(animatable)) {
                    return BASE_MODEL_CORKED;
                }
            } else {
                if(isCorked(animatable)) {
                    return HANGING_MODEL_CORKED;
                } else {
                    return HANGING_MODEL_OPEN;
                }
            }
        }
        return BASE_MODEL_OPEN;
    }

    @Override
    public ResourceLocation getTextureResource(GlassJarBlockEntity animatable) {
        if(animatable.getLevel().getBlockState(animatable.getBlockPos()).getBlock() instanceof GlassJarBlock) {
            if(isLit(animatable)) {
                return LIT_TEXTURE;
            }
        }
        return UNLIT_TEXTURE;
    }

    @Override
    public RenderType getRenderType(GlassJarBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }

    private BlockState getState(GlassJarBlockEntity block) {
        return block.getLevel().getBlockState(block.getBlockPos());
    }

    private boolean isHanging(GlassJarBlockEntity block) {
        return getState(block).getValue(GlassJarBlock.HANGING);
    }

    private boolean isCorked(GlassJarBlockEntity block) {
        return getState(block).getValue(GlassJarBlock.CORKED);
    }

    private boolean isCandled(GlassJarBlockEntity block) {
        return getState(block).getValue(GlassJarBlock.HAS_CANDLE);
    }

    private boolean isLit(GlassJarBlockEntity block) {
        return getState(block).getValue(GlassJarBlock.LIT);
    }
}
