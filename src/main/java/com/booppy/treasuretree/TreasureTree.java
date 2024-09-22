package com.booppy.treasuretree;

import java.util.Optional;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TreasureTree implements ModInitializer {

	public static final String MOD_ID="treasuretree";

	public static final RegistryKey<ConfiguredFeature<?, ?>> TREASURE_TREE_FEATURE = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID,"treasure_tree"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_TREASURE_TREE_FEATURE = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID,"treasure_tree_mega"));
	
	public static final SaplingGenerator TREASURE_TREE = new SaplingGenerator(
			"treasure_tree",
			Optional.of(TreasureTree.MEGA_TREASURE_TREE_FEATURE),
			Optional.of(TreasureTree.TREASURE_TREE_FEATURE),
			Optional.empty()
		);
	
	public static final Item TREASURE_SAPLING_ITEM = Registry.register(Registries.ITEM,Identifier.of(MOD_ID, "treasure_sapling"),
			new BlockItem(TTBlocks.TREASURE_SAPLING,new Item.Settings()));
	
	public static final Item GOLDEN_LEAVES = Registry.register(Registries.ITEM,Identifier.of(MOD_ID, "golden_leaves"),
			new BlockItem(TTBlocks.GOLDEN_LEAVES,new Item.Settings()));
	
	public static final TreeDecoratorType<TreasureTreeDecorator> TREASURE_DECORATOR = Registry.register(Registries.TREE_DECORATOR_TYPE,Identifier.of(MOD_ID, "treasure"),
			new TreeDecoratorType<TreasureTreeDecorator>(TreasureTreeDecorator.CODEC));
	
	public static final TrunkPlacerType<TreasureTreeTrunkPlacer> TREASURE_TRUNK_PLACER = Registry.register(Registries.TRUNK_PLACER_TYPE,Identifier.of(MOD_ID, "treasure"),
			new TrunkPlacerType<TreasureTreeTrunkPlacer>(TreasureTreeTrunkPlacer.CODEC));
	
	@Override
	public void onInitialize() {
		TTBlocks.init();
		TTBlockTypes.init();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(c->{
			c.add(TREASURE_SAPLING_ITEM);
			c.add(GOLDEN_LEAVES);
		});
	}

}
