package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.block.GlassJarBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ConcordiaArcanaMod.MODID);

    public static final RegistryObject<GlassJarBlock> GLASS_JAR = BLOCKS.register("glass_jar", () -> new GlassJarBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f, 3600000.0F).sound(SoundType.GLASS)));
}
