package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.blockentity.GlassJarBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ConcordiaArcanaMod.MODID);

    public static final RegistryObject<BlockEntityType<GlassJarBlockEntity>> GLASS_JAR = BLOCK_ENTITIES.register("glass_jar", () -> BlockEntityType.Builder.of(GlassJarBlockEntity::new, ModBlocks.GLASS_JAR.get()).build(null));
}
