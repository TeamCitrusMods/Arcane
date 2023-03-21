package dev.teamcitrus.concordiaarcana.client.model.block;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.block.SpellJarBlock;
import dev.teamcitrus.concordiaarcana.blockentity.SpellJarBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class SpellJarModel extends DefaultedBlockGeoModel<SpellJarBlockEntity> {
    private final ResourceLocation BASE_MODEL_OPEN = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_floor_open"));
    private final ResourceLocation BASE_MODEL_CORKED = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_floor_corked"));
    private final ResourceLocation BASE_MODEL_CANDLE = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_floor_candled"));

    private final ResourceLocation HANGING_MODEL_OPEN = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_hanging_open"));
    private final ResourceLocation HANGING_MODEL_CORKED = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_hanging_corked"));
    private final ResourceLocation HANGING_MODEL_CANDLED = buildFormattedModelPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_hanging_candled"));

    private final ResourceLocation UNLIT_TEXTURE = buildFormattedTexturePath(new ResourceLocation(ConcordiaArcanaMod.MODID, "spell_jar_unlit"));
    private final ResourceLocation LIT_TEXTURE = buildFormattedTexturePath(new ResourceLocation(ConcordiaArcanaMod.MODID, "spell_jar_lit"));

    private final ResourceLocation BASE_MODEL_SWING = buildFormattedAnimationPath(new ResourceLocation(ConcordiaArcanaMod.MODID, "jar_swing"));

    public SpellJarModel() {
        super(new ResourceLocation(ConcordiaArcanaMod.MODID, "spell_jar"));
    }

    @Override
    public ResourceLocation getAnimationResource(SpellJarBlockEntity animatable) {
        return BASE_MODEL_SWING;
    }

    @Override
    public ResourceLocation getModelResource(SpellJarBlockEntity animatable) {
        if (animatable.getLevel().getBlockState(animatable.getBlockPos()).getBlock() instanceof SpellJarBlock) {
            if (!isHanging(animatable)) {
                if (isCandled(animatable)) {
                    return BASE_MODEL_CANDLE;
                }
                if (isCorked(animatable)) {
                    return BASE_MODEL_CORKED;
                }
            } else {
                if (isCandled(animatable)) {
                    return HANGING_MODEL_CANDLED;
                }
                if (isCorked(animatable)) {
                    return HANGING_MODEL_CORKED;
                } else {
                    return HANGING_MODEL_OPEN;
                }
            }
        }
        return BASE_MODEL_OPEN;
    }

    @Override
    public ResourceLocation getTextureResource(SpellJarBlockEntity animatable) {
        if(animatable.getLevel().getBlockState(animatable.getBlockPos()).getBlock() instanceof SpellJarBlock) {
            if(isLit(animatable)) {
                return LIT_TEXTURE;
            }
        }
        return UNLIT_TEXTURE;
    }

    @Override
    public RenderType getRenderType(SpellJarBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }

    private BlockState getState(SpellJarBlockEntity block) {
        return block.getLevel().getBlockState(block.getBlockPos());
    }

    private boolean isHanging(SpellJarBlockEntity block) {
        return getState(block).getValue(SpellJarBlock.HANGING);
    }

    private boolean isCorked(SpellJarBlockEntity block) {
        return getState(block).getValue(SpellJarBlock.CORKED);
    }

    private boolean isCandled(SpellJarBlockEntity block) {
        return getState(block).getValue(SpellJarBlock.HAS_CANDLE);
    }

    private boolean isLit(SpellJarBlockEntity block) {
        return getState(block).getValue(SpellJarBlock.LIT);
    }
}
