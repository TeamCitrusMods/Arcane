package dev.teamcitrus.arcane.data.provider;

import dev.teamcitrus.arcane.ArcaneMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcaneBlockStateProvider extends BlockStateProvider {
    public ArcaneBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ArcaneMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
