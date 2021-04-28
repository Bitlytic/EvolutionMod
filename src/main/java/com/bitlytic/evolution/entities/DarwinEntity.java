package com.bitlytic.evolution.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class DarwinEntity extends AnimalEntity {
	private static final DataParameter<Boolean> HAS_TAIL = EntityDataManager.createKey(DarwinEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Float> M_HEIGHT = EntityDataManager.createKey(DarwinEntity.class, DataSerializers.FLOAT);

	public DarwinEntity(EntityType<? extends AnimalEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HAS_TAIL, false);
		this.dataManager.register(M_HEIGHT, world.getRandom().nextFloat() * 0.30f + 0.85f);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new BreedGoal(this, 0.25));
		goalSelector.addGoal(1, new DarwinTemptGoal(this));
		goalSelector.addGoal(2, new LookRandomlyGoal(this));
		goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.25));

	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isFood();
	}

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity ageableEntity) {
		DarwinEntity parent = (DarwinEntity) ageableEntity;
		DarwinEntity child = ModEntities.DARWIN.get().create(world);
		child.setShowTail(this.isShowTail() || parent.isShowTail());

		return child;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!this.isShowTail() && stack.getItem() == Items.LAPIS_LAZULI) {
			this.setShowTail(true);
			if (!player.isCreative()) {
				stack.setCount(stack.getCount() - 1);
			}
		}

		return super.processInteract(player, hand);
	}

	public boolean isShowTail() {
		return this.dataManager.get(HAS_TAIL);
	}

	public void setShowTail(boolean t) {
		this.dataManager.set(HAS_TAIL, t);
	}

	public float getMHeight() {
		return this.dataManager.get(M_HEIGHT);
	}

	public void setMHeight(float h) {
		this.dataManager.set(M_HEIGHT, h);
	}

	private class DarwinTemptGoal extends TemptGoal {
		public DarwinTemptGoal(CreatureEntity creatureIn) {
			super(creatureIn, 0.25, null, false);
		}

		@Override
		protected boolean isTempting(ItemStack stack) {
			return stack.isFood();
		}
	}
}
