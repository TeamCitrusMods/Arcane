package dev.teamcitrus.arcane;

import com.mojang.logging.LogUtils;
import dev.teamcitrus.arcane.data.ArcaneData;
import dev.teamcitrus.arcane.registry.ModBlockEntities;
import dev.teamcitrus.arcane.registry.ModBlocks;
import dev.teamcitrus.arcane.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(ArcaneMod.MODID)
public class ArcaneMod {
    public static final String MODID = "arcane";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab TAB = new CreativeModeTab("arcane") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.SALT.get());
        }
    };

    public ArcaneMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(ArcaneData::gatherData);

        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }
}
