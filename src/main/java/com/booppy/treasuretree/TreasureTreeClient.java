package com.booppy.treasuretree;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TreasureTreeClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.TREASURE_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.COIN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.COIN_D, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.COIN_E, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.COIN_S, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.POTTED_TREASURE_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TreasureTree.GOLDEN_LEAVES, RenderLayer.getCutout());
	}

}
