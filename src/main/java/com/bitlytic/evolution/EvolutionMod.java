package com.bitlytic.evolution;

import com.bitlytic.evolution.entities.DarwinEntity;
import com.bitlytic.evolution.entities.ModEntities;
import com.bitlytic.evolution.setup.ClientProxy;
import com.bitlytic.evolution.setup.IProxy;
import com.bitlytic.evolution.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EvolutionMod.MOD_ID)
public class EvolutionMod {

	public static final String MOD_ID = "evolutionmod";
	public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public EvolutionMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModEntities.ENTITY_TYPES.register(eventBus);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
		proxy.init();
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}

		@SubscribeEvent
		public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event) {
//			IForgeRegistry<EntityType<?>> registry = event.getRegistry();
//			registry.register(
//					EntityType.Builder.create(DarwinEntity::new, EntityClassification.CREATURE)
//							.size(1, 1)
//							.setShouldReceiveVelocityUpdates(false)
//							.build("darwin").setRegistryName(MOD_ID, "darwin"));

		}
	}
}
