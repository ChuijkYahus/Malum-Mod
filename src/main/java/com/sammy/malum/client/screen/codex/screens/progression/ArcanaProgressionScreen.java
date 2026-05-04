package com.sammy.malum.client.screen.codex.screens.progression;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.entries.*;
import com.sammy.malum.client.screen.codex.objects.progression.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.*;
import net.minecraft.resources.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.*;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;

public class ArcanaProgressionScreen extends AbstractProgressionCodexScreen {

    public static final ResourceLocation BACKGROUND_TEXTURE = malumPath("textures/gui/book/background.png");

    public static final ProgressionScreenHolder<ArcanaProgressionScreen> SCREEN = new ProgressionScreenHolder<>(ArcanaProgressionScreen::new, MalumSoundEvents.ARCANA_TRANSITION_NORMAL);

    protected ArcanaProgressionScreen() {
        super(MalumSoundEvents.ARCANA_SWEETENER_NORMAL, 1024, 2560);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        renderBackground(poseStack, BACKGROUND_TEXTURE, 0.1f, 0.4f);
    }

    @Override
    public void setupEntries() {
        addEntry("chronicles_of_the_void", 0, -1)
                .setWidgetSupplier((e, x, y) -> new ScreenOpenerObject(e, x, y, VoidProgressionScreen.SCREEN, malumPath("textures/gui/book/icons/void_button.png"), 20, 20))
                .configureWidget(w -> w.setDesign(GRAND, RUNEWOOD, DARK).setCondition(AbstractProgressionCodexScreen::isVoidTouched))
                .withTitleStyle(s -> s.withColor(ChatFormatting.LIGHT_PURPLE));

        IntroductionEntries.setupEntries(this);
//        ArtificeEntries.setupEntries(this);
//        AugmentationEntries.setupEntries(this);
//        TinkeringEntries.setupEntries(this);
//        RuneWorkingEntries.setupEntries(this);
//        GeasEntries.setupEntries(this);
//        TotemMagicEntries.setupEntries(this);
//        MiscellaneousKnowledgeEntries.setupEntries(this);

//        addEntry("ritual_magic", 0, 24, b -> b
//                .configureWidget(w -> w.setIcon(RITUAL_PLINTH).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("ritual_magic"))
//        );
//        RitualEntries.setupEntries(ENTRIES);

//        addEntry("mirror_magic", 6, 17, b -> b
//                .configureWidget(w -> w.setIcon(CONVOLUTED_LENS).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("mirror_magic"))
//                .addPage(new TextPage("mirror_magic.2"))
//        );
//
//        addEntry("voodoo_magic", -6, 17, b -> b
//                .configureWidget(w -> w.setIcon(POPPET).setDesign(GILDED, SOULWOOD, PAPER))
//                .addPage(new HeadlineTextPage("voodoo_magic"))
//                .addPage(new TextPage("voodoo_magic.2"))
//        );
//
//        addEntry("the_device", 0, -10, b -> b
//                .setWidgetSupplier(VanishingEntryObject::new)
//                .configureWidget(w -> w.setIcon(THE_DEVICE).setDesign(DEFAULT, WITHERED, DARK))
//                .disableTooltip()
//                .addPage(new HeadlineTextPage("the_device"))
//                .addPage(new CraftingPage(THE_DEVICE.get(),
//                        TWISTED_ROCK.get(), TAINTED_ROCK.get(), TWISTED_ROCK.get(),
//                        TAINTED_ROCK.get(), TWISTED_ROCK.get(), TAINTED_ROCK.get(),
//                        TWISTED_ROCK.get(), TAINTED_ROCK.get(), TWISTED_ROCK.get()))
//        );
    }
}