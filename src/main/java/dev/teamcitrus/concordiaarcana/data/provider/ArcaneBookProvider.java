package dev.teamcitrus.concordiaarcana.data.provider;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import dev.teamcitrus.concordiaarcana.ConcordiaArcanaMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

public class ArcaneBookProvider extends BookProvider {
    public ArcaneBookProvider(PackOutput packOutput, LanguageProvider lang) {
        super(packOutput, ConcordiaArcanaMod.MODID, lang);
    }

    private BookModel makeBook(String bookName) {
        //The lang helper keeps track of the "DescriptionIds", that is, the language keys for translations, for us
        var helper = ModonomiconAPI.get().getLangHelper(this.modid);

        //we tell the helper the book we're in.
        helper.book(bookName);

        //Now we create the book with settings of our choosing
        var demoBook = BookModel.builder()
                .withId(this.modLoc(bookName)) //the id of the book. modLoc() prepends the mod id.
                .withName(helper.bookName()) //the name of the book. The lang helper gives us the correct translation key.
                .withTooltip(helper.bookTooltip()) //the hover tooltip for the book. Again we get a translation key.
                .withGenerateBookItem(true) //auto-generate a book item for us.
                .withModel(new ResourceLocation("modonomicon:modonomicon_red")) //use the default red modonomicon icon for the book
                .withCreativeTab(new ResourceLocation(ModonomiconAPI.ID, "modonomicon")) //and put it in the modonomicon tab
                .build();
        return demoBook;
    }

    @Override
    protected void generate() {
        BookModel arcaneBook = this.makeBook(ConcordiaArcanaMod.MODID);

        this.add(arcaneBook);
    }
}
