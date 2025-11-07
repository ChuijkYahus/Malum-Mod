package com.sammy.malum.client.screen.codex;

import com.sammy.malum.*;
import net.minecraft.resources.*;

import javax.annotation.*;
import java.util.function.Consumer;

public final class WidgetDesignType {

    public enum FrameType {
        RUNEWOOD("runewood"),
        SOULWOOD("soulwood"),
        WITHERED("withered");
        private final String id;

        FrameType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public enum FillingType {
        PAPER("paper"),
        DARK("dark");
        private final String id;

        FillingType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static final WidgetDesignType DEFAULT =
            new WidgetDesignType("default");

    public static final WidgetDesignType GILDED =
            new WidgetDesignType("gilded");

    public static final WidgetDesignType SUBENTRY =
            new WidgetDesignType("subentry");

    public static final WidgetDesignType TOTEMIC =
            new WidgetDesignType("totemic");

    public static final WidgetDesignType SMALL =
            new WidgetDesignType("small");

    public static final WidgetDesignType GRAND =
            new WidgetDesignType("grand");

    public static final WidgetDesignType EMPTY =
            new WidgetDesignType("empty");

    private final String id;
    private final ResourceLocation glow;
    private final ResourceLocation outline;

    private WidgetDesignType(String id) {
        this.id = id;
        this.glow = MalumMod.malumPath("textures/gui/book/widgets/outline/glow_" + id + ".png");
        this.outline = MalumMod.malumPath("textures/gui/book/widgets/outline/outline_" + id + ".png");
    }

    public String getId() {
        return id;
    }

    public ResourceLocation getGlowTexture() {
        return glow;
    }

    public ResourceLocation getOutlineTexture() {
        return outline;
    }

    public WidgetDesign createDesign(@Nullable FrameType frame, @Nullable FillingType filling) {
        return createDesign(b -> b.withFrame(frame).withFilling(filling));
    }

    public WidgetDesign createDesign(Consumer<WidgetDesignBuilder> modifier) {
        WidgetDesignBuilder builder = new WidgetDesignBuilder(this);
        modifier.accept(builder);
        return builder.build();
    }

    public static class WidgetDesignBuilder {
        private final WidgetDesignType design;
        private FrameType frame = FrameType.RUNEWOOD;
        private FillingType filling = FillingType.PAPER;

        public WidgetDesignBuilder(WidgetDesignType design) {
            this.design = design;
        }

        public WidgetDesignBuilder withFrame(FrameType frame) {
            this.frame = frame;
            return this;
        }

        public WidgetDesignBuilder withFilling(FillingType filling) {
            this.filling = filling;
            return this;
        }

        public WidgetDesign build() {
            return new WidgetDesign(design, frame, filling);
        }
    }
}