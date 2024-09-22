package com.booppy.treasuretree;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TreasureTreeClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.TREASURE_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.COIN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.COIN_D, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.COIN_E, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.COIN_S, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.POTTED_TREASURE_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TTBlocks.GOLDEN_LEAVES, RenderLayer.getCutout());
	}

}
