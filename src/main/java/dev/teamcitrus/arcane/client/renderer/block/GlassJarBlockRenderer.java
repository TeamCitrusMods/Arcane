package dev.teamcitrus.arcane.client.renderer.block;

import dev.teamcitrus.arcane.blockentity.GlassJarBlockEntity;
import dev.teamcitrus.arcane.client.model.GlassJarModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GlassJarBlockRenderer extends GeoBlockRenderer<GlassJarBlockEntity> {
    public GlassJarBlockRenderer() {
        super(new GlassJarModel());
    }
}
