package com.bitlytic.evolution.setup;

import net.minecraft.world.World;

public interface IProxy {

	void init();

	World getClientWorld();
}
