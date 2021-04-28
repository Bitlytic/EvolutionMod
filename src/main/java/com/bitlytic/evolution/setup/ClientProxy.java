package com.bitlytic.evolution.setup;

import com.bitlytic.evolution.entities.DarwinEntity;
import com.bitlytic.evolution.entities.ModEntities;
import com.bitlytic.evolution.entities.renderer.DarwinRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

	@Override
	public void init() {
		System.out.println("Hey, I'm running!");
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.DARWIN.get(), new DarwinRenderer.RenderFactory());
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}
}
