package com.sammy.malum.common.category;

import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumAlchemyAndMetallicsTab extends AbstractMalumCreativeTab {

        public static final String BASIC_ALCHEMICAL_FOCI = "basic_alchemical_foci";
        public static final String METALLICS = "metallics";

        public MalumAlchemyAndMetallicsTab(Builder builder) {
                super(builder);
        }

        @Override
        public void buildCategories() {
                createCategory(BASIC_ALCHEMICAL_FOCI)
                        .addItems(AlchemyAndMetallics.ALCHEMICAL_IMPETUS, AlchemyAndMetallics.FRACTURED_ALCHEMICAL_IMPETUS)
                        .addItems(AlchemyAndMetallics.ZEPHYR_IMPETUS, AlchemyAndMetallics.FRACTURED_ZEPHYR_IMPETUS)
                        .addItems(AlchemyAndMetallics.IFRIT_IMPETUS, AlchemyAndMetallics.FRACTURED_IFRIT_IMPETUS)
                        .bake();

                createCategory(METALLICS)
                        .addItems(AlchemyAndMetallics.IRON_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.COPPER_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.GOLD_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.ZINC_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.LEAD_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.SILVER_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.ALUMINIUM_METALLICS::addToCreativeTab)
                        .addItems(AlchemyAndMetallics.NICKEL_METALLICS::addToCreativeTab)
                        .bake();
        }
}