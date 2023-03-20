package dev.teamcitrus.concordiaarcana.data.provider;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcaneItemProvider extends ItemModelProvider {
    public ArcaneItemProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ConcordiaArcanaMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CORK.get());
        basicItem(ModItems.BLACK_PEPPER.get());
        basicItem(ModItems.CINNAMON.get());
        basicItem(ModItems.HIMALAYAN_SALT.get());
        basicItem(ModItems.LABRADORITE.get());
        basicItem(ModItems.PYRITE.get());
        basicItem(ModItems.ROSE_QUARTZ.get());
        basicItem(ModItems.SALT.get());
        basicItem(ModItems.SELENITE.get());
        basicItem(ModItems.SMOKEY_QUARTZ.get());
        basicItem(ModItems.TIGERS_EYE.get());
    }
}
