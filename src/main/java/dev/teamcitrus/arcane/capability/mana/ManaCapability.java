package dev.teamcitrus.arcane.capability.mana;

import net.minecraft.nbt.CompoundTag;

public class ManaCapability implements IManaCapability {
    private int mana = 0;

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("mana", getMana());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.mana = nbt.getInt("mana");
    }
}
