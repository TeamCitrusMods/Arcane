package dev.teamcitrus.concordiaarcana.client.renderer.block;

import dev.teamcitrus.concordiaarcana.blockentity.SpellJarBlockEntity;
import dev.teamcitrus.concordiaarcana.client.model.block.SpellJarModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SpellJarBlockRenderer extends GeoBlockRenderer<SpellJarBlockEntity> {
    public SpellJarBlockRenderer() {
        super(new SpellJarModel());
    }
}
