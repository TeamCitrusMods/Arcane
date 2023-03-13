package dev.teamcitrus.arcane.capability.mana;

import dev.teamcitrus.arcane.registry.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final IManaCapability cap = new ManaCapability();
    private final LazyOptional<IManaCapability> lazyMana = LazyOptional.of(() -> cap);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.MANA.orEmpty(cap, this.lazyMana);
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
