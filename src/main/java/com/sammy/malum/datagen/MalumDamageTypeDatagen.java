package com.sammy.malum.datagen;

import com.sammy.malum.registry.common.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;

public class MalumDamageTypeDatagen {

    public static void bootstrap(BootstrapContext<DamageType> context) {
        register(context, DamageTypeRegistry.VOODOO);
        register(context, DamageTypeRegistry.VOODOO_PLAYERLESS);

        register(context, DamageTypeRegistry.NITRATE);
        register(context, DamageTypeRegistry.NITRATE_PLAYERLESS);

        register(context, DamageTypeRegistry.VOID);
        register(context, DamageTypeRegistry.KARMIC);
        register(context, DamageTypeRegistry.ROT);

        register(context, DamageTypeRegistry.SCYTHE_MELEE);
        register(context, DamageTypeRegistry.SCYTHE_SWEEP);
        register(context, DamageTypeRegistry.SCYTHE_REBOUND);
        register(context, DamageTypeRegistry.SCYTHE_ASCENSION);
        register(context, DamageTypeRegistry.SCYTHE_COMBO);
        register(context, DamageTypeRegistry.SCYTHE_MAELSTROM);

        register(context, DamageTypeRegistry.HIDDEN_BLADE_PHYSICAL_COUNTER);
        register(context, DamageTypeRegistry.HIDDEN_BLADE_MAGIC_COUNTER);

        register(context, DamageTypeRegistry.MALIGNANT_METAL_COMBO);

        register(context, DamageTypeRegistry.TYRVING);

        register(context, DamageTypeRegistry.SUNDERING_ANCHOR_PHYSICAL_COMBO);
        register(context, DamageTypeRegistry.SUNDERING_ANCHOR_MAGIC_COMBO);

        register(context, DamageTypeRegistry.SOULWASHING_RETALIATION);
        register(context, DamageTypeRegistry.SOULWASHING_PROPAGATION);
    }

    private static void register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key) {
        context.register(key, new DamageType(key.location().getPath(), 0.1f, DamageEffects.HURT));
    }
}