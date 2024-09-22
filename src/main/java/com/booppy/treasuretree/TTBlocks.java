package com.booppy.treasuretree;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import static com.booppy.treasuretree.TreasureTree.MOD_ID;

public class TTBlocks {

	public static final Block GOLDEN_LEAVES = register(new LeavesBlock(AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.strength(0.2F)
			.ticksRandomly()
			.sounds(BlockSoundGroup.GRASS)
			.nonOpaque()
			.allowsSpawning(Blocks::canSpawnOnLeaves)
			.suffocates(Blocks::never)
			.blockVision(Blocks::never)
			.pistonBehavior(PistonBehavior.DESTROY)
			.solidBlock(Blocks::never)
			.luminance((s)->{return 4;})),
					"golden_leaves");
	
	public static final Block TREASURE_SAPLING = register(new SaplingBlock(TreasureTree.TREASURE_TREE, AbstractBlock.Settings.create()
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
			.luminance((s)->6)),
					"coin");
	
	public static final Block COIN_S = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->6)),
					"coin_single");
	
	public static final Block COIN_E = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->6)),
					"coin_emerald");
	
	public static final Block COIN_D = register(new CoinBlock( AbstractBlock.Settings.create()
			.mapColor(MapColor.GOLD)
			.noCollision()
			.breakInstantly()
			.sounds(BlockSoundGroup.METAL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.luminance((s)->6)),
					"coin_diamond");
	
	public static final Block POTTED_TREASURE_SAPLING = register(createFlowerPotBlock(TREASURE_SAPLING), "potted_treasure_sapling");
	
	public static Block register(Block b,String id) {
		return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, id),
				b);
	}
	
	public static Block createFlowerPotBlock(Block flower) {
		return new FlowerPotBlock(flower, AbstractBlock.Settings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY));
	}

	public static void init() {
		
	}
}
