package com.sammy.malum.registry.common.util;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.common.block.curiosities.poppetry.PoppetPillowBlock;
import com.sammy.malum.registry.common.block.properties.MalumBlockProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;

import java.util.HashMap;
import java.util.List;
import java.util.function.*;

import static com.sammy.malum.registry.common.MalumContent.Materials;
import static com.sammy.malum.registry.common.MalumContent.registerBlock;

public class DyedVariantBundle<T> extends MalumRegistrySet {

    protected final ImmutableMap<DyeColor, T> variants;

    public DyedVariantBundle(String id, BiFunction<String, DyeColor, T> registry) {
        super(id);
        var builder = ImmutableMap.<DyeColor, T>builder();
        for (DyeColor color : DyeColor.values()) {
            var colorName = color.getName();
            var entryId = name(colorName + "_%s");
            T object = registry.apply(entryId, color);
            builder.put(color, object);
        }
        variants = builder.build();
    }

    public ImmutableMap<DyeColor, T> getVariants() {
        return variants;
    }

    public T getVariant(DyeColor color) {
        return variants.get(color);
    }
}