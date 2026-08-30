package com.sammy.malum.datagen.magic.rite;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.datagen.magic.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;
import static com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteEffectTypes.*;
import static com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteTypes.*;

public class SpiritRiteTypeDatagenData {

    public static void init(MalumMagicDatagenProvider provider) {
        create(provider, UNDIRECTED_RITE, UNCHAINED_RITE)
                .specialPattern(ARCANE_SPIRIT, 5)
                .runewoodEffect(UNDIRECTED_RITE_EFFECT)
                .soulwoodEffect(UNCHAINED_RITE_EFFECT);


        create(provider, RITE_OF_HEALING, RITE_OF_NOURISHMENT)
                .lesserPattern(SACRED_SPIRIT)
                .runewoodEffect(HEALING_EFFECT)
                .soulwoodEffect(NOURISHMENT_EFFECT);
        create(provider, RITE_OF_NURTURING, RITE_OF_LUST)
                .greaterPattern(SACRED_SPIRIT)
                .runewoodEffect(NURTURING_EFFECT)
                .soulwoodEffect(LUST_EFFECT);

        create(provider, RITE_OF_HARMING, RITE_OF_EMPOWERMENT)
                .lesserPattern(WICKED_SPIRIT)
                .runewoodEffect(HARMING_EFFECT)
                .soulwoodEffect(EMPOWERMENT_EFFECT);
        create(provider, RITE_OF_CULLING, RITE_OF_RAISING)
                .greaterPattern(WICKED_SPIRIT)
                .runewoodEffect(CULLING_EFFECT)
                .soulwoodEffect(RAISING_EFFECT);

        create(provider, RITE_OF_HOWLING_GALE, RITE_OF_SKY_TETHER)
                .lesserPattern(AERIAL_SPIRIT)
                .runewoodEffect(APPLY_HOWLING_GALE_EFFECT)
                .soulwoodEffect(APPLY_SKY_TETHER_EFFECT);
        create(provider, RITE_OF_GRAVITY, RITE_OF_ASCENSION)
                .greaterPattern(AERIAL_SPIRIT)
                .runewoodEffect(BLOCK_GRAVITY_EFFECT)
                .soulwoodEffect(BLOCK_ASCENSION_EFFECT);

        create(provider, RITE_OF_FLOWING_GRASP, RITE_OF_GOOD_TIDES)
                .lesserPattern(AQUEOUS_SPIRIT)
                .runewoodEffect(APPLY_FLOWING_GRASP_EFFECT)
                .soulwoodEffect(APPLY_GOOD_TIDES_EFFECT);
        create(provider, RITE_OF_SOAKING, RITE_OF_SAPPING)
                .greaterPattern(AQUEOUS_SPIRIT)
                .runewoodEffect(SOAKING_EFFECT)
                .soulwoodEffect(SAPPING_EFFECT);

        create(provider, RITE_OF_STONE_WARD, RITE_OF_OAKEN_MIGHT)
                .lesserPattern(EARTHEN_SPIRIT)
                .runewoodEffect(APPLY_STONE_WARD_EFFECT)
                .soulwoodEffect(APPLY_OAKEN_MIGHT_EFFECT);
        create(provider, RITE_OF_CREATION, RITE_OF_DESTRUCTION)
                .greaterPattern(EARTHEN_SPIRIT)
                .runewoodEffect(CREATION_EFFECT)
                .soulwoodEffect(DESTRUCTION_EFFECT);

        create(provider, RITE_OF_BURNING_FERVOR, RITE_OF_FIERY_EMBRACE)
                .lesserPattern(INFERNAL_SPIRIT)
                .runewoodEffect(APPLY_BURNING_FERVOR_EFFECT)
                .soulwoodEffect(APPLY_FIERY_EMBRACE_EFFECT);
        create(provider, RITE_OF_SMELTING, RITE_OF_QUICKENING)
                .greaterPattern(INFERNAL_SPIRIT)
                .runewoodEffect(SMELTING_EFFECT)
                .soulwoodEffect(QUICKENING_EFFECT);
    }

    public static DatagenRiteDefinition create(MalumMagicDatagenProvider provider, ResourceLocation runewoodKey, ResourceLocation soulwoodKey) {
        return new DatagenRiteDefinition(provider, runewoodKey, soulwoodKey);
    }

    public static class DatagenRiteDefinition {

        protected final SpiritRiteJsonBody runewood;
        protected final SpiritRiteJsonBody soulwood;

        public DatagenRiteDefinition(MalumMagicDatagenProvider provider, ResourceLocation runewoodKey, ResourceLocation soulwoodKey) {
            this.runewood = provider.addData(runewoodKey, SpiritRiteJsonBody::new);
            this.soulwood = provider.addData(soulwoodKey, SpiritRiteJsonBody::new).setSoulwood();
        }

        public DatagenRiteDefinition runewoodEffect(Holder<SpiritRiteEffect> effect) {
            //TODO: This should just add another JsonBody basically
            runewood.setEffect(effect);
            return this;
        }

        public DatagenRiteDefinition soulwoodEffect(Holder<SpiritRiteEffect> effect) {
            //TODO: This should just add another JsonBody basically
            soulwood.setEffect(effect);
            return this;
        }

        private DatagenRiteDefinition lesserPattern(SpiritLike spirit) {
            return pattern(spirit, false);
        }

        private DatagenRiteDefinition greaterPattern(SpiritLike spirit) {
            return pattern(spirit, true);
        }

        private DatagenRiteDefinition pattern(SpiritLike spirit, boolean major) {
            List<Holder<SpiritArcanaType>> pattern = new ArrayList<>();
            if (major) {
                pattern.add(ELDRITCH_SPIRIT);
            }
            pattern.add(ARCANE_SPIRIT);
            pattern.add(spirit.getHolder());
            pattern.add(spirit.getHolder());
            runewood.setPattern(pattern);
            soulwood.setPattern(pattern);
            return this;
        }

        private DatagenRiteDefinition specialPattern(Holder<SpiritArcanaType> spirit, int count) {
            List<Holder<SpiritArcanaType>> pattern = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                pattern.add(spirit);
            }
            runewood.setPattern(pattern);
            soulwood.setPattern(pattern);
            return this;
        }
    }
}