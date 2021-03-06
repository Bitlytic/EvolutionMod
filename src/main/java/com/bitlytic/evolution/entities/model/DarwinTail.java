package com.bitlytic.evolution.entities.model;

// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


import com.bitlytic.evolution.entities.DarwinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DarwinTail extends EntityModel<DarwinEntity> {
	private final ModelRenderer Tail;

	public DarwinTail() {
		textureWidth = 16;
		textureHeight = 16;

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 24.0F, 0.0F);
		Tail.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, 4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DarwinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
