package dev.teamcitrus.concordiaarcana.client.renderer.item;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.item.JournalItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class JournalRenderer extends GeoItemRenderer<JournalItem> {
    public JournalRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(ConcordiaArcanaMod.MODID, "journal")));
    }
}
