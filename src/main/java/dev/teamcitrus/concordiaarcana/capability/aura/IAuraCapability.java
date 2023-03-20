package dev.teamcitrus.concordiaarcana.capability.aura;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAuraCapability extends INBTSerializable<CompoundTag> {
    int getAura();

    void drainAura(int amount);

    void setAura(int amount);
}
