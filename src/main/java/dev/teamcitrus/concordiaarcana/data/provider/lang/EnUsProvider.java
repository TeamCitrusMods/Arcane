package dev.teamcitrus.concordiaarcana.data.provider.lang;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import dev.teamcitrus.concordiaarcana.registry.ModBlocks;
import dev.teamcitrus.concordiaarcana.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, ConcordiaArcanaMod.MODID, "en_us");
    }

    private void addBlocks() {
        add(ModBlocks.SPELL_JAR.get(), "Spell Jar");
    }

    private void addItems() {
        add(ModItems.JOURNAL.get(), "The Journal");
        add(ModItems.CORK.get(), "Cork");
        add(ModItems.ROSE_QUARTZ.get(), "Rose Quartz");
        add(ModItems.SELENITE.get(), "Selenite");
        add(ModItems.TIGERS_EYE.get(), "Tigers Eye");
        add(ModItems.PYRITE.get(), "Pyrite");
        add(ModItems.LABRADORITE.get(), "Labradorite");
        add(ModItems.SMOKEY_QUARTZ.get(), "Smokey Quartz");
        add(ModItems.ROSEMARY.get(), "Rosemary");
        add(ModItems.SALT.get(), "Salt");
        add(ModItems.PINK_ROCKSALT.get(), "Pink Rocksalt");
        add(ModItems.BLACK_PEPPER.get(), "Black Pepper");
        add(ModItems.CINNAMON.get(), "Cinnamon");
        add(ModItems.GARLIC_POWDER.get(), "Garlic Powder");
        add(ModItems.PARSLEY.get(), "Parsley");
        add(ModItems.BAY_LEAVES.get(), "Bay Leaves");
        add(ModItems.CHAMOMILE.get(), "Chamomile");
        add(ModItems.THYME.get(), "Thyme");
        add(ModItems.MINT.get(), "Mint");
        add(ModItems.STINGING_NETTLES.get(), "Stinging Nettles");
    }

    private void addBook() {
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(ConcordiaArcanaMod.MODID);
        helper.book(ConcordiaArcanaMod.MODID);
        this.add(helper.bookName(), "The Journal");
        this.add(helper.bookTooltip(), "1st Edition");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.concordiaarcana", "Concordia Arcana");
        addBlocks();
        addItems();
        addBook();
    }
}
