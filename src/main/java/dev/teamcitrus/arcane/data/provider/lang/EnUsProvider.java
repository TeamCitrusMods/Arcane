package dev.teamcitrus.arcane.data.provider.lang;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import dev.teamcitrus.arcane.ArcaneMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, ArcaneMod.MODID, "en_us");
    }

    private void addBook() {
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(ArcaneMod.MODID);
        helper.book(ArcaneMod.MODID);
        this.add(helper.bookName(), "Arcane Book");
        this.add(helper.bookTooltip(), "[WIP]");
    }

    @Override
    protected void addTranslations() {
        addBook();
    }
}
