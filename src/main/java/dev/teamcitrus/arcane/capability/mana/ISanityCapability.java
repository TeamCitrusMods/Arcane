package dev.teamcitrus.arcane.capability.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISanityCapability extends INBTSerializable<CompoundTag> {
    int getSanity();
    void setSanity(int amount);
}
