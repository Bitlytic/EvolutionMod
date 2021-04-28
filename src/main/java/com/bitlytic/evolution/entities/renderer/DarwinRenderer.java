package com.bitlytic.evolution.entities.renderer;

import com.bitlytic.evolution.EvolutionMod;
import com.bitlytic.evolution.entities.DarwinEntity;
import com.bitlytic.evolution.entities.model.DarwinModel;
import com.bitlytic.evolution.entities.model.layer.DarwinLayer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarwinRenderer extends MobRenderer<DarwinEntity, DarwinModel> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(EvolutionMod.MOD_ID, "textures/entity/darwin.png");

	public DarwinRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new DarwinModel(), 0.5f);
		this.addLayer(new DarwinLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(DarwinEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(DarwinEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.scale(1, entityIn.getMHeight(), 1);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

	}

	public static class RenderFactory implements IRenderFactory<DarwinEntity> {

		@Override
		public EntityRenderer<? super DarwinEntity> createRenderFor(EntityRendererManager manager) {
			return new DarwinRenderer(manager);
		}
	}
}
