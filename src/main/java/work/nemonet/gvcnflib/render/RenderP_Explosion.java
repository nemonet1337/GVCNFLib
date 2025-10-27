package gvclib.render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.mod_GVCLib;
import gvclib.entity.EntityP_Explosion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

@SideOnly(Side.CLIENT)
public class RenderP_Explosion<T extends EntityP_Explosion> extends Render<T>
{
	 private static final ResourceLocation EXPLOSION_TEXTURE = new ResourceLocation("textures/entity/explosion.png");
	 private static final IModelCustom exp = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/particle/explosion.mqo"));
	 /*   private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F)
	    		.addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB)
	    		.addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);*/

	    
	    
    public RenderP_Explosion(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        //this.rendermanager = renderManagerIn;
    }

    public void renderExp_Big(String ti, float x, float y, float z) {
    	GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
    	renderExp(ti, 0.5F, 0.5F, 0.5F);
		renderExp(ti, -0.75F, 0.25F, 0.25F);
		renderExp(ti, 0.25F, -0.25F, -0.25F);
		renderExp(ti, -0.5F, -0.5F, -0.5F);
		GlStateManager.translate(-x, -y, -z);
		GlStateManager.popMatrix();
    }
    
    public void renderExp(String ti, float x, float y, float z) {
    	GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		exp.renderPart("exp_" + ti);
		GlStateManager.translate(-x, -y, -z);
		GlStateManager.popMatrix();
    }
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	Minecraft minecraft = Minecraft.getMinecraft();
        
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        
        GlStateManager.rotate(-minecraft.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(minecraft.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * minecraft.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        {
        	 minecraft.getTextureManager().bindTexture(EXPLOSION_TEXTURE);
        	int i = entity.time*2;
        	String ti = Integer.toString(i);
        	/*if(entity.getExpSize() >= 2) {
        		/*int exp_size = (int)entity.getExpSize() * (int)entity.getExpSize();
            	if(exp_size >= 255)exp_size = 255;
            	for(int size = 0; size < exp_size; ++size) {
            		GlStateManager.pushMatrix();
            		GlStateManager.translate(entity.exp_x[size], entity.exp_y[size], entity.exp_z[size]);
            		exp.renderPart("exp_" + ti);
            		GlStateManager.popMatrix();
            	}*
        		for (int iz = 0; iz < 6; ++iz)
                {
        			GlStateManager.pushMatrix();
            		GlStateManager.translate(entity.exp_x[iz], entity.exp_y[iz], entity.exp_z[iz]);
            		exp.renderPart("exp_" + ti);
            		GlStateManager.translate(-entity.exp_x[iz], -entity.exp_y[iz], -entity.exp_z[iz]);
            		GlStateManager.popMatrix();
                }
        	}*/
        	if(entity.getExpSize() >= 2) {
        		renderExp_Big(ti, 2F, 2F, 1F);
        		renderExp_Big(ti, -2F, 1F, -1F);
        		renderExp_Big(ti, 1F, -1F, 1F);
        		renderExp_Big(ti, -1F, 0F, -10F);
        	}
        	else if(entity.getExpSize() > 1) {
        		renderExp(ti, 0.5F, 0.5F, 0.5F);
        		renderExp(ti, -0.75F, 0.25F, 0.25F);
        		renderExp(ti, 0.25F, -0.25F, -0.25F);
        		renderExp(ti, -0.5F, -0.5F, -0.5F);
        	}
        	exp.renderPart("exp_" + ti);
        }
        
        /*GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        //buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        float rotationX = ActiveRenderInfo.getRotationX();
        float rotationZ = ActiveRenderInfo.getRotationZ();
        float rotationYZ = ActiveRenderInfo.getRotationYZ();
        float rotationXY = ActiveRenderInfo.getRotationXY();
        float rotationXZ = ActiveRenderInfo.getRotationXZ();
        
        float fz = entity.world.rand.nextFloat() * 0.6F + 0.4F;
        float particleRed = fz;
        float particleGreen = fz;
        float particleBlue = fz;
        
        double interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
        
        int i = entity.time;//(int)(((float)this.life + partialTicks) * 15.0F / (float)this.lifeTime);

        if (entity.time <= 15)
        {
        	minecraft.getTextureManager().bindTexture(EXPLOSION_TEXTURE);
            float f = (float)(i % 4) / 4.0F;
            float f1 = f + 0.24975F;
            float f2 = (float)(i / 4) / 4.0F;
            float f3 = f2 + 0.24975F;
            float f4 = 2.0F * 1;//2.0F * this.size;
            float f5 = (float)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks - interpPosX);
            float f6 = (float)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks - interpPosY);
            float f7 = (float)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks - interpPosZ);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            RenderHelper.disableStandardItemLighting();
            buffer.begin(7, VERTEX_FORMAT);
            buffer.pos((double)(f5 - rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4), (double)(f7 - rotationYZ * f4 - rotationXZ * f4)).tex((double)f1, (double)f3).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4), (double)(f7 - rotationYZ * f4 + rotationXZ * f4)).tex((double)f1, (double)f2).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 + rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4), (double)(f7 + rotationYZ * f4 + rotationXZ * f4)).tex((double)f, (double)f2).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 + rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4), (double)(f7 + rotationYZ * f4 - rotationXZ * f4)).tex((double)f, (double)f3).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            Tessellator.getInstance().draw();
            GlStateManager.enableLighting();
        }
        
        
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);*/
        
        GlStateManager.translate((float)-x, (float)-y, (float)-z);
        GlStateManager.popMatrix();
        
        
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return EXPLOSION_TEXTURE;
	}
	
}