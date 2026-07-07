package com.sammy.malum.client.screen.codex.objects.progression;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.handlers.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.screen.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import javax.annotation.*;
import java.lang.Math;
import java.util.*;
import java.util.stream.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;
import static org.lwjgl.opengl.GL11C.GL_SCISSOR_TEST;

public class SubspaceEntryObject extends ProgressionEntryObject {

    private static final ResourceLocation SUBSPACE_TEXTURE = malumPath("textures/gui/book/subspace_container.png");
    private static final ResourceLocation GLOW_TEXTURE = malumPath("textures/gui/book/subentry_glow.png");
    private static final ResourceLocation ICON_LEFT_TEXTURE = malumPath("textures/gui/book/subentry_icon_left.png");
    private static final ResourceLocation ICON_RIGHT_TEXTURE = malumPath("textures/gui/book/subentry_icon_right.png");

    private static final int WARMUP_TIME = 16;

    protected final ScreenParticleHolder entryParticles = new ScreenParticleHolder();
    protected final ScreenParticleHolder subspaceParticles = new ScreenParticleHolder();
    protected final SubspaceEntryObjectHandler objects = new SubspaceEntryObjectHandler();
    protected final EntryStorage entryStorage;
    protected final int subspaceSize;
    @Nullable
    protected final SpiritRiteType riteType;

    protected boolean assembledObjects = false;

    protected boolean isActive = false;
    protected int activeDuration;

    public SubspaceEntryObject(BookEntry entry, int posX, int posY, EntryStorage entryStorage, int subspaceSize) {
        super(entry, posX, posY);
        this.entryStorage = entryStorage;
        this.subspaceSize = subspaceSize;
        this.riteType = entryStorage.getEntries().stream().findAny().map(RiteEntryObject::getRiteTypeFromEntry).flatMap(o -> o).orElse(null);
    }

    @Override
    public boolean hasPriority(AbstractProgressionCodexScreen screen) {
        return isActive;
    }

    @Override
    public void tick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        int centerX = getCenterX();
        int centerY = getCenterY();
        spawnEntryParticles();
        entryParticles.tick();
        if (isActive || activeDuration > 0) {
            spawnSubspaceParticles();
            subspaceParticles.tick();
        }
        if (isActive) {
            int xDiff = centerX - (int) mouseX;
            int yDiff = centerY - (int) mouseY;
            double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
            if (distance > (double) subspaceSize / 2 + 16) {
                screen.playSweetenedSound(MalumSoundEvents.ARCANA_SUBENTRY_CLOSE, 0.75f);
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

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
        entryParticles.render(guiGraphics);
        int posX = getOffsetXPosition();
        int posY = getOffsetYPosition();
        renderGlow(guiGraphics, posX, posY, entry.associatedSpirit);
        var pose = guiGraphics.pose();
        if (riteType != null) {
            renderRiteIcon(riteType, pose, getOffsetXPosition() + 8, getOffsetYPosition() + 8);
        }
        else {
            Set<SpiritLike> spirits = entryStorage.getEntries().stream().map(e -> e.associatedSpirit).filter(Objects::nonNull).collect(Collectors.toSet());
            for (int i = 0; i < 2; i++) {
                var texture = i == 0 ? ICON_LEFT_TEXTURE : ICON_RIGHT_TEXTURE;
                var spirit = spirits.stream().findFirst().orElse(entry.associatedSpirit);
                if (spirit == null) {
                    continue;
                }
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                CodexRenderHelper.renderSpiritIcon(texture, pose, spirit, false, getOffsetXPosition(), getOffsetYPosition(), 0, 32, 32);
                for (int j = 0; j < 4; j++) {
                    float angle = (j / 4f) * 6.28f + ((Minecraft.getInstance().level.getGameTime() + partialTicks) * 0.05f) % 6.28f;
                    float offsetScale = 2.5f;
                    float offsetX = getOffsetXPosition() + (Mth.sin(angle) * offsetScale);
                    float offsetY = getOffsetYPosition() + (Mth.cos(angle) * offsetScale);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 0.15f);
                    CodexRenderHelper.renderSpiritIcon(texture, pose, spirit, j%2==0, offsetX, offsetY, 0, 32, 32);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                }
                spirits.remove(spirit);
            }

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
            } else {
                duration -= partialTicks;
            }
            float delta = Easing.SINE_IN_OUT.ease(Math.min(0.1f + duration / (float) WARMUP_TIME, 1));
            var minecraft = Minecraft.getInstance();
            int scale = (int) minecraft.getWindow().getGuiScale();
            int size = (int) (subspaceSize * delta);
            int offset = size / 2;
            int x = getOffsetXPosition() + width / 2 - offset;
            int y = getOffsetYPosition() + height / 2 - offset;
            int margin = 6;
            pose.pushPose();
            pose.translate(0, 0, 500);
            renderSubspace(guiGraphics, x - margin, y - margin, size + margin * 2, delta);

            GL11.glEnable(GL_SCISSOR_TEST);
            GL11.glScissor(
                    x * scale,
                    minecraft.getWindow().getHeight() - (y + size) * scale,
                    size * scale,
                    size * scale);

            subspaceParticles.render();
            screen.captureLateRendering();
            objects.renderObjects(screen, guiGraphics, xOffset, yOffset, mouseX, mouseY, partialTicks);
            GL11.glDisable(GL_SCISSOR_TEST);
            if (isActive) {
                objects.renderObjectsLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
            }
            screen.doLateRendering();
            pose.popPose();
        } else {
            super.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public boolean tryClick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!assembledObjects) {
            objects.setupEntryObjects(screen, entryStorage);
            assembledObjects = true;
        }
        if (isActive) {
            return objects.click(screen, mouseX, mouseY);
        }
        return super.tryClick(screen, mouseX, mouseY);
    }

    @Override
    public boolean tryRelease(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (isActive) {
            return objects.release(screen, mouseX, mouseY);
        }
        return super.tryRelease(screen, mouseX, mouseY);
    }

    @Override
    public boolean click(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        for (BookObject<AbstractProgressionCodexScreen> object : screen.progressionObjects.getObjects()) {
            if (object instanceof SubspaceEntryObject otherSubspace) {
                if (otherSubspace != this && otherSubspace.isActive) {
                    return false;
                }
            }
        }
        if (!isActive) {
            screen.playSweetenedSound(MalumSoundEvents.ARCANA_SUBENTRY_OPEN, 1.25f);
            isActive = true;
            return true;
        }
        return super.click(screen, mouseX, mouseY);
    }


    public void spawnEntryParticles() {
        var spirit = entry.associatedSpirit;
        if (spirit == null) {
            return;
        }
        var minecraft = Minecraft.getInstance();
        var level = minecraft.level;
        var rand = level.random;
        if (level.getGameTime() % 4 == 0L) {
            for (int i = 0; i < 3; i++) {
                float angle = ((level.getGameTime() * 1.5f + i * 120) / 360 * 6.28f) % 6.28f;
                int lifetime = Easing.SINE_IN_OUT.asWeighedRandom(rand, 100, 140);
                ScreenParticleBuilder.create(MalumScreenParticles.LIGHT_SPEC, entryParticles)
                        .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0f).setEasing(Easing.CUBIC_OUT, Easing.SINE_IN_OUT).build())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.05f, 0.2f), 0).randomSpinOffset(rand).setEasing(Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.3f, 0.9f), 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setColorData(spirit.createColorData().setCoefficient(0.9f))
                        .setLifetime(lifetime)
                        .addRenderActor(p -> {
                            float distance = 7.5f;
                            float ageDelta = (float) p.age / p.lifetime;
                            var updatedAngle = angle + ageDelta * 0.05f;
                            p.x = (getCenterX() + Math.sin(updatedAngle) * distance);
                            p.y = (getCenterY() + Math.cos(updatedAngle) * distance);
                        })
                        .spawn(0, 0);
            }
        }
    }

    public void spawnSubspaceParticles() {
        var spirit = entry.associatedSpirit;
        if (spirit == null) {
            return;
        }
        var minecraft = Minecraft.getInstance();
        var level = minecraft.level;
        var rand = level.random;
        float distance = (subspaceSize - 20) * 0.5f;
        float finalDistance = distance * Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.6f, 1.2f);
        for (int i = 0; i < 4; i++) {
            float angle = ((level.getGameTime() * 8.2f + i * 80) / 320 * 6.28f) % 6.28f;
            int lifetime = Easing.SINE_IN_OUT.asWeighedRandom(rand, 200, 400);
            ScreenParticleBuilder.create(MalumScreenParticles.LIGHT_SPEC, subspaceParticles)
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.4f, 0f).setEasing(Easing.CUBIC_OUT, Easing.SINE_IN_OUT).build())
                    .setSpinData(SpinParticleData.createRandomDirection(rand, Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.05f, 0.2f), 0).randomSpinOffset(rand).setEasing(Easing.SINE_IN_OUT).build())
                    .setScaleData(GenericParticleData.create(Easing.SINE_IN_OUT.asWeighedRandom(rand, 0.5f, 1.5f), 0).setEasing(Easing.EXPO_IN).build())
                    .setColorData(spirit.createColorData().setCoefficient(0.9f))
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


    public static void renderGlow(GuiGraphics graphics, int x, int y, @Nullable SpiritLike spirit) {
        if (spirit == null) {
            return;
        }
        ExtendedShaderInstance shaderInstance = LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(600f);
        shaderInstance.safeGetUniform("Intensity").set(100f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(-1f, 2f, -1f, 2f));

        VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                .setShader(shaderInstance)
                .setColor(spirit.getPrimaryColor())
                .setAlpha(0.3f);

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        renderGlowTexture(graphics, builder, x, y);
        builder.setAlpha(0.1f);
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        shaderInstance.safeGetUniform("Speed").set(800f);
        renderGlowTexture(graphics, builder, x - 1, y);
        renderGlowTexture(graphics, builder, x + 1, y);
        renderGlowTexture(graphics, builder, x, y - 1);
        builder.setColor(spirit.getSecondaryColor());
        renderGlowTexture(graphics, builder, x, y + 1);
        shaderInstance.setUniformDefaults();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    public static void renderSubspace(GuiGraphics graphics, int x, int y, int size, float delta) {
        ExtendedShaderInstance shaderInstance = LodestoneShaders.MANUAL_NINE_SLICE.getShaderInstance();
        shaderInstance.safeGetUniform("YFrequency").set(10f);
        shaderInstance.safeGetUniform("XFrequency").set(10f);
        shaderInstance.safeGetUniform("Speed").set(400f);
        shaderInstance.safeGetUniform("Intensity").set(100f);
        shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(-1f, 2f, -1f, 2f));
        shaderInstance.safeGetUniform("Size").set(3f,3f);

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

    public static void renderSubspaceTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y, int size) {
        builder.setTexture(SUBSPACE_TEXTURE).setPositionWithWidth(x, y, size, size).blit(graphics.pose());
    }

    public static void renderGlowTexture(GuiGraphics graphics, VFXBuilders.ScreenVFXBuilder builder, int x, int y) {
        builder.setTexture(GLOW_TEXTURE).setPositionWithWidth(x, y, 32, 32).blit(graphics.pose());
    }

    public static class SubspaceWidgetSupplier implements PlacedBookEntry.WidgetSupplier, PlacedEntryAcceptor {

        protected final EntryStorage entryStorage = new EntryStorage();

        protected int size = 160;

        public SubspaceWidgetSupplier() {
        }

        public SubspaceWidgetSupplier setSize(int size) {
            this.size = size;
            return this;
        }

        @Override
        public EntryStorage getEntryStorage() {
            return entryStorage;
        }

        @Override
        public ProgressionEntryObject getBookObject(BookEntry entry, int x, int y) {
            return new SubspaceEntryObject(entry, x, y, entryStorage, size);
        }
    }
}