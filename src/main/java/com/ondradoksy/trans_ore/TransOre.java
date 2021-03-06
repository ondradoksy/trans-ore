package com.ondradoksy.trans_ore;

import com.ondradoksy.trans_ore.block.TransOreBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class TransOre implements ModInitializer {
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger("trans_ore");
    // Blocks
    public static final TransOreBlock TRANS_ORE_BLOCK = new TransOreBlock(FabricBlockSettings.of(Material.STONE).strength(100.0f, 1.0f).requiresTool());
    // Items
    public static final Item TRANS_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    // Configured Features
    private static final ConfiguredFeature<?, ?> OVERWORLD_TRANS_ORE_CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(
            OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
            TRANS_ORE_BLOCK.getDefaultState(),
            9));
    // Placed Features
    private static final PlacedFeature OVERWORLD_TRANS_ORE_PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(OVERWORLD_TRANS_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(20),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())
            ));

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Trans Ore");
        // Register blocks
        Registry.register(Registry.BLOCK, new Identifier("trans_ore", "trans_ore_block"), TRANS_ORE_BLOCK);
        // Register items
        Registry.register(Registry.ITEM, new Identifier("trans_ore", "trans_ore_block"), new BlockItem(TRANS_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("trans_ore", "trans_ingot"), TRANS_INGOT);
        // Register ores
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("trans_ore", "overworld_trans_ore_block"), OVERWORLD_TRANS_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("trans_ore", "overworld_trans_ore_block"), OVERWORLD_TRANS_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("trans_ore", "overworld_trans_ore_block")));
        LOGGER.info("Done!");
    }
}
