package dev.teamcitrus.concordia;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Concordia.MODID)
public class Concordia {
    public static final String MODID = "examplemod";
    private static final Logger LOGGER = LogManager.getLogger();

    public Concordia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
