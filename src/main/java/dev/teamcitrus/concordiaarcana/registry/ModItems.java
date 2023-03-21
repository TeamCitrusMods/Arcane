package dev.teamcitrus.concordiaarcana.registry;

import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.item.SpellJarItem;
import dev.teamcitrus.concordiaarcana.item.JournalItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ConcordiaArcanaMod.MODID);

    public static final RegistryObject<Item> SPELL_JAR_ITEM = ITEMS.register("spell_jar", () -> new SpellJarItem(ModBlocks.SPELL_JAR.get(), new Item.Properties()));

    public static final RegistryObject<Item> JOURNAL = ITEMS.register("journal", () -> new JournalItem(new Item.Properties()));
    public static final RegistryObject<Item> CORK = ITEMS.register("cork", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SELENITE = ITEMS.register("selenite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIGERS_EYE = ITEMS.register("tigers_eye", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PYRITE = ITEMS.register("pyrite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LABRADORITE = ITEMS.register("labradorite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMOKEY_QUARTZ = ITEMS.register("smokey_quartz", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSEMARY = ITEMS.register("rosemary", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ROCKSALT = ITEMS.register("pink_rocksalt", () -> new Item(new Item.Properties()));
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
