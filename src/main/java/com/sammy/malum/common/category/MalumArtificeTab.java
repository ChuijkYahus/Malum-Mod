package com.sammy.malum.common.category;

import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumArtificeTab extends AbstractMalumCreativeTab {

        public static final String ARTIFICE_CATEGORY = "arcane_artifice";
        public static final String BASIC_ALCHEMICAL_FOCI = "basic_alchemical_foci";
        public static final String METALLICS = "metallics";

        public MalumArtificeTab(Builder builder) {
                super(builder);
        }

        public void buildCategories() {
                createCategory(ARTIFICE_CATEGORY)
                        .addItems(
                                Artifice.TUNING_FORK,
                                Artifice.SPIRIT_CRUCIBLE,
                                Artifice.SPIRIT_CATALYZER,
                                Artifice.REPAIR_PYLON
                        ).nextLine()
                        .addItems(
                                Artifice.ARTIFICERS_CLAW,
                                Artifice.WAVECHARGER,
                                Artifice.WAVEBANKER,
                                Artifice.WAVEMAKER,
                                Artifice.WAVEBREAKER,
                                Artifice.GUST_IGNITER,
                                Artifice.WIND_TUNNEL
                        ).nextLine()
                        .addItems(
                                Artifice.MENDING_DIFFUSER,
                                Artifice.IMPURITY_STABILIZER,
                                Artifice.SHIELDING_APPARATUS,
                                Artifice.WARPING_ENGINE,
                                Artifice.ACCELERATING_INLAY,
                                Artifice.PRISMATIC_FOCUS_LENS,
                                Artifice.BLAZING_DIODE,
                                Artifice.INTRICATE_ASSEMBLY
                        ).nextLine()
                        .addItems(
                                Artifice.SYMPATHY_DRIVE,
                                Artifice.SUSPICIOUS_DEVICE,
                                Artifice.CAUSTIC_CATALYST,
                                Artifice.RESONANCE_TUNER,
                                Artifice.STELLAR_MECHANISM
                        )
                        .bake();
                createCategory(BASIC_ALCHEMICAL_FOCI)
                        .addItems(Artifice.ALCHEMICAL_IMPETUS, Artifice.FRACTURED_ALCHEMICAL_IMPETUS)
                        .addItems(Artifice.ZEPHYR_IMPETUS, Artifice.FRACTURED_ZEPHYR_IMPETUS)
                        .addItems(Artifice.IFRIT_IMPETUS, Artifice.FRACTURED_IFRIT_IMPETUS)
                        .bake();

                createCategory(METALLICS)
                        .addItems(Artifice.IRON_METALLICS::addToCreativeTab)
                        .addItems(Artifice.COPPER_METALLICS::addToCreativeTab)
                        .addItems(Artifice.GOLD_METALLICS::addToCreativeTab)
                        .addItems(Artifice.ZINC_METALLICS::addToCreativeTab)
                        .addItems(Artifice.LEAD_METALLICS::addToCreativeTab)
                        .addItems(Artifice.SILVER_METALLICS::addToCreativeTab)
                        .addItems(Artifice.ALUMINIUM_METALLICS::addToCreativeTab)
                        .addItems(Artifice.NICKEL_METALLICS::addToCreativeTab)
                        .bake();
        }
}