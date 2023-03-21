package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ConcordiaArcanaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    @SubscribeEvent
    public static void registerCreativeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(ConcordiaArcanaMod.MODID, "tab"), builder -> builder.title(Component.translatable("itemGroup.concordiaarcana"))
                .icon(() -> new ItemStack(ModItems.PINK_ROCKSALT.get()))
                .displayItems((feature, output, hasPerm) -> ModItems.ITEMS.getEntries().forEach(i -> output.accept(i.get()))));
    }
}
