package com.sammy.malum.client.screen.codex.objects.progression;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.client.gui.*;

import java.util.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class RiteEntryObject extends ProgressionEntryObject {
    public final SpiritRiteType riteType;

    public RiteEntryObject(BookEntry entry, int posX, int posY) {
        super(entry, posX, posY);
        var riteType = getRiteTypeFromEntry(entry);
        if (riteType.isPresent()) {
            this.riteType = riteType.get();
        } else {
            throw new IllegalArgumentException("Entry " + entry.translationKey() + " lacks a spirit rite page");
        }
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
        renderRiteIcon(riteType, guiGraphics.pose(), getOffsetXPosition() + 8, getOffsetYPosition() + 8);
    }

    public static Optional<SpiritRiteType> getRiteTypeFromEntry(BookEntry entry) {
        return entry.pages.stream().filter(p -> p instanceof SpiritRiteTextPage).map(p -> ((SpiritRiteTextPage) p).rite).findAny();
    }
}