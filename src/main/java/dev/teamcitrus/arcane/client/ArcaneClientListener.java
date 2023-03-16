package dev.teamcitrus.arcane.client;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.client.renderer.block.GlassJarBlockRenderer;
import dev.teamcitrus.arcane.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcaneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArcaneClientListener {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GLASS_JAR.get(), context -> new GlassJarBlockRenderer());
    }
}
