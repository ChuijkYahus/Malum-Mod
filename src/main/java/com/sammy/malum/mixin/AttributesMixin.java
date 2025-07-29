package com.sammy.malum.mixin;

import net.minecraft.world.entity.ai.attributes.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Attributes.class)
public class AttributesMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/RangedAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=attribute.name.generic.armor")), index = 2)
    private static double malum$modifyArmor(double max) {
        if (max == 30.0D) return 1000.0D;
        return max;
    }
}
