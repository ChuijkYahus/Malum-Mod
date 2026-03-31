package com.sammy.malum.common.block;

import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.inventory.*;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MalumBlockItemStackHandlerBuilder extends LodestoneItemStackBlockHandlerBuilder {

    protected MalumBlockItemStackHandlerBuilder(LodestoneBlockEntity parent, int slotCount) {
        super(parent, slotCount);
    }

    @Override
    public MalumBlockItemStackHandlerBuilder limitItemSize(int allowedItemSize) {
        return (MalumBlockItemStackHandlerBuilder) super.limitItemSize(allowedItemSize);
    }

    @Override
    public MalumBlockItemStackHandlerBuilder onContentsChanged(Runnable contentsChangeBehavior) {
        return (MalumBlockItemStackHandlerBuilder) super.onContentsChanged(contentsChangeBehavior);
    }

    @Override
    public MalumBlockItemStackHandlerBuilder setInputPredicate(Predicate<ItemStack> inputPredicate) {
        return (MalumBlockItemStackHandlerBuilder) super.setInputPredicate(inputPredicate);
    }

    @Override
    public MalumBlockItemStackHandler build() {
        return new MalumBlockItemStackHandler(parent, slotCount, allowedItemSize, inputPredicate,onContentsChanged);
    }

    public MalumBlockItemStackHandlerBuilder onlyAugments() {
        setInputPredicate(p -> p.is(MalumTags.ItemTags.AUGMENTS) && !p.is(MalumTags.ItemTags.CORE_AUGMENTS));
        return this;
    }
    public MalumBlockItemStackHandlerBuilder onlyCoreAugments() {
        setInputPredicate(p -> p.is(MalumTags.ItemTags.CORE_AUGMENTS));
        return this;
    }

    public MalumBlockItemStackHandlerBuilder onlySpirits() {
        setInputPredicate(p -> p.is(MalumTags.ItemTags.SPIRITS));
        return this;
    }
    public MalumBlockItemStackHandlerBuilder noSpirits() {
        setInputPredicate(p -> !p.is(MalumTags.ItemTags.SPIRITS));
        return this;
    }
}
