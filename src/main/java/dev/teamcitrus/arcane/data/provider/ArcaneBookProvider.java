package dev.teamcitrus.arcane.data.provider;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import dev.teamcitrus.arcane.ArcaneMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

public class ArcaneBookProvider extends BookProvider {
    public ArcaneBookProvider(PackOutput packOutput, LanguageProvider lang) {
        super(packOutput, ArcaneMod.MODID, lang);
    }

    private BookModel makeBook(String bookName) {
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(this.modid);
        helper.book(bookName);

        BookModel book = BookModel.builder()
                .withId(this.modLoc(bookName))
                .withName(helper.bookName())
                .withTooltip(helper.bookTooltip())
                .withGenerateBookItem(true)
                .withModel(new ResourceLocation("modonomicon:modonomicon_red"))
                .withCreativeTab("modonomicon")
                .build();

        return book;
    }

    @Override
    protected void generate() {
        BookModel arcaneBook = this.makeBook(ArcaneMod.MODID);

        this.add(arcaneBook);
    }
}
