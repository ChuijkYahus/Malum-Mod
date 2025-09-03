package com.sammy.malum.client.screen.codex;

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
            new WidgetDesignType("grand", 40, 40);

    public static final WidgetDesignType EMPTY =
            new WidgetDesignType("empty");

    private final String id;
    private final int textureWidth;
    private final int textureHeight;
    private final int itemXOffset;
    private final int itemYOffset;

    private WidgetDesignType(String id) {
        this(id, 32, 32, 8, 8);
    }

    private WidgetDesignType(String id, int textureWidth, int textureHeight) {
        this(id, textureWidth, textureHeight, 8, 8);
    }

    private WidgetDesignType(String id, int textureWidth, int textureHeight, int itemXOffset, int itemYOffset) {
        this.id = id;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.itemXOffset = itemXOffset;
        this.itemYOffset = itemYOffset;
    }

    public String getId() {
        return id;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public int getItemXOffset() {
        return itemXOffset;
    }

    public int getItemYOffset() {
        return itemYOffset;
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