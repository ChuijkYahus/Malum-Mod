package com.sammy.malum.client.screen.codex;

import net.minecraft.resources.*;

import javax.annotation.*;
import java.util.*;

import static com.sammy.malum.MalumMod.malumPath;

public final class WidgetDesign {
    private final WidgetDesignType designType;
    @Nullable
    private final WidgetDesignType.FrameType frame;
    @Nullable
    private final WidgetDesignType.FillingType filling;
    @Nullable
    private final ResourceLocation frameTexture;
    @Nullable
    private final ResourceLocation fillingTexture;

    public WidgetDesign(WidgetDesignType designType, @Nullable WidgetDesignType.FrameType frame,
                        @Nullable WidgetDesignType.FillingType filling, @Nullable ResourceLocation frameTexture,
                        @Nullable ResourceLocation fillingTexture) {
        this.designType = designType;
        this.frame = frame;
        this.filling = filling;
        this.frameTexture = frameTexture;
        this.fillingTexture = fillingTexture;
    }

    public WidgetDesign(WidgetDesignType designType, WidgetDesignType.FrameType frame, WidgetDesignType.FillingType filling) {
        this(designType, frame, filling,
                frame == null ? null : malumPath("textures/gui/book/widgets/" + frame.getId() + "_frame_" + designType.getId() + ".png"),
                filling == null ? null : malumPath("textures/gui/book/widgets/" + filling.getId() + "_filling_" + designType.getId() + ".png"));
    }

    public WidgetDesignType getDesignType() {
        return designType;
    }

    @Nullable
    public WidgetDesignType.FrameType getFrame() {
        return frame;
    }

    @Nullable
    public WidgetDesignType.FillingType getFilling() {
        return filling;
    }

    public Optional<ResourceLocation> getFrameTexture() {
        return Optional.ofNullable(frameTexture);
    }

    public Optional<ResourceLocation> getFillingTexture() {
        return Optional.ofNullable(fillingTexture);
    }
}
