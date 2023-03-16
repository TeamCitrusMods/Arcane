package dev.teamcitrus.arcane.client.model.block;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.block.GlassJarBlock;
import dev.teamcitrus.arcane.blockentity.GlassJarBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class GlassJarModel extends DefaultedBlockGeoModel<GlassJarBlockEntity> {
    private final ResourceLocation BASE_MODEL_OPEN = buildFormattedModelPath(new ResourceLocation(ArcaneMod.MODID, "jar_floor_open"));

    private final ResourceLocation BASE_MODEL_SWING = buildFormattedAnimationPath(new ResourceLocation(ArcaneMod.MODID, "jar_swing"));

    private final ResourceLocation BASE_IDLE = buildFormattedAnimationPath(new ResourceLocation(GeckoLib.MOD_ID, "misc.idle"));

    public GlassJarModel() {
        super(new ResourceLocation(ArcaneMod.MODID, "glass_jar"));
    }

    @Override
    public ResourceLocation getAnimationResource(GlassJarBlockEntity animatable) {
        return BASE_MODEL_SWING;
    }

    @Override
    public ResourceLocation getModelResource(GlassJarBlockEntity animatable) {
        return BASE_MODEL_OPEN;
    }

    @Override
    public RenderType getRenderType(GlassJarBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}
