package com.sammy.malum.datagen;

import com.sammy.malum.registry.common.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;

public class MalumDamageTypeDatagen {

    public static void bootstrap(BootstrapContext<DamageType> context) {
        register(context, MalumDataTypes.VOODOO);
        register(context, MalumDataTypes.VOODOO_PLAYERLESS);

        register(context, MalumDataTypes.NITRATE);
        register(context, MalumDataTypes.NITRATE_PLAYERLESS);

        register(context, MalumDataTypes.VOID);
        register(context, MalumDataTypes.KARMIC);
        register(context, MalumDataTypes.ROT);


        register(context, MalumDataTypes.SCYTHE_MELEE);
        register(context, MalumDataTypes.SCYTHE_SWEEP);
        register(context, MalumDataTypes.SCYTHE_REBOUND);
        register(context, MalumDataTypes.SCYTHE_ASCENSION);
        register(context, MalumDataTypes.SCYTHE_COMBO);
        register(context, MalumDataTypes.SCYTHE_MAELSTROM);

        register(context, MalumDataTypes.HIDDEN_BLADE_PHYSICAL_COUNTER);
        register(context, MalumDataTypes.HIDDEN_BLADE_MAGIC_COUNTER);

        register(context, MalumDataTypes.MALIGNANT_METAL_COMBO);

        register(context, MalumDataTypes.TYRVING);

        register(context, MalumDataTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO);
        register(context, MalumDataTypes.SUNDERING_ANCHOR_MAGIC_COMBO);

        register(context, MalumDataTypes.WARLOCK_SPIRIT_IMPACT);
        register(context, MalumDataTypes.BERSERKER_SPIRIT_IMPACT);

        register(context, MalumDataTypes.INVERTED_HEART_RETALIATION);
        register(context, MalumDataTypes.INVERTED_HEART_PROPAGATION);
    }

    private static void register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key) {
        context.register(key, new DamageType(key.location().getPath(), 0.1f, DamageEffects.HURT));
    }
}