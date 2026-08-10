package com.sammy.malum.datagen.spiritRite;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SpiritRiteDatagen implements DataProvider {

    private final PackOutput output;

    public SpiritRiteDatagen(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        add(
                futures,
                "destruction_effect",
                SpiritRiteBuilder.of(
                        List.of(
                                ResourceLocation.fromNamespaceAndPath("malum", "eldritch"),
                                ResourceLocation.fromNamespaceAndPath("malum", "arcane"),
                                ResourceLocation.fromNamespaceAndPath("malum", "earthen"),
                                ResourceLocation.fromNamespaceAndPath("malum", "earthen")
                        ),
                        true,
                        ResourceLocation.fromNamespaceAndPath("malum", "destruction_effect")
                ),
                cache
        );

        return CompletableFuture.allOf(
                futures.toArray(CompletableFuture[]::new)
        );
    }

    private void add(List<CompletableFuture<?>> futures, String name, SpiritRiteBuilder builder, CachedOutput cache) {

        Path path = output
                .getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve("malum/spirit_rites/" + name + ".json");

        System.out.println("Generating spirit rite: " + path);

        JsonObject json = builder.toJson();

        futures.add(
                DataProvider.saveStable(
                        cache,
                        json,
                        path
                )
        );
    }

    @Override
    public String getName() {
        return "Spirit Rite Definitions";
    }
}