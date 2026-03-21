package com.sammy.malum.client.screen.codex.objects.progression;

import com.google.common.collect.*;
import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.rendering.*;

import java.awt.*;
import java.util.List;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class ProgressionEntryObject extends AbstractSelectableEntryObject<AbstractProgressionCodexScreen> {

    public WidgetDesign design = WidgetDesignType.DEFAULT.createDesign(RUNEWOOD, PAPER);
    public boolean isOrigin;

    protected int oldOutlineVisibility;
    protected int outlineVisibility;

    public ProgressionEntryObject(BookEntry entry, int posX, int posY) {
        super(entry, posX, posY, 32, 32);
    }

    @Override
    public boolean isInView(AbstractProgressionCodexScreen screen) {
        return screen.isInView(getOffsetXPosition(), getOffsetYPosition())
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition())
                || screen.isInView(getOffsetXPosition(), getOffsetYPosition() + height)
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition() + height);
    }

    @Override
    public void tick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (design.getDesignType().equals(WidgetDesignType.EMPTY)) {
            return;
        }
        oldOutlineVisibility = outlineVisibility;
        if (isHoveredOver) {
            if (outlineVisibility == 6) {
                screen.playSound(MalumSoundEvents.ARCANA_ENTRY_HOVER, 0.2f, 1f);
            }
            if (outlineVisibility < 20) {
                outlineVisibility = Math.min(outlineVisibility + 2, 20);
            }
        }
        else {
            if (outlineVisibility == 15) {
                screen.playSound(MalumSoundEvents.ARCANA_ENTRY_UNHOVER, 0.1f, 0.75f);
            }
            if (outlineVisibility > 0) {
                outlineVisibility--;
            }
        }
    }

    @Override
    public void applyTransforms(AbstractProgressionCodexScreen screen, PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        float effectStrength = Mth.lerp(partialTicks, oldOutlineVisibility, outlineVisibility) / 20f;
        if (effectStrength > 0) {
            float offset = Easing.CIRC_OUT.ease(effectStrength, 0, 2);
            poseStack.translate(0, -offset, 0);
        }
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        var poseStack = guiGraphics.pose();
        int posX = getOffsetXPosition() - 16;
        int posY = getOffsetYPosition() - 16;
        int centerX = getCenterX();
        int centerY = getCenterY();
        renderTexture(WIDGET_FADE_TEXTURE, poseStack, centerX - 29, centerY - 29, 0, 0, 58, 58);
        if (design != null) {
            var minecraft = screen.getMinecraft();
            float delta = minecraft.getTimer().getGameTimeDeltaPartialTick(true);
            float effectStrength = Mth.lerp(delta, oldOutlineVisibility, outlineVisibility) / 20f;
            if (effectStrength > 0) {
                float distortionIntensity = 5f + 35f * effectStrength;
                RenderSystem.depthMask(true);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();

                float darknessAlpha = Math.min(effectStrength * 2.5f, 1f);
                renderOutline(poseStack, distortionIntensity, darknessAlpha, WidgetDesignType::getOutlineTexture, i -> Color.BLACK);

                if (effectStrength >= 0.5f) {
                    float glowAlpha = (effectStrength - 0.5f) * 2f;
                    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                    renderOutline(poseStack, distortionIntensity, glowAlpha, WidgetDesignType::getGlowTexture, this::getSpiritColor);
                }

                RenderSystem.defaultBlendFunc();
                RenderSystem.disableBlend();
            }
            design.getFrameTexture().ifPresent(texture -> renderTexture(texture, poseStack, posX, posY, 0, 0, 64, 64));
            design.getFillingTexture().ifPresent(texture -> renderTexture(texture, poseStack, posX, posY, 0, 0, 64, 64));
        }
        if (iconStack != null) {
            guiGraphics.renderItem(iconStack, centerX-8, centerY-8);
        }
    }

    @Override
    public List<Component> gatherTooltip(AbstractProgressionCodexScreen screen) {
        var tooltip = super.gatherTooltip(screen);
        ImmutableList<EntryReference> references = entry.references;
        for (int i = references.size()-1; i >=0; i--) {
            EntryReference reference = references.get(i);
            if (reference.entry.shouldShow()) {
                var slash = Component.literal("┇ ");
                var text = Component.translatable(reference.entry.translationKey());
                var component = slash.append(text).withStyle(ChatFormatting.DARK_GRAY);
                tooltip.add(1, component);
            }
        }
        return tooltip;
    }

    public void renderOutline(PoseStack poseStack, float distortionIntensity, float intensity, Function<WidgetDesignType, ResourceLocation> texture, Int2ObjectFunction<Color> colorSupplier) {
        int posX = getOffsetXPosition() - 16;
        int posY = getOffsetYPosition() - 16;
        var minecraft = Minecraft.getInstance();
        float delta = minecraft.getTimer().getGameTimeDeltaPartialTick(true);
        var light = LodestoneShaders.RADIAL_DISTORTED_SCREEN_LIGHT.getShaderInstance();
        light.safeGetUniform("YFrequency").set(24f);
        light.safeGetUniform("XFrequency").set(32f);
        light.safeGetUniform("Speed").set(2000f);
        light.safeGetUniform("Intensity").set(distortionIntensity);
        light.safeGetUniform("LumiTransparency").set(1f);
        light.safeGetUniform("Width").set(80f);
        light.safeGetUniform("Height").set(80f);
        var builder = VFXBuilders.createScreen()
                .setTexture(texture.apply(design.getDesignType()))
                .setPositionWithWidth(posX, posY, 64, 64)
                .setShader(light);
        float offset = hashCode() % 3600;
        float time = (minecraft.level.getGameTime() + delta) * 0.4f + offset;
        for (int i = 0; i < 8; i++) {
            int angle = (int) ((time * 2 + i * 90) % 720);
            float glowAlpha = 1 - Mth.abs(1 - (angle / 180f));
            if (angle >= 360) {
                continue;
            }
            light.safeGetUniform("Angle").set(angle);
            builder.setColor(colorSupplier.get(i));
            for (int j = 0; j < 2; j++) {
                float range = 120f * (j+1) * intensity;
                light.safeGetUniform("LightAngleRange").set(range);
                builder.setAlpha(glowAlpha * intensity).blit(poseStack);
            }
        }
        light.setUniformDefaults();
    }

    public Color getSpiritColor(int index) {
        var spirits = new SpiritArcanaType[] {
                MalumSpiritTypes.SACRED_SPIRIT.get(),
                MalumSpiritTypes.AERIAL_SPIRIT.get(),
                MalumSpiritTypes.WICKED_SPIRIT.get(),
                MalumSpiritTypes.AQUEOUS_SPIRIT.get(),
                MalumSpiritTypes.ARCANE_SPIRIT.get(),
                MalumSpiritTypes.EARTHEN_SPIRIT.get(),
                MalumSpiritTypes.ELDRITCH_SPIRIT.get(),
                MalumSpiritTypes.INFERNAL_SPIRIT.get()
        };
        return spirits[index].getPrimaryColor();
    }

    public int getCenterX() {
        return getOffsetXPosition() + width / 2;
    }

    public int getCenterY() {
        return getOffsetYPosition() + height / 2;
    }

    @Override
    public ProgressionEntryObject setIcon(Supplier<? extends Item> item) {
        return (ProgressionEntryObject) super.setIcon(item);
    }

    @Override
    public ProgressionEntryObject setIcon(Item item) {
        return (ProgressionEntryObject) super.setIcon(item);
    }

    @Override
    public ProgressionEntryObject setIcon(Holder<GeasEffectType> geas) {
        return (ProgressionEntryObject) super.setIcon(geas);
    }

    @Override
    public ProgressionEntryObject setIcon(ItemStack itemStack) {
        return (ProgressionEntryObject) super.setIcon(itemStack);
    }

    @Override
    public ProgressionEntryObject setCondition(Predicate<AbstractProgressionCodexScreen> isValid) {
        return (ProgressionEntryObject) super.setCondition(isValid);
    }

    public ProgressionEntryObject setDesign(WidgetDesignType design, WidgetDesignType.FrameType frame, WidgetDesignType.FillingType filling) {
        return setDesign(design.createDesign(frame, filling));
    }

    public ProgressionEntryObject setDesign(WidgetDesign design) {
        this.design = design;
        return this;
    }

    public ProgressionEntryObject setOrigin() {
        this.isOrigin = true;
        return this;
    }
}
