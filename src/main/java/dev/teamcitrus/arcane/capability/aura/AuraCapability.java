package dev.teamcitrus.arcane.capability.aura;

import dev.teamcitrus.arcane.capability.mana.ISanityCapability;
import dev.teamcitrus.arcane.registry.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AuraCapability implements IAuraCapability {
    private int aura;

    @Override
    public int getAura() {
        return this.aura;
    }

    @Override
    public void drainAura(int amount) {
        if(amount <= this.aura) {
            this.aura = this.aura - amount;
        }
    }

    @Override
    public void setAura(int amount) {
        this.aura = amount;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("aura", getAura());
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.aura = nbt.getInt("aura");
    }

    public static class AuraProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final IAuraCapability cap = new AuraCapability();
        private final LazyOptional<IAuraCapability> lazyAura = LazyOptional.of(() -> cap);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ModCapabilities.AURA.orEmpty(cap, this.lazyAura);
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.cap.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.cap.deserializeNBT(nbt);
        }
    }
}
