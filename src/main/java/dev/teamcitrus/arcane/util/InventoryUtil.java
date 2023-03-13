package dev.teamcitrus.arcane.util;

import dev.teamcitrus.arcane.ArcaneMod;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

/*
InventoryUtil is a class from Lothrazar's RootsClassic: https://github.com/Lothrazar/RootsClassic/blob/1.19.x/src/main/java/elucent/rootsclassic/util/InventoryUtil.java
Imported here to help make using the inventory a lot easier
 */
public class InventoryUtil {
    public static boolean isFull(IItemHandler itemHandler) {
        ArcaneMod.LOGGER.warn("1");
        if (itemHandler == null) {
            ArcaneMod.LOGGER.warn("End");
            return true;
        }
        ArcaneMod.LOGGER.warn("2");
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ArcaneMod.LOGGER.warn("3");
            ItemStack stack = itemHandler.getStackInSlot(i);
            ArcaneMod.LOGGER.warn("4");
            if (stack.isEmpty() || (stack.getCount() < stack.getMaxStackSize())) {
                ArcaneMod.LOGGER.warn("5");
                return false;
            }
        }
        ArcaneMod.LOGGER.warn("6");
        return true;
    }
}
