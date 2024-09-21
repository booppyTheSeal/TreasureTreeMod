package com.booppy.treasuretree;

import java.util.Optional;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class TreasureTree implements ModInitializer {

	static final String MOD_ID="treasuretree";

	private static final RegistryKey<ConfiguredFeature<?, ?>> TREASURE_TREE_FEATURE = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID,"treasure_tree"));
	
	public static Block register(Block b,String id) {
		return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, id),
				b);
	}
	
	public static Block createFlowerPotBlock(Block flower) {
		return new FlowerPotBlock(flower, AbstractBlock.Settings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY));
	}
	
	public static final SaplingGenerator TREASURE_TREE = new SaplingGenerator(
			"treasure_tree",
			Optional.empty(),
			Optional.of(TreasureTree.TREASURE_TREE_FEATURE),
			Optional.empty()
		);
	
	public static final Block GOLDEN_LEAVES = register(new LeavesBlock(AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.strength(0.2F)
			.ticksRandomly()
			.sounds(BlockSoundGroup.GRASS)
			.nonOpaque()
			.allowsSpawning(Blocks::canSpawnOnLeaves)
			.suffocates(Blocks::never)
			.blockVision(Blocks::never)
			.burnable()
			.pistonBehavior(PistonBehavior.DESTROY)
			.solidBlock(Blocks::never)),
					"golden_leaves");
	
	public static final Block TREASURE_SAPLING = register(new SaplingBlock(TREASURE_TREE, AbstractBlock.Settings.create()
	.mapColor(MapColor.GOLD)
	.noCollision()
	.ticksRandomly()
	.breakInstantly()
	.sounds(BlockSoundGroup.GRASS)
	.pistonBehavior(PistonBehavior.DESTROY)),
			"treasure_sapling");
	
	public static final Block COIN = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->4)),
					"coin");
	
	public static final Block COIN_S = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->4)),
					"coin_single");
	
	public static final Block COIN_E = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->4)),
					"coin_emerald");
	
	public static final Block COIN_D = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->4)),
					"coin_diamond");
	
	public static final Block POTTED_TREASURE_SAPLING = register(createFlowerPotBlock(TREASURE_SAPLING), "potted_treasure_sapling");
	
	public static final Item TREASURE_SAPLING_ITEM = Registry.register(Registries.ITEM,Identifier.of(MOD_ID, "treasure_sapling"),
			new BlockItem(TREASURE_SAPLING,new Item.Settings()));
	
	public static final TreeDecoratorType<TreasureTreeDecorator> TREASURE_DECORATOR = Registry.register(Registries.TREE_DECORATOR_TYPE,Identifier.of(MOD_ID, "treasure"),
			new TreeDecoratorType<TreasureTreeDecorator>(TreasureTreeDecorator.CODEC));
	
	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(c->{
			c.add(TREASURE_SAPLING_ITEM);
		});
	}

}
