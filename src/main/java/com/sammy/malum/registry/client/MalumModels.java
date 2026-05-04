package com.sammy.malum.registry.client;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.model.*;
import com.sammy.malum.client.model.cosmetic.pride.PridewearArmorModel;
import com.sammy.malum.client.model.cosmetic.pride.SlimPridewearArmorModel;

import com.sammy.malum.client.model.mob.altar.AltarModel;
import com.sammy.malum.client.model.mob.believer.BelieverModel;
import com.sammy.malum.client.model.mob.cardinal.CardinalModel;
import com.sammy.malum.client.model.mob.cherub.CherubModel;
import com.sammy.malum.client.model.mob.evangelist.EvangelistModel;
import com.sammy.malum.client.renderer.item.WandItemRenderer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.*;
import team.lodestar.lodestone.modules.rendering.handlers.ModelHandler;
import team.lodestar.lodestone.systems.model.IRenderableModel;
import team.lodestar.lodestone.systems.model.obj.ObjModel;

import java.util.function.Function;
import java.util.function.Supplier;

public class MalumModels {

//            public static final ObjModel WAND_PARTS = (ObjModel) ModelHandler.register(MalumMod.malumPath("models/wand/wand_parts.obj"));

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AltarModel.LAYER, AltarModel::createBodyLayer);
        event.registerLayerDefinition(BelieverModel.LAYER, BelieverModel::createBodyLayer);
        event.registerLayerDefinition(CherubModel.LAYER, CherubModel::createBodyLayer);
        event.registerLayerDefinition(CardinalModel.LAYER, CardinalModel::createBodyLayer);
        event.registerLayerDefinition(EvangelistModel.LAYER, EvangelistModel::createBodyLayer);

        SoulHunterArmorModel.MODEL.register(event);
        SoulStainedSteelArmorModel.MODEL.register(event);
        MalignantStrongholdArmorModel.MODEL.register(event);

        PridewearArmorModel.MODEL.register(event);
        SlimPridewearArmorModel.MODEL.register(event);
    }

    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        SoulHunterArmorModel.MODEL.bake(event);
        SoulStainedSteelArmorModel.MODEL.bake(event);
        MalignantStrongholdArmorModel.MODEL.bake(event);

        PridewearArmorModel.MODEL.bake(event);
        SlimPridewearArmorModel.MODEL.bake(event);
    }

    public static class ModelHolder<T extends Model> {
        private final ModelLayerLocation layer;
        private final Function<ModelPart, T> modelBuilder;
        private final Supplier<LayerDefinition> definitionBuilder;
        private T model;

        public ModelHolder(String id, Function<ModelPart, T> modelBuilder, Supplier<LayerDefinition> definitionBuilder) {
            this(MalumMod.malumPath(id), modelBuilder, definitionBuilder);
        }

        public ModelHolder(ResourceLocation model, Function<ModelPart, T> modelBuilder, Supplier<LayerDefinition> definitionBuilder) {
            this(new ModelLayerLocation(model, "main"), modelBuilder, definitionBuilder);
        }

        public ModelHolder(ModelLayerLocation layer, Function<ModelPart, T> modelBuilder, Supplier<LayerDefinition> definitionBuilder) {
            this.layer = layer;
            this.modelBuilder = modelBuilder;
            this.definitionBuilder = definitionBuilder;
        }

        public void bake(EntityRenderersEvent.AddLayers event) {
            model = modelBuilder.apply(event.getEntityModels().bakeLayer(layer));
        }

        public void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(layer, definitionBuilder);
        }

        public T getModel() {
            return model;
        }
    }
}