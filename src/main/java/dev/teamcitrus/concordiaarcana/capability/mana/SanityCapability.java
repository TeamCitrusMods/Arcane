package dev.teamcitrus.concordiaarcana.capability.mana;

import dev.teamcitrus.concordiaarcana.registry.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SanityCapability implements ISanityCapability {
    private int sanity = 0;

    @Override
    public int getSanity() {
        return sanity;
    }

    @Override
    public void setSanity(int amount) {
        this.sanity = amount;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("sanity", getSanity());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.sanity = nbt.getInt("sanity");
    }

    public static class SanityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final ISanityCapability cap = new SanityCapability();
        private final LazyOptional<ISanityCapability> lazyMana = LazyOptional.of(() -> cap);

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
}
