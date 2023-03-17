package dev.teamcitrus.arcane.client.renderer.item;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.item.JournalItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class JournalRenderer extends GeoItemRenderer<JournalItem> {
    public JournalRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(ArcaneMod.MODID, "journal")));
    }
}
