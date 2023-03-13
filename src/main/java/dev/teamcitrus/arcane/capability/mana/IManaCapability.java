package dev.teamcitrus.arcane.capability.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaCapability extends INBTSerializable<CompoundTag> {
    int getMana();
    void setMana(int mana);
}
