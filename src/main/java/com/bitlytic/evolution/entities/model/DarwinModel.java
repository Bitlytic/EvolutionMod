package com.bitlytic.evolution.entities.model;

import com.bitlytic.evolution.entities.DarwinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class DarwinModel extends EntityModel<DarwinEntity> {

	private final ModelRenderer Body;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightArm;
	private final ModelRenderer Head;

	public DarwinModel() {
		textureWidth = 32;
		textureHeight = 32;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -9.0F, -1.5F, 6.0F, 5.0F, 3.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(-1.5F, -4.0F, 0.0F);
		Body.addChild(LeftLeg);
		LeftLeg.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(1.5F, -4.0F, 0.0F);
		Body.addChild(RightLeg);
		RightLeg.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(-3.0F, -9.0F, 0.0F);
		Body.addChild(LeftArm);
		setRotationAngle(LeftArm, 0.0F, 0.0F, 0.1745F);
		LeftArm.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(3.0F, -9.0F, 0.0F);
		Body.addChild(RightArm);
		setRotationAngle(RightArm, 0.0F, 0.0F, -0.1745F);
		RightArm.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DarwinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
		Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

		float l1 = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		float l2 = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		RightArm.rotateAngleX = l1;
		LeftLeg.rotateAngleX = l1;

		RightLeg.rotateAngleX = l2;
		LeftArm.rotateAngleX = l2;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setBodyBigger() {
		Body.addBox(-4.0F, -10.0F, -3.5F, 10.0F, 7.0F, 3.0F, 0.0F);
	}
}
