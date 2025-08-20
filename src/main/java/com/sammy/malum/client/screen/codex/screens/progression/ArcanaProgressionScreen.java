package com.sammy.malum.client.screen.codex.screens.progression;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.entries.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.client.screen.codex.pages.recipe.vanilla.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.*;
import net.minecraft.resources.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.item.MalumItems.*;

public class ArcanaProgressionScreen extends AbstractProgressionCodexScreen {

    public static final ResourceLocation BACKGROUND_TEXTURE = malumPath("textures/gui/book/background.png");

    public static final ProgressionScreenHolder<ArcanaProgressionScreen> SCREEN = new ProgressionScreenHolder<>(ArcanaProgressionScreen::new, MalumSoundEvents.ARCANA_TRANSITION_NORMAL);

    protected ArcanaProgressionScreen() {
        super(MalumSoundEvents.ARCANA_SWEETENER_NORMAL, 1024, 2560);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        renderBackground(poseStack, BACKGROUND_TEXTURE, 0.2f, 0.4f);
    }

    @Override
    public void setupEntries() {
        addEntry("chronicles_of_the_void", 0, -1, b -> b
                .setWidgetSupplier((e, x, y) -> new ScreenOpenerObject(e, x, y, VoidProgressionScreen.SCREEN, malumPath("textures/gui/book/icons/void_button.png"), 20, 20))
                .configureEntry(w -> w.setStyle(BookWidgetStyle.DARK_GRAND_RUNEWOOD).setHeadlineFormatting(ChatFormatting.LIGHT_PURPLE).setCondition(AbstractProgressionCodexScreen::isVoidTouched))
        );

        IntroductionEntries.setupEntries(this);
        ArtificeEntries.setupEntries(this);
        AugmentationEntries.setupEntries(this);
        TinkeringEntries.setupEntries(this);
        RuneWorkingEntries.setupEntries(this);
        GeasEntries.setupEntries(this);
        TotemMagicEntries.setupEntries(this);
        MiscellaneousKnowledgeEntries.setupEntries(this);

        addEntry("ritual_magic", 0, 26, b -> b
                .configureEntry(w -> w.setIcon(RITUAL_PLINTH).setStyle(BookWidgetStyle.GILDED_SOULWOOD))
                .addPage(new HeadlineTextPage("ritual_magic", "ritual_magic.1"))
        );
//        RitualEntries.setupEntries(ENTRIES);

        addEntry("mirror_magic", 10, 15, b -> b
                .configureEntry(w -> w.setIcon(CONVOLUTED_LENS).setStyle(BookWidgetStyle.GILDED_SOULWOOD))
                .addPage(new HeadlineTextPage("mirror_magic", "mirror_magic.1"))
                .addPage(new TextPage("mirror_magic.2"))
        );

        addEntry("voodoo_magic", -10, 15, b -> b
                .configureEntry(w -> w.setIcon(POPPET).setStyle(BookWidgetStyle.GILDED_SOULWOOD))
                .addPage(new HeadlineTextPage("voodoo_magic", "voodoo_magic.1"))
                .addPage(new TextPage("voodoo_magic.2"))
        );

        addEntry("the_device", 0, -10, b -> b
            .setWidgetSupplier(VanishingEntryObject::new)
            .configureEntry(w -> w.setIcon(THE_DEVICE).setStyle(BookWidgetStyle.WITHERED))
            .disableTooltip()
                .addPage(new HeadlineTextPage("the_device", "the_device"))
            .addPage(new CraftingPage(THE_DEVICE.get(),
                TWISTED_ROCK.get(), TAINTED_ROCK.get(), TWISTED_ROCK.get(),
                TAINTED_ROCK.get(), TWISTED_ROCK.get(), TAINTED_ROCK.get(),
                TWISTED_ROCK.get(), TAINTED_ROCK.get(), TWISTED_ROCK.get()))
        );
    }
}
