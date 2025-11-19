package com.sammy.malum.client.screen.waveform;

import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.systems.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.*;
import com.sammy.malum.common.payloads.waveform.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.neoforged.neoforge.network.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import static com.sammy.malum.MalumMod.malumPath;

public class GustGizmoConfigurationScreen extends AbstractValueConfigurationScreen {

    private static final int DIAL_WIDTH = 32;
    private static final int DIAL_HEIGHT = 64;

    protected static final ResourceLocation DIAL_TEXTURE = malumPath("textures/gui/waveform_artifice/gust_gizmo_configuration_dial.png");

    private final GustIgniterBlockEntity gustIgniter;
    private final boolean isTunnel;

    private int oldStrength, strength;
    private boolean modified;

    public GustGizmoConfigurationScreen(GustIgniterBlockEntity gustIgniter, boolean isTunnel) {
        super(getTitleComponent(gustIgniter), 180+DIAL_WIDTH, 60+DIAL_HEIGHT, DIAL_WIDTH, DIAL_HEIGHT);
        this.gustIgniter = gustIgniter;
        this.isTunnel = isTunnel;
        this.strength = gustIgniter.strength;
        this.modified = gustIgniter.modified;
    }

    public static Component getTitleComponent(LodestoneBlockEntity blockEntity) {
        if (blockEntity instanceof GustIgniterBlockEntity igniter) {
            var igniterPos = igniter.getBlockPos();
            var facing = igniter.getBlockState().getValue(GustIgniterBlock.FACING);
            var tunnelPos = igniterPos.relative(facing);
            if (igniter.getLevel().getBlockEntity(tunnelPos) instanceof WindTunnelBlockEntity tunnel) {
                return AbstractValueConfigurationScreen.getTitleComponent(tunnel);
            }
        }
        return AbstractValueConfigurationScreen.getTitleComponent(blockEntity);
    }

    @Override
    protected void notifyServer(boolean isOpen) {
        PacketDistributor.sendToServer(new GustGizmoStateUpdatePayload(gustIgniter.getBlockPos(), isOpen, new AbstractGustGizmoBlockEntity.GustGizmoInfo(strength, modified)));
    }

    @Override
    public void updateMousePosition(double mouseX, double mouseY) {
        double newMouseX = Mth.clamp(mouseX, dialLeft, dialRight);
        double newMouseY = dialBottom - strength * 2;
        setCursor(newMouseX, newMouseY);
    }

    @Override
    protected boolean hasChanged() {
        if (oldStrength != strength) {
            oldStrength = strength;
            return true;
        }
        return false;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (!disableMouse) {
            if (isHovering(mouseX, mouseY, dialLeft, dialTop, DIAL_WIDTH, DIAL_HEIGHT)) {
                double offsetY = (dialBottom - mouseY) / 2;
                strength = clampStrength(Mth.ceil(offsetY));
            }
        }
        disableMouse = false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        modified = !modified;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        strength = clampStrength(strength+(scrollY > 0 ? 1 : -1));
        updateMousePosition(mouseX, mouseY);
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        for (int i = 0; i < 3; i++) {
            Component guideText = Component.translatable("malum.waveform_artifice.guide." + i);
            int y = guiTop + screenHeight - 10 * (3 - i);
            guiGraphics.drawString(font, guideText, xCenter - font.width(guideText) / 2, y, 0xdddddd, false);
        }
        for (int i = 0; i < 2; i++) {
            boolean isModified = i == 1;
            Component text = getModeText(isModified);
            renderTextWidget(guiGraphics, text, dialLeft - BORDER_SIZE, dialTop + 13 * i, isModified == modified, partialTick);
        }

        renderBorderBackground(guiGraphics, dialLeft, dialTop, DIAL_WIDTH, DIAL_HEIGHT);
        renderDial(guiGraphics, dialLeft, dialTop);
        renderBorder(guiGraphics, dialLeft, dialTop, DIAL_WIDTH, DIAL_HEIGHT);
        var text = Component.literal(FORMAT.format(strength));
        renderText(guiGraphics, text, xDialCenter + 0.5f - font.width(text) / 2f, yDialCenter + 0.5f - font.lineHeight / 2f, true, partialTick);
    }

    public int clampStrength(int strength) {
        int newStrength = strength;
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 344)) {
            if (oldStrength > strength) {
                newStrength = Mth.floor((strength / 4f)) * 4;
            } else {
                newStrength = Mth.ceil((strength / 4f)) * 4;
            }
        }
        int min = 1;
        int max = 32;
        return Mth.clamp(newStrength, min, max);
    }

    public Component getModeText(boolean isModified) {
        if (title.getContents() instanceof TranslatableContents contents) {
            return Component.translatable(contents.getKey() + "." + (isModified ? "alt" : "default"));
        }
        throw new IllegalArgumentException();
    }

    public void renderDial(GuiGraphics graphics, int x, int y) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        shaderInstance.safeGetUniform("Intensity").set(100f);

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setAlpha(0.9f)
                .setColor(0.7f, 0.1f, 0.1f);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        renderDialTexture(graphics, builder, x, y);
        builder.setAlpha(0.2f);
        shaderInstance.safeGetUniform("Speed").set(800f);
        renderDialTexture(graphics, builder, x - 1, y);
        renderDialTexture(graphics, builder, x + 1, y);
        renderDialTexture(graphics, builder, x, y - 1);
        renderDialTexture(graphics, builder, x, y + 1);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public void renderDialTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y) {
        builder.setTexture(DIAL_TEXTURE).setPositionWithWidth(x, y, DIAL_WIDTH, DIAL_HEIGHT).blit(graphics.pose());
    }
}