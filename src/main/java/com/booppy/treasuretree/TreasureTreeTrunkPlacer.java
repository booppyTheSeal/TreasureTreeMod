package com.booppy.treasuretree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer.TreeNode;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TreasureTreeTrunkPlacer extends TrunkPlacer {

	public static final MapCodec<TreasureTreeTrunkPlacer> CODEC= RecordCodecBuilder.mapCodec(
			instance -> fillTrunkPlacerFields(instance)
			.apply(instance, TreasureTreeTrunkPlacer::new));

	public TreasureTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TreasureTree.TREASURE_TRUNK_PLACER;
	}

	@Override
	public List<TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random,
			int height, BlockPos blockPos, TreeFeatureConfig config) {
		BlockPos.Mutable pos1,pos2,pos3,pos4;
		pos1=blockPos.down().mutableCopy();
		pos2=blockPos.down().east().mutableCopy();
		pos3=blockPos.down().south().mutableCopy();
		pos4=blockPos.down().south().east().mutableCopy();
		TrunkPlacer.setToDirt(world, replacer, random, pos1, config);
		TrunkPlacer.setToDirt(world, replacer, random, pos3, config);
		TrunkPlacer.setToDirt(world, replacer, random, pos2, config);
		TrunkPlacer.setToDirt(world, replacer, random, pos4, config);
		
		List<FoliagePlacer.TreeNode> list = new ArrayList<TreeNode>();
		int branchBase=height/2;
		for(int h=1;h<branchBase;h++) {
			this.getAndSetState(world, replacer, random, pos1.move(0, 1, 0), config);
			this.getAndSetState(world, replacer, random, pos2.move(0, 1, 0), config);
			this.getAndSetState(world, replacer, random, pos3.move(0, 1, 0), config);
			this.getAndSetState(world, replacer, random, pos4.move(0, 1, 0), config);
		}

		List<BranchNode> branches = new ArrayList<BranchNode>();
		branches.add(new BranchNode(-1,-1,pos1));
		branches.add(new BranchNode(1,-1,pos2));
		branches.add(new BranchNode(-1,1,pos3));
		branches.add(new BranchNode(1,1,pos4));
		for(int h=branchBase;h<height+2;h++) {
			List<BranchNode> newBranch = new ArrayList<BranchNode>();
			for(BranchNode node:branches) {
				node.pos.move(0, 1, 0);
				this.genBranch(h,node,world,replacer,config,random,branchBase);
			}
		}
		for(BranchNode node:branches) {
			node.pos.move(0, 1, 0);
			list.add(new FoliagePlacer.TreeNode(node.pos, 0, true));
		}
		return list;
	}

	private void genBranch(int h, BranchNode node, TestableWorld world, 
			BiConsumer<BlockPos, BlockState> replacer,
			TreeFeatureConfig config, Random random, int branchBase) 
	{
		
		if(node.isBranch) {
			this.getAndSetState(world, replacer, random,node.getPos(random), config);
			if(random.nextBoolean())this.getAndSetState(world, replacer, random,node.pos.down(), config);
		}
		else {
			node.isBranch=random.nextInt(4)==1;
			this.getAndSetState(world, replacer, random,node.pos, config);
		}
	}

	static class BranchNode{
		final int x,z;
		final BlockPos.Mutable pos;
		boolean isBranch;
		BranchNode(int x,int z,Mutable pos){
			this.x=x;
			this.z=z;
			this.pos=pos;
			this.isBranch=false;
		}
		
		public BlockPos getPos(Random random) {
			int i=random.nextInt(5);
			return switch(i) {
			case 0->pos.move(x, 0, z);
			case 1->pos.move(0, 0, z);
			case 2->pos.move(x, 0, 0);
			case 3->pos.move(0, 0, z);
			case 4->pos.move(x, 0, 0);
			default -> pos.move(x, 0, z);
			};
		}
	}
}
