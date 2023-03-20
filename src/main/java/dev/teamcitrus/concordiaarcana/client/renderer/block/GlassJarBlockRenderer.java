package dev.teamcitrus.concordiaarcana.client.renderer.block;

import dev.teamcitrus.concordiaarcana.blockentity.GlassJarBlockEntity;
import dev.teamcitrus.concordiaarcana.client.model.block.GlassJarModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GlassJarBlockRenderer extends GeoBlockRenderer<GlassJarBlockEntity> {
    public GlassJarBlockRenderer() {
        super(new GlassJarModel());
    }
}
