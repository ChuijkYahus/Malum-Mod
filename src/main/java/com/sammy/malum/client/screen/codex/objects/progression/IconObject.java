package com.sammy.malum.client.screen.codex.objects.progression;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

@Deprecated
public class IconObject extends ProgressionEntryObject {
    public final ResourceLocation textureLocation;
    public final int textureWidth;
    public final int textureHeight;

    public IconObject(BookEntry entry, int posX, int posY, ResourceLocation textureLocation) {
        this(entry, posX, posY, textureLocation, 16, 16);
    }

    public IconObject(BookEntry entry, int posX, int posY, ResourceLocation textureLocation, int textureWidth, int textureHeight) {
        super(entry, posX, posY);
        this.textureLocation = textureLocation;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
        int x = getCenterX() - textureWidth/2;
        int y = getCenterY() - textureHeight/2;
        renderWavyIcon(textureLocation, guiGraphics.pose(), x, y, 0, textureWidth, textureHeight);
    }
}