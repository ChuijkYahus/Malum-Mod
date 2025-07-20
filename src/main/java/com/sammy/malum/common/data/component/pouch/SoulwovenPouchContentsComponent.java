package com.sammy.malum.common.data.component.pouch;

import com.mojang.serialization.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import org.apache.commons.lang3.math.*;

import java.util.*;

public class SoulwovenPouchContentsComponent extends MalumPouchContentsComponent {

    public static final SoulwovenPouchContentsComponent EMPTY = new SoulwovenPouchContentsComponent(List.of());
    public static final Codec<SoulwovenPouchContentsComponent> CODEC = ItemStack.CODEC.listOf().xmap(SoulwovenPouchContentsComponent::new, SoulwovenPouchContentsComponent::getItems);

    public static final StreamCodec<RegistryFriendlyByteBuf, SoulwovenPouchContentsComponent> STREAM_CODEC = ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(SoulwovenPouchContentsComponent::new, MalumPouchContentsComponent::getItems);

    public SoulwovenPouchContentsComponent(List<ItemStack> items) {
        super(items, SoulwovenPouchContentsWeightProcessor.INSTANCE);
    }

    @Override
    public SoulwovenPouchContentsComponent.Mutable mutable() {
        return new Mutable(this);
    }

    @Override
    public int getStorageSize() {
        return 512;
    }

    public static class SoulwovenPouchContentsWeightProcessor extends PouchContentsWeightProcessor {

        public static final SoulwovenPouchContentsWeightProcessor INSTANCE = new SoulwovenPouchContentsWeightProcessor();

        @Override
        public int getWeightModifier(ItemStack stack) {
            return stack.is(MalumTags.ItemTags.SOULWOVEN_POUCH_EFFICIENT) ? 8 : 1;
        }
    }

    public static final class Mutable extends MalumPouchContentsComponent.Mutable {

        public Mutable(MalumPouchContentsComponent contents) {
            super(contents, SoulwovenPouchContentsWeightProcessor.INSTANCE);
        }

        @Override
        public SoulwovenPouchContentsComponent immutable() {
            return new SoulwovenPouchContentsComponent(items);
        }
    }
}
