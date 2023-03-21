package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.block.SpellJarBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ConcordiaArcanaMod.MODID);

    public static final RegistryObject<SpellJarBlock> SPELL_JAR = BLOCKS.register("spell_jar", () -> new SpellJarBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f, 3600000.0F).sound(SoundType.GLASS)));
}
