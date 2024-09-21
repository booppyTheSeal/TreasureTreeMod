package com.booppy.treasuretree;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class TreasureTreeDecorator extends TreeDecorator {
	
	public static final MapCodec<TreasureTreeDecorator> CODEC =Codec.floatRange(0.0F, 1.0F)
			.fieldOf("probability")
			.xmap(TreasureTreeDecorator::new, decorator -> decorator.probability);

	private final float probability;
	
	static final Direction[] directions=new Direction[] {
		Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST,
	};
	
	static final Block[] coins=new Block[] {
			TreasureTree.COIN_D,TreasureTree.COIN_E,TreasureTree.COIN_S
			,TreasureTree.COIN,TreasureTree.COIN
		};
	
	public TreasureTreeDecorator(float f) {
		this.probability=f;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return TreasureTree.TREASURE_DECORATOR;
	}

	@Override
	public void generate(Generator generator) {
		Random random = generator.getRandom();
		List<BlockPos> list = generator.getLeavesPositions();
		list.stream().forEach(pos -> {
			BlockPos blockPos = pos.down();
			if (generator.isAir(blockPos)) {
				if (random.nextFloat() <= this.probability) {
					this.genCoins(blockPos,generator,random);
				}
			}
		});
	}

	private void genCoins(BlockPos pos, Generator generator, Random random) {
		int max=random.nextInt(4);
		for(int i=0;i<max;i++) {
			if(generator.isAir(pos.down(i))) {
				Block b=Util.getRandom(coins, random);
				generator.replace(pos.down(i), this.getCoin(b,random));
				if(b!=TreasureTree.COIN)return;
			}else {
				generator.replace(pos, this.getCoin(TreasureTree.COIN_S,random));
				return;
			}
		}
		generator.replace(pos.down(3), this.getCoin(TreasureTree.COIN_D,random));
	}

	private BlockState getCoin(Block coin, Random random) {
		Direction d=Util.getRandom(TreasureTreeDecorator.directions,random);
		return coin.getDefaultState().with(CoinBlock.FACING, d);
	}

}
