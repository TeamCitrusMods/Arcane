package dev.teamcitrus.arcane.data;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.data.provider.ArcaneBlockStateProvider;
import dev.teamcitrus.arcane.data.provider.ArcaneItemProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcaneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArcaneData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper file = event.getExistingFileHelper();

        gen.addProvider(true, new ArcaneBlockStateProvider(gen, file));
        gen.addProvider(true, new ArcaneItemProvider(gen, file));
    }
}
