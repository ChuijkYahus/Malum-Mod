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
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.*;
import team.lodestar.lodestone.modules.rendering.handlers.ModelHandler;

import java.util.function.Function;
import java.util.function.Supplier;

public class MalumModels {

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
}