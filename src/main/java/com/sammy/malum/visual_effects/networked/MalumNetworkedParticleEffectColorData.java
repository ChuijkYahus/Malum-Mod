package com.sammy.malum.visual_effects.networked;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectColorData;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MalumNetworkedParticleEffectColorData extends NetworkedParticleEffectColorData {

    public static final Codec<MalumNetworkedParticleEffectColorData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ColorParticleData.CODEC.listOf().fieldOf("colors").forGetter(data -> data.colors),
            MalumSpiritType.CODEC.listOf().fieldOf("spirits").forGetter(data -> data.spirits)
    ).apply(instance, MalumNetworkedParticleEffectColorData::new));

    public static final StreamCodec<ByteBuf, MalumNetworkedParticleEffectColorData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

    private final List<MalumSpiritType> spirits;
    public int colorCycleCounter;

    public static MalumNetworkedParticleEffectColorData fromSpiritItems(Collection<ItemStack> spirits) {
        return new MalumNetworkedParticleEffectColorData(Collections.emptyList(), spirits.stream().map(s -> ((SpiritShardItem)s.getItem()).type).collect(Collectors.toList()));
    }

    public static MalumNetworkedParticleEffectColorData fromSpiritIngredients(Collection<SpiritIngredient> malumSpiritTypes) {
        return new MalumNetworkedParticleEffectColorData(Collections.emptyList(), malumSpiritTypes.stream().map(SpiritIngredient::getSpiritType).collect(Collectors.toList()));
    }

    public static MalumNetworkedParticleEffectColorData fromColors(List<ColorParticleData> colors) {
        return new MalumNetworkedParticleEffectColorData(colors, Collections.emptyList());
    }

    public static MalumNetworkedParticleEffectColorData fromColor(ColorParticleData color) {
        return fromColors(List.of(color));
    }

    public MalumNetworkedParticleEffectColorData(List<ColorParticleData> colors, List<MalumSpiritType> spirits) {
        super(colors);
        this.spirits = spirits.isEmpty() ? Collections.emptyList() : spirits;
    }

    public MalumNetworkedParticleEffectColorData(ColorParticleData... colors) {
        this(List.of(colors), Collections.emptyList());
    }

    public MalumNetworkedParticleEffectColorData(MalumSpiritType... spirits) {
        this(Collections.emptyList(), List.of(spirits));
    }

    public boolean isSpiritBased() {
        return colors.isEmpty();
    }

    @Override
    public ColorParticleData getColor() {
        if (!spirits.isEmpty()) {
            return getSpirit().createColorData().build();
        }
        if (colors.size() == 1) {
            return colors.getFirst();
        }
        return colors.get(colorCycleCounter++ % colors.size());
    }

    public MalumSpiritType getSpirit() {
        if (!colors.isEmpty()) {
            throw new IllegalArgumentException("Networked Particle Effect expected Spirit Color Data. Raw Color Data was passed instead, which is not supported.");
        }
        if (spirits.size() == 1) {
            return spirits.getFirst();
        }
        return spirits.get(colorCycleCounter++ % spirits.size());
    }
}