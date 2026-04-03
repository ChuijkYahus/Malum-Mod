package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.helpers.*;

import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.screen.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class SpiritRiteRecipePage extends BookPage {

    private static final ScreenParticleHolder RITE_PARTICLES = new ScreenParticleHolder();

    private final SpiritRiteType riteType;

    public SpiritRiteRecipePage(RiteHolder<SpiritRiteType> riteType) {
        this.riteType = riteType.value();
    }

    @Override
    public ResourceLocation getBackground(boolean isRightSide) {
        return MalumMod.malumPath("textures/gui/book/pages/spirit_rite_recipe_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        var result = riteType.isCorrupted() ? MalumItems.SOULWOOD_TOTEM_BASE.get() : MalumItems.RUNEWOOD_TOTEM_BASE.get();
        CodexItemHelper.renderItem(screen, guiGraphics, result.getDefaultInstance(), left + 63, top + 147, mouseX, mouseY);

        var spirits = riteType.getSpirits();
        var minecraft = Minecraft.getInstance();
        var rand = minecraft.level.random;
        var poseStack = guiGraphics.pose();
        if (!isRepeat) {
            if (ScreenParticleHandler.canSpawnParticles) {
                RITE_PARTICLES.tick();
            }
            RITE_PARTICLES.render();
        }

        int riteStartX = left + 63;
        int riteStartY = top + 118;
        for (int i = 0; i < spirits.size(); i++) {
            int y = riteStartY - 20 * i;
            var spiritType = spirits.get(i);
            var spiritTexture = spiritType.getSpirit().getGlowTexture();
            var stack = spirits.get(i).getSpiritStack();
            var isCorrupt = riteType.isCorrupted();
            renderSpiritIcon(spiritTexture, poseStack, spiritType, isCorrupt, riteStartX, y);
            if (screen.isHovering(mouseX, mouseY, riteStartX, y, 16, 16)) {
                guiGraphics.renderComponentTooltip(minecraft.font, Screen.getTooltipFromItem(minecraft, stack), mouseX, mouseY);
            }
            if (ScreenParticleHandler.canSpawnParticles && minecraft.level.getGameTime() % 6L == 0) {
                int x = riteStartX + 8;
                int xOffset = 25;
                float yMotion = Easing.SINE_IN_OUT.asWeighedRandom(rand, -0.05f, -0.3f);
                int lifetime = Easing.SINE_IN_OUT.asWeighedRandom(rand, 60, 120);
                ScreenParticleBuilder.create(MalumScreenParticles.LIGHT_SPEC, RITE_PARTICLES)
                        .setTransparencyData(GenericParticleData.create(0.04f, 0.4f, 0f).setEasing(Easing.CUBIC_OUT, Easing.SINE_IN_OUT).build())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.1f, 0.2f), 0).randomSpinOffset(rand).setEasing(Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.8f, 2.4f), 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setColorData(spiritType.createColorData().setCoefficient(0.25f).build())
                        .setLifetime(lifetime)
                        .setMotion(0, yMotion)
                        .spawn(x - xOffset, y + 8 + 4 * i)
                        .spawn(x + xOffset, y + 8 + 4 * i);
            }
        }
    }
}