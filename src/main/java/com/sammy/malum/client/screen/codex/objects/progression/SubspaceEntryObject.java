package com.sammy.malum.client.screen.codex.objects.progression;

import com.google.common.collect.*;
import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.handlers.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.screen.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;
import java.util.*;
import java.util.List;

import static com.sammy.malum.MalumMod.malumPath;
import static org.lwjgl.opengl.GL11C.GL_SCISSOR_TEST;

public class SubspaceEntryObject extends ProgressionEntryObject {

    private static final ResourceLocation SUBSPACE_TEXTURE = malumPath("textures/gui/book/subspace_container.png");
    private static final int WARMUP_TIME = 16;

    protected final ScreenParticleHolder subspaceParticles = new ScreenParticleHolder();
    protected final SubspaceEntryObjectHandler objects = new SubspaceEntryObjectHandler();
    protected final List<PlacedBookEntry> entries;
    protected final int subspaceSize;

    protected boolean assembledObjects = false;

    protected boolean isActive = false;
    protected int activeDuration;

    public SubspaceEntryObject(BookEntry entry, int posX, int posY, List<PlacedBookEntry> entries, int subspaceSize) {
        super(entry, posX, posY);
        this.entries = ImmutableList.copyOf(entries);
        this.subspaceSize = subspaceSize;
    }

    @Override
    public void tick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        int centerX = getCenterX();
        int centerY = getCenterY();
        if (isActive || activeDuration > 0) {
            spawnParticles(screen, activeDuration/(float)WARMUP_TIME);
            subspaceParticles.tick();
        }
        if (isActive) {
            int xDiff = centerX - (int) mouseX;
            int yDiff = centerY - (int) mouseY;
            double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
            if (distance > (double) subspaceSize /2+16) {
                isActive = false;
            }

            if (activeDuration < WARMUP_TIME) {
                activeDuration++;
            }
            objects.tick(screen, mouseX, mouseY);
        } else {
            if (activeDuration > 0) {
                activeDuration--;
            }
            super.tick(screen, mouseX, mouseY);
        }
    }

    //☺
    @Override
    public void renderLate(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isActive || activeDuration > 0) {
            var pose = guiGraphics.pose();
            float duration = activeDuration;
            if (isActive) {
                if (duration < WARMUP_TIME) {
                    duration += partialTicks;
                }
            }
            else {
                duration -= partialTicks;
            }
            float delta = Easing.SINE_IN_OUT.ease(Math.min(0.1f + duration / (float)WARMUP_TIME, 1), 0, 1);
            var minecraft = Minecraft.getInstance();
            int scale = (int) minecraft.getWindow().getGuiScale();
            int size = (int) (subspaceSize * delta);
            int offset = size / 2;
            int x = getOffsetXPosition() + width / 2 - offset;
            int y = getOffsetYPosition() + height / 2 - offset;
            int margin = 6;
            pose.translate(0, 0, 500);
            renderSubspace(guiGraphics, x-margin, y-margin, size+margin*2, delta);

            GL11.glEnable(GL_SCISSOR_TEST);
            GL11.glScissor(
                    x * scale,
                    minecraft.getWindow().getHeight() - (y + size) * scale,
                    size * scale,
                    size * scale);

            ScreenParticleHandler.renderParticles(subspaceParticles);
            objects.renderObjects(screen, guiGraphics, xOffset, yOffset, mouseX, mouseY, partialTicks);
            GL11.glDisable(GL_SCISSOR_TEST);
            if (isActive) {
                objects.renderObjectsLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
            }
            pose.translate(0, 0, -500);
        }
        else {
            super.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public boolean tryClick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!assembledObjects) {
            objects.setupEntryObjects(screen, entries);
            assembledObjects = true;
        }
        if (isActive) {
            return objects.click(screen, mouseX, mouseY);
        }
        return super.tryClick(screen, mouseX, mouseY);
    }

    @Override
    public boolean click(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!isActive) {
            isActive = true;
            return true;
        }
        return super.click(screen, mouseX, mouseY);
    }

    public void spawnParticles(AbstractProgressionCodexScreen screen, float delta) {
        if (entry.associatedSpirit != null) {
            var minecraft = Minecraft.getInstance();
            final ClientLevel level = minecraft.level;
            var rand = level.random;
            float distance = (subspaceSize - 20) * 0.5f;
            float finalDistance = distance * RandomHelper.randomBetween(rand, 0.6f, 1.2f);
            for (int i = 0; i < 4; i++) {
                float angle = ((level.getGameTime() * 0.6f + i * 80) / 320 * 6.28f) % 6.28f;
                int lifetime = RandomHelper.randomBetween(rand, 160, 320);
                ScreenParticleBuilder.create(MalumScreenParticles.LIGHT_SPEC, subspaceParticles)
                        .setTransparencyData(GenericParticleData.create(0, 0.4f, 0f).setEasing(Easing.CUBIC_OUT, Easing.SINE_IN_OUT).build())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, RandomHelper.randomBetween(rand, 0.05f, 0.2f), 0).randomSpinOffset(rand).setEasing(Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(rand, 0.5f, 1.5f), 0).setEasing(Easing.EXPO_IN).build())
                        .setColorData(entry.associatedSpirit.createColorData().setCoefficient(0.9f))
                        .setLifetime(lifetime)
                        .addRenderActor(p -> {
                            float ageDelta = (float) p.age / p.lifetime;
                            var updatedAngle = angle + ageDelta * 0.05f;
                            var updatedDistance = Mth.clampedLerp(distance, finalDistance, ageDelta);
                            p.x = (getCenterX() + Math.sin(updatedAngle) * updatedDistance);
                            p.y = (getCenterY() + Math.cos(updatedAngle) * updatedDistance);
                        })
                        .repeat(0, 0, 2);
            }
        }
    }

    public void renderSubspace(GuiGraphics graphics, int x, int y, int size, float delta) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        shaderInstance.safeGetUniform("Intensity").set(100f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(-1f, 2f, -1f, 2f));

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setAlpha(delta);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        renderSubspaceTexture(graphics, builder, x, y, size);
        builder.setAlpha(0.2f * delta);
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        shaderInstance.safeGetUniform("Speed").set(800f);
        renderSubspaceTexture(graphics, builder, x - 1, y, size);
        renderSubspaceTexture(graphics, builder, x + 1, y, size);
        renderSubspaceTexture(graphics, builder, x, y - 1, size);
        renderSubspaceTexture(graphics, builder, x, y + 1, size);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public void renderSubspaceTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y, int size) {
        builder.setShaderTexture(SUBSPACE_TEXTURE).setPositionWithWidth(x, y, size, size).blit(graphics.pose());
    }

    public int getCenterX() {
        int posX = getOffsetXPosition() - (width - 32) / 2;
        return posX + width / 2;
    }
    public int getCenterY() {
        int posY = getOffsetYPosition() - (height - 32) / 2;
        return posY + height / 2;
    }

    public static class SubspaceWidgetSupplier implements PlacedBookEntry.WidgetSupplier, PlacedEntryAcceptor {

        protected final List<PlacedBookEntry> entries = new ArrayList<>();

        protected int size = 160;

        public SubspaceWidgetSupplier() {
        }

        public SubspaceWidgetSupplier setSize(int size) {
            this.size = size;
            return this;
        }

        @Override
        public List<PlacedBookEntry> getEntries() {
            return entries;
        }

        @Override
        public ProgressionEntryObject getBookObject(BookEntry entry, int x, int y) {
            return new SubspaceEntryObject(entry, x, y, entries, size);
        }
    }
}