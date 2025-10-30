package com.sammy.malum.client.screen.codex.objects;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.function.*;

public abstract class AbstractSelectableEntryObject<T extends AbstractMalumCodexScreen> extends BookObject<T> {

    public final BookEntry entry;
    public ItemStack iconStack;
    public Predicate<T> isValid = t -> true;

    public AbstractSelectableEntryObject(BookEntry entry, int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.entry = entry;
        this.iconStack = null;
    }

    public AbstractSelectableEntryObject(EntryReference reference, int posY, int width, int height, int posX) {
        super(posX, posY, width, height);
        this.entry = reference.entry;
        this.iconStack = reference.icon;
    }

    @Override
    public boolean isValid(T screen) {
        return isValid.test(screen) && entry.shouldShow();
    }

    @Override
    public void renderLate(T screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isHoveredOver && entry.hasTooltip()) {
            var tooltip = gatherTooltip(screen);
            guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY);
        }
    }

    public List<Component> gatherTooltip(T screen) {
        return new ArrayList<>(List.of(
                CodexTextHelper.convertToComponent(entry.translationKey(), entry.titleStyle),
                CodexTextHelper.convertToComponent(entry.descriptionTranslationKey(), entry.subtitleStyle)));
    }

    @Override
    public boolean click(T screen, double mouseX, double mouseY) {
        if (entry.hasContents()) {
            CodexEntryScreen.openScreen(entry);
            return true;
        }
        return false;
    }

    public AbstractSelectableEntryObject<T> setIcon(Supplier<? extends Item> item) {
        return setIcon(item.get());
    }

    public AbstractSelectableEntryObject<T> setIcon(Item item) {
        return setIcon(item.getDefaultInstance());
    }

    public AbstractSelectableEntryObject<T> setIcon(Holder<GeasEffectType> geas) {
        return setIcon(geas.value().createDefaultStack());
    }

    public AbstractSelectableEntryObject<T> setIcon(ItemStack itemStack) {
        iconStack = itemStack;
        return this;
    }

    public AbstractSelectableEntryObject<T> setCondition(Predicate<T> isValid) {
        this.isValid = isValid;
        return this;
    }
}