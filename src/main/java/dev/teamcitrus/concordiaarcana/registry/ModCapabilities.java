package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.capability.aura.AuraCapability;
import dev.teamcitrus.concordiaarcana.capability.aura.IAuraCapability;
import dev.teamcitrus.concordiaarcana.capability.mana.ISanityCapability;
import dev.teamcitrus.concordiaarcana.capability.mana.SanityCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ConcordiaArcanaMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {
    public static final Capability<IAuraCapability> AURA = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<ISanityCapability> MANA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(IAuraCapability.class);
        event.register(ISanityCapability.class);
    }

    @SubscribeEvent
    public static void attachEntityCaps(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(ConcordiaArcanaMod.MODID, "mana"), new SanityCapability.SanityProvider());
        }
    }

    @SubscribeEvent
    public static void attachWorldCaps(AttachCapabilitiesEvent<LevelChunk> event) {
        event.addCapability(new ResourceLocation(ConcordiaArcanaMod.MODID, "aura"), new AuraCapability.AuraProvider());
    }
}
