package dev.teamcitrus.arcane.registry;

import dev.teamcitrus.arcane.ArcaneMod;
import dev.teamcitrus.arcane.item.CorkItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcaneMod.MODID);

    public static final RegistryObject<Item> GLASS_JAR_ITEM = ITEMS.register("glass_jar", () -> new BlockItem(ModBlocks.GLASS_JAR.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORK = ITEMS.register("cork", () -> new CorkItem(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SELENITE = ITEMS.register("selenite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIGERS_EYE = ITEMS.register("tigers_eye", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PYRITE = ITEMS.register("pyrite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LABRADORITE = ITEMS.register("labradorite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMOKEY_QUARTZ = ITEMS.register("smokey_quartz", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSEMARY = ITEMS.register("rosemary", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HIMALAYAN_SALT = ITEMS.register("himalayan_salt", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PEPPER = ITEMS.register("black_pepper", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CINNAMON = ITEMS.register("cinnamon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GARLIC_POWDER = ITEMS.register("garlic_powder", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PARSLEY = ITEMS.register("parsley", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BAY_LEAVES = ITEMS.register("bay_leaves", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHAMOMILE = ITEMS.register("chamomile", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THYME = ITEMS.register("thyme", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINT = ITEMS.register("mint", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STINGING_NETTLES = ITEMS.register("stinging_nettles", () -> new Item(new Item.Properties()));
}
