package dev.teamcitrus.arcane.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class BlockUtil {
    public static boolean isEmpty(IItemHandler itemHandler) {
        if (itemHandler == null) return true;
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static ItemStack getLastStackInBlock(IItemHandler itemHandler) {
        if (itemHandler == null) return ItemStack.EMPTY;
        for (int i = itemHandler.getSlots() - 1; i >= 0; i--) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
