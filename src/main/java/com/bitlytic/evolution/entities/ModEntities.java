package com.bitlytic.evolution.entities;

import com.bitlytic.evolution.EvolutionMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntities {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, EvolutionMod.MOD_ID);

	public static final RegistryObject<EntityType<DarwinEntity>> DARWIN =
			ENTITY_TYPES.register("darwin",
					() -> EntityType.Builder
					.create(DarwinEntity::new, EntityClassification.CREATURE)
					.size(0.5f, 0.5f)
					.build("darwin"));

}
