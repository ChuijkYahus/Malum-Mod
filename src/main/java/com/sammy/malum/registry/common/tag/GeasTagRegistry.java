package com.sammy.malum.registry.common.tag;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;

public class GeasTagRegistry {

    public static final TagKey<GeasEffectType> IS_BOND = malumTag("is_bond");
    public static final TagKey<GeasEffectType> IS_OATH = malumTag("is_oath");
    public static final TagKey<GeasEffectType> IS_AUTHORITY = malumTag("is_authority");

    public static final TagKey<GeasEffectType> HIDDEN_UNTIL_BLACK_CRYSTAL = malumTag("hidden_geas/black_crystal");

    private static TagKey<GeasEffectType> modTag(String path) {
        return TagKey.create(MalumGeasEffectTypeRegistry.GEAS_TYPES_KEY, ResourceLocation.parse(path));
    }

    private static TagKey<GeasEffectType> malumTag(String path) {
        return TagKey.create(MalumGeasEffectTypeRegistry.GEAS_TYPES_KEY, MalumMod.malumPath(path));
    }

    private static TagKey<GeasEffectType> commonTag(String name) {
        return TagKey.create(MalumGeasEffectTypeRegistry.GEAS_TYPES_KEY, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
