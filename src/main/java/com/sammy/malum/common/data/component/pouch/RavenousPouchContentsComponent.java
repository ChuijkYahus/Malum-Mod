package com.sammy.malum.common.data.component.pouch;

import com.mojang.serialization.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import org.apache.commons.lang3.math.*;

import java.util.*;

public class RavenousPouchContentsComponent extends MalumPouchContentsComponent {

    public static final RavenousPouchContentsComponent EMPTY = new RavenousPouchContentsComponent(List.of());
    public static final Codec<RavenousPouchContentsComponent> CODEC = ItemStack.CODEC.listOf().xmap(RavenousPouchContentsComponent::new, RavenousPouchContentsComponent::getItems);

    public static final StreamCodec<RegistryFriendlyByteBuf, RavenousPouchContentsComponent> STREAM_CODEC = ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(RavenousPouchContentsComponent::new, MalumPouchContentsComponent::getItems);

    public RavenousPouchContentsComponent(List<ItemStack> items) {
        super(items, RavenousPouchContentsWeightProcessor.INSTANCE);
    }

    @Override
    public RavenousPouchContentsComponent.Mutable mutable() {
        return new Mutable(this);
    }

    @Override
    public int getStorageSize() {
        return 512;
    }

    public static class RavenousPouchContentsWeightProcessor extends PouchContentsWeightProcessor {

        public static final RavenousPouchContentsWeightProcessor INSTANCE = new RavenousPouchContentsWeightProcessor();

        @Override
        public int getWeightModifier(ItemStack stack) {
            return 8;
        }
    }

    public static final class Mutable extends MalumPouchContentsComponent.Mutable {

        public Mutable(MalumPouchContentsComponent contents) {
            super(contents, RavenousPouchContentsWeightProcessor.INSTANCE);
        }

        @Override
        public RavenousPouchContentsComponent immutable() {
            return new RavenousPouchContentsComponent(items);
        }
    }
}
