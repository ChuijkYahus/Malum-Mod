package com.sammy.malum.common.block;

import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandlerBuilder;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MalumBlockItemStackHandlerBuilder extends LodestoneItemStackHandlerBuilder {

    protected final LodestoneBlockEntity parent;
    protected MalumBlockItemStackHandlerBuilder(LodestoneBlockEntity parent, int slotCount) {
        super(slotCount);
        this.parent = parent;
    }

    @Override
    public MalumBlockItemStackHandlerBuilder setInputPredicate(Predicate<ItemStack> inputPredicate) {
        super.setInputPredicate(inputPredicate);
        return this;
    }

    @Override
    public MalumBlockItemStackHandlerBuilder onContentsChanged(Runnable contentsChangeBehavior) {
        super.onContentsChanged(contentsChangeBehavior);
        return this;
    }

    public MalumBlockItemStackHandlerBuilder onContentsChanged(Consumer<Level> contentsChangeBehavior) {
        super.onContentsChanged(() -> contentsChangeBehavior.accept(parent.getLevel()));
        return this;
    }

    @Override
    public MalumBlockItemStackHandler build() {
        return (MalumBlockItemStackHandler) build(((slotCount, allowedItemSize, inputPredicate, onContentsChanged) ->
                new MalumBlockItemStackHandler(parent, slotCount, allowedItemSize, inputPredicate, onContentsChanged)));
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
