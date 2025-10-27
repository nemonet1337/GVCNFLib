package gvclib.render;

import org.lwjgl.opengl.GL11;

import gvclib.entity.living.EntityVehicleBase;
import net.minecraft.util.ResourceLocation;
import objmodel.IModelCustom;

public class RenderCanopy {
	public static void can(EntityVehicleBase entity, IModelCustom model) {
		GL11.glPushMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA , GL11.GL_ONE);//これでBLEND固定(ちらつき防止
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
//		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 0.6F);//1F,1F,1F,1Fでも変化しない
		model.renderPart("canopy");
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glDepthMask(true);
//        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
