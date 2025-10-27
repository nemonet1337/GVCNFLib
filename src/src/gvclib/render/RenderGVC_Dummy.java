
package gvclib.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.entity.living.EntityGVC_Dummy;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

public class RenderGVC_Dummy extends Render {

	private static final ResourceLocation skeletonTexturesz = new ResourceLocation("gvclib:textures/entity/flare.png");
	private static final IModelCustom tankk = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/entity/flare.mqo"));
	private float scale;

	public RenderGVC_Dummy(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }
	
	float xsxs;
	public void doRender(EntityGVC_Dummy entity, double p_76986_2_, double p_76986_4_, double p_76986_6_,
			float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1EntityLiving) {
		return this.skeletonTexturesz;
	}

}