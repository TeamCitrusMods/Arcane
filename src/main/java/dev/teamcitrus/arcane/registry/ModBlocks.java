package dev.teamcitrus.arcane.registry;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.block.GlassJarBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArcaneMod.MODID);

    public static final RegistryObject<GlassJarBlock> GLASS_JAR = BLOCKS.register("glass_jar", () -> new GlassJarBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
}
