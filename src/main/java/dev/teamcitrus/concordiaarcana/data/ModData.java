package dev.teamcitrus.concordiaarcana.data;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.data.provider.ArcaneBookProvider;
import dev.teamcitrus.concordiaarcana.data.provider.ArcaneItemProvider;
import dev.teamcitrus.concordiaarcana.data.provider.lang.EnUsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ConcordiaArcanaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper file = event.getExistingFileHelper();
        PackOutput output = gen.getPackOutput();

        LanguageProvider lang = new EnUsProvider(output);
        gen.addProvider(event.includeServer(), new ArcaneBookProvider(output, lang));
        gen.addProvider(event.includeClient(), lang);

        gen.addProvider(event.includeClient(), new ArcaneItemProvider(output, file));
    }
}
