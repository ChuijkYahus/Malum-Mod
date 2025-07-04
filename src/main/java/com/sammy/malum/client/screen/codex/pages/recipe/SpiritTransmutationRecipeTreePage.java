package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.client.screen.codex.screens.EntryScreen;
import com.sammy.malum.registry.client.MalumScreenParticles;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.screenparticle.ScreenParticleHandler;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.ArrayList;
import java.util.List;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class SpiritTransmutationRecipeTreePage extends BookPage {
    private static final Component BASE = Component.translatable("malum.gui.book.entry.page.info.unchained_transmutation_tree");

    private static final ScreenParticleHolder TRANSMUTATION_PARTICLES = new ScreenParticleHolder();

    private final Component headline;
    private final List<Ingredient> itemTree = new ArrayList<>();

    public SpiritTransmutationRecipeTreePage(String headline, Item start) {
        super(MalumMod.malumPath("textures/gui/book/pages/transmutation_recipe_tree_page.png"));
        this.headline = Component.translatable(BookPage.HEADLINE + "." + headline);

        Level level = Minecraft.getInstance().level;
        if (level != null) {

            var recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.SPIRIT_TRANSMUTATION.get(), new SingleRecipeInput(start.getDefaultInstance()));
            while (true) {
                if (recipe == null) {
                    itemTree.add(Ingredient.of(MalumItems.BLIGHTED_EARTH.get()));
                    break;
                }
                itemTree.add(recipe.ingredient);
                ItemStack output = recipe.output;
                recipe = LodestoneRecipeType.getRecipe(level, MalumRecipeTypes.SPIRIT_TRANSMUTATION.get(), new SingleRecipeInput(output));
            }
        }
    }

    @Override
    public boolean isValid() {
        return !itemTree.isEmpty();
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        renderHeadline(guiGraphics, headline, left, top);
        if (!isRepeat) {
            if (ScreenParticleHandler.canSpawnParticles) {
                TRANSMUTATION_PARTICLES.tick();
            }
            ScreenParticleHandler.renderParticles(TRANSMUTATION_PARTICLES);
        }
        renderIngredient(screen, guiGraphics, itemTree.getFirst(), left + 63, top + 38, mouseX, mouseY);
        renderIngredient(screen, guiGraphics, itemTree.getLast(), left + 63, top + 142, mouseX, mouseY);

        int leftStart = left + 73 - (itemTree.size())*10;
        for (int i = 1; i < itemTree.size()-1; i++) {
            renderIngredient(screen, guiGraphics, itemTree.get(i), leftStart+i*20, top + 90, mouseX, mouseY);
        }
        screen.renderLater(() -> {
            if (screen.isHovering(mouseX, mouseY, left + 62, top + 60, 18, 18)) {
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, wrapComponent(BASE, 180), mouseX, mouseY);
            }
        });

        int particlesX = left + 25;
        int particlesY = top + 98;
        if (ScreenParticleHandler.canSpawnParticles) {
            var level = Minecraft.getInstance().level;
            RandomSource rand = level.random;
            long time = level.getGameTime();
            for (int i = 0; i < 36; i++) {
                int yOffsetScale = 4 + Mth.floor(i/4f);
                float scale = RandomHelper.randomBetween(rand, 0.6f, 0.9f);
                float spin = RandomHelper.randomBetween(rand, 0.2f, 0.4f);
                float xTime = ((time + i * 33) % 240) / 240f;
                float yTime = ((time + i * 27) % 100f) / 100f;
                final double xOffset = 92 * xTime;
                final double yOffset = Math.sin(yTime * 6.28f) * yOffsetScale;
                ScreenParticleBuilder.create(MalumScreenParticles.LIGHT_SPEC, TRANSMUTATION_PARTICLES)
                        .setTransparencyData(GenericParticleData.create(0.2f, 0.4f, 0f).build())
                        .setSpinData(SpinParticleData.create(spin).build())
                        .setScaleData(GenericParticleData.create(0, scale, 0).build())
                        .setColorData(MalumSpiritTypes.ARCANE_SPIRIT.createColorData().setCoefficient(0.75f).build())
                        .setLifetime(i % 2 == 0 ? 20 : 40)
                        .setLifeDelay(i % 3 == 0 ? 0 : 4)
                        .spawn(particlesX + xOffset, particlesY + yOffset);
            }
        }
    }
}
