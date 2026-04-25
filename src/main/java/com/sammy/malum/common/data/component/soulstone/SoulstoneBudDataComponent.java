package com.sammy.malum.common.data.component.soulstone;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.*;

import java.util.*;

public record SoulstoneBudDataComponent(List<StoredInSoulstoneMetal> composition, int purity) {

    public static int MAX_PURITY = 100;
    public static final SoulstoneBudDataComponent DEFAULT = new SoulstoneBudDataComponent(Collections.emptyList(), 50);

    public static Codec<SoulstoneBudDataComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StoredInSoulstoneMetal.CODEC.listOf().optionalFieldOf("composition", new ArrayList<>()).forGetter(g -> g.composition),
            Codec.INT.fieldOf("purity").forGetter(g -> g.purity)
    ).apply(instance, SoulstoneBudDataComponent::new));

    public static StreamCodec<ByteBuf, SoulstoneBudDataComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(SoulstoneBudDataComponent.CODEC);

    public boolean hasMetal(StoredInSoulstoneMetal metal) {
        for (StoredInSoulstoneMetal existingMetal : composition) {
            if (existingMetal.equals(metal)) {
                return true;
            }
        }
        return false;
    }

    public SoulstoneBudDataComponent addMetal(StoredInSoulstoneMetal metal) {
        int addedPurity = hasMetal(metal) ? 4 : 10;
        return new SoulstoneBudDataComponent(
                ImmutableList.<StoredInSoulstoneMetal>builder()
                        .addAll(composition)
                        .add(metal)
                        .build(),
                Math.min(purity() + addedPurity, MAX_PURITY)
        );
    }

    public Component getMetalTooltip() {
        var composition = Component.empty();
        var data = new Object2IntLinkedOpenHashMap<StoredInSoulstoneMetal>();
        for (StoredInSoulstoneMetal metal : this.composition) {
            data.addTo(metal, 1);
        }
        for (var entry : data.object2IntEntrySet()) {
            int count = entry.getIntValue();
            var value = entry.getKey();
            if (count > 1) {
                composition.append(Component.literal("" + count).withStyle(ChatFormatting.GOLD));
            }
            composition.append(value.getComponent().withStyle(ChatFormatting.GOLD));
        }
        return Component.translatable(StoredInSoulstoneMetal.METAL_COMPOSITION, composition).withStyle(ChatFormatting.GRAY);
    }

    public Component getPurityTooltip() {
        var purity = Component.literal("0." + this.purity).withStyle(ChatFormatting.GOLD);
        return Component.translatable(StoredInSoulstoneMetal.METAL_PURITY, purity).withStyle(ChatFormatting.GRAY);
    }
}