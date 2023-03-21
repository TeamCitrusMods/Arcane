package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.blockentity.SpellJarBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ConcordiaArcanaMod.MODID);

    public static final RegistryObject<BlockEntityType<SpellJarBlockEntity>> SPELL_JAR = BLOCK_ENTITIES.register("spell_jar", () -> BlockEntityType.Builder.of(SpellJarBlockEntity::new, ModBlocks.SPELL_JAR.get()).build(null));
}
