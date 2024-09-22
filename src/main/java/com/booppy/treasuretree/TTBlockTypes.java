package com.booppy.treasuretree;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TTBlockTypes {

	public static void init() {
		Registry.register(Registries.BLOCK_TYPE,Identifier.of(TreasureTree.MOD_ID,"coin"), CoinBlock.CODEC);
	}

}
