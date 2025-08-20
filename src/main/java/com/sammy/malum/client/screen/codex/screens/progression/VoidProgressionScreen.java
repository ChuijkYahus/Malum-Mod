package com.sammy.malum.client.screen.codex.screens.progression;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.VoidRevelationHandler;
import com.sammy.malum.client.screen.codex.BookEntry;
import com.sammy.malum.client.screen.codex.BookWidgetStyle;
import com.sammy.malum.client.screen.codex.PlacedBookEntry;
import com.sammy.malum.client.screen.codex.entries.*;
import com.sammy.malum.client.screen.codex.objects.progression.IconObject;
import com.sammy.malum.client.screen.codex.objects.progression.ScreenOpenerObject;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.pages.CyclingPage;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.client.screen.codex.pages.EntrySelectorPage;
import com.sammy.malum.client.screen.codex.pages.recipe.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.CraftingPage;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.core.systems.events.SetupMalumCodexEntriesEvent;
import com.sammy.malum.common.item.codex.EncyclopediaEsotericaItem;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.VoidRevelationHandler.RevelationType.VOID_READER;
import static com.sammy.malum.registry.common.item.MalumItems.*;
import static net.minecraft.world.item.Items.ENCHANTED_BOOK;

public class VoidProgressionScreen extends AbstractProgressionCodexScreen {

    public static final ResourceLocation BACKGROUND_TEXTURE = malumPath("textures/gui/book/void_background.png");

    public static final ProgressionScreenHolder<VoidProgressionScreen> SCREEN = new ProgressionScreenHolder<>(VoidProgressionScreen::new, MalumSoundEvents.ARCANA_TRANSITION_EVIL);

    protected VoidProgressionScreen() {
        super(MalumSoundEvents.ARCANA_SWEETENER_EVIL, 1024, 768);
        VoidRevelationHandler.seeTheRevelation(VOID_READER);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        renderBackground(poseStack, BACKGROUND_TEXTURE, 0.2f, 0.2f);
    }

    @Override
    public void setupEntries() {
        VoidCodexEntries.setupEntries(this);
    }
}
