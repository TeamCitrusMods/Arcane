package dev.teamcitrus.arcane.registry;

import dev.teamcitrus.arcane.ArcaneMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcaneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    @SubscribeEvent
    public static void registerCreativeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(ArcaneMod.MODID, "tab"), builder -> builder.title(Component.translatable("itemGroup.arcane"))
                .icon(() -> new ItemStack(ModItems.HIMALAYAN_SALT.get()))
                .displayItems((feature, output, hasPerm) -> ModItems.ITEMS.getEntries().forEach(i -> output.accept(i.get()))));
    }
}
