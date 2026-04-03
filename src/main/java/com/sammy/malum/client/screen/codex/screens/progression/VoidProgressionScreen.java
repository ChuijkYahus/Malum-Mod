package com.sammy.malum.client.screen.codex.screens.progression;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.VoidRevelationHandler;
import com.sammy.malum.client.screen.codex.entries.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.VoidRevelationHandler.RevelationType.VOID_READER;

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
