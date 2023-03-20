package dev.teamcitrus.concordiaarcana;

import com.mojang.logging.LogUtils;
import dev.teamcitrus.concordiaarcana.registry.ModBlockEntities;
import dev.teamcitrus.concordiaarcana.registry.ModBlocks;
import dev.teamcitrus.concordiaarcana.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ConcordiaArcanaMod.MODID)
public class ConcordiaArcanaMod {
    public static final String MODID = "concordiaarcana";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ConcordiaArcanaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }
}
