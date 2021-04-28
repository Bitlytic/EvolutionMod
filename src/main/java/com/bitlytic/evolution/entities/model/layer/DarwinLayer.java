package com.bitlytic.evolution.entities.model.layer;

import com.bitlytic.evolution.EvolutionMod;
import com.bitlytic.evolution.entities.DarwinEntity;
import com.bitlytic.evolution.entities.model.DarwinModel;
import com.bitlytic.evolution.entities.model.DarwinTail;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Resource;

public class DarwinLayer extends LayerRenderer<DarwinEntity, DarwinModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EvolutionMod.MOD_ID, "textures/entity/darwin_tail.png");
	private DarwinTail tailModel = new DarwinTail();

	public DarwinLayer(IEntityRenderer<DarwinEntity, DarwinModel> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, DarwinEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

		if(entitylivingbaseIn.isShowTail()) {
		renderCopyCutoutModel(this.getEntityModel(), this.tailModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1, 1, 1);
		}
	}
}
