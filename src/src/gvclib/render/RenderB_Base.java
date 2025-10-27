package gvclib.render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import gvclib.mod_GVCLib;
import gvclib.entity.EntityBBase;
import gvclib.entity.EntityB_Bullet;
import gvclib.entity.EntityB_BulletAA;
import gvclib.entity.EntityB_Shell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

@SideOnly(Side.CLIENT)
public class RenderB_Base<T extends EntityBBase> extends Render<T>
{
	private static final IModelCustom tankk = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/entity/mflash.mqo"));
    private static final ResourceLocation boatTextures0 = new ResourceLocation("gvclib:textures/entity/mflash.png");
    public RenderB_Base(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    //public  int moi = 0;
	//public  String[] modelt = new String[64];
	//public  IModelCustom[] model = new IModelCustom[64];
    /**
     * Renders the desired {@code T} type Entity.
     */
    //@Override
    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
    	return true;
    }
    
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	
    	Minecraft minecraft = Minecraft.getMinecraft();
    	/*if(entity.time < 255){
    		for(int ti =0; ti < entity.time; ++ti){
    			 GlStateManager.pushMatrix();
    			 double d0 = entity.traceL_posX[ti] + (entity.trace_posX[ti] - entity.traceL_posX[ti]) * (double)partialTicks;
    		     double d1 = entity.traceL_posY[ti] + (entity.trace_posY[ti] - entity.traceL_posY[ti]) * (double)partialTicks;
    		     double d2 = entity.traceL_posZ[ti] + (entity.trace_posZ[ti] - entity.traceL_posZ[ti]) * (double)partialTicks;
    	    	 GlStateManager.translate((float)d0, (float)d1, (float)d2);
    	    	 GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
    	    	 GlStateManager.rotate(-entity.rotationPitch, 1.0F, 0.0F, 0.0F);
    	    	 this.bindTexture(boatTextures0);
    	    	 tankk.renderPart("mat1");
    	    	 tankk.renderPart("obj1");
    	    	 tankk.renderPart("bullet");
    	    	 GlStateManager.popMatrix();
    		}
    	}
    	
    	/*
    	if(minecraft.getDebugFPS() < 15)return;
    	{
    		double xx = entity.motionX;
    		double yy = entity.motionY;
    		double zz = entity.motionZ;
    		double xy = Math.sqrt(xx * xx + yy * yy);
    		double xyz = Math.sqrt(xy * xy + zz * zz);
    		if(xyz > 5)return;
    	}
    	*/
    	
    	
        //this.bindEntityTexture(new ResourceLocation(entity.getModel()));
        GlStateManager.pushMatrix();
        //GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
        //GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-entity.rotationPitch, 1.0F, 0.0F, 0.0F);
        
        if(entity.model == null && entity.getModel() != null){
        	boolean mo = false;
        	for(int ii = 0; ii < 1024; ++ii) {
        		if(mod_GVCLib.modelt[ii] != null && mod_GVCLib.modelt[ii].equals(entity.getModel())) {
        			entity.model  = mod_GVCLib.model[ii];
        			mo = true;
        			break;
        		}
        	}
        	if(!mo) 
        	{
        		ResourceLocation resource = new ResourceLocation(entity.getModel());
        		try {
					IResource res = Minecraft.getMinecraft().getResourceManager().getResource(resource);
					if(res != null) {
	        			entity.model = AdvancedModelLoader.loadModel(resource);
	            		++mod_GVCLib.entityid;
	            		mod_GVCLib.model[mod_GVCLib.entityid] = entity.model;
	            		mod_GVCLib.modelt[mod_GVCLib.entityid] = entity.getModel();
	            		System.out.println(String.format("HMGGVC-" + mod_GVCLib.entityid));
	        		}
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println(String.format("warning! not exist model!::::" + entity.getModel()));
				}
        		
        	}
			
		}
        
        if(entity.model != null && entity.time > 1){
            minecraft.getTextureManager().bindTexture(new ResourceLocation(entity.getTex()));
        	//minecraft.getTextureManager().bindTexture(entity.entity_tex);
            {
            	 GlStateManager.pushMatrix();
                 entity.model.renderPart("bullet_no_rote");
            	 GlStateManager.popMatrix();
            }
            {
            	 GlStateManager.pushMatrix();
            	 if(!entity.onGround && !entity.inGround) {
            		 GlStateManager.rotate(entity.time * 10, 0.0F, 0.0F, 1.0F);
            	 }
            	 if(entity instanceof EntityB_Bullet || entity instanceof EntityB_BulletAA){
            		 //EntityB_Bullet  bullet = (EntityB_Bullet) entity;
            		 EntityLivingBase player = entity.getThrower();
            		 boolean flags = true;
            		 if(player == minecraft.player) {
            			 if(minecraft.gameSettings.thirdPersonView == 0){
            				 flags = false;
            			 }
            		 }
            		 if(entity.getModel().equals("gvclib:textures/entity/model/bullet_aaa.mqo")
            				 || entity.getModel().equals("gvclib:textures/entity/bulletaaa.mqo")
            				 || entity.getModel().equals("gvclib:textures/entity/bulletaaa.obj")) {
            			 flags = false;
            		 }
            		 
            		 
            		 double xz = Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
            		 double xyz = Math.sqrt(xz * xz + entity.motionY * entity.motionY);
            		 if(xyz > 2.2 && (!entity.onGround && !entity.inGround) && flags) {
            			 //GlStateManager.translate(0.0F, 0.0F, -(float)xyz*1);
            			 GlStateManager.scale(1.0F, 1.0F, (float)xyz*10);
            		 }
            	 }
                 entity.model.renderPart("bullet");
            	 GlStateManager.popMatrix();
            }
            {
           	 GlStateManager.pushMatrix();
           	 if(!entity.onGround && !entity.inGround) {
           		 GlStateManager.rotate(entity.time * 10, 1.0F, 0.0F, 0.0F);
           	 }
                entity.model.renderPart("bullet_x");
           	 GlStateManager.popMatrix();
           }
            {
              	 GlStateManager.pushMatrix();
              	 if(!entity.onGround && !entity.inGround) {
              		 GlStateManager.rotate(entity.time * 10, 0.0F, 1.0F, 0.0F);
              	 }
                   entity.model.renderPart("bullet_y");
              	 GlStateManager.popMatrix();
              }
            
            
            
            {
            	GlStateManager.disableLighting();
            	float size = partialTicks * 0.4F + 1;
            	GlStateManager.scale(size, size, size);
            	entity.model.renderPart("bullet_e_1");
            	 GlStateManager.enableLighting();
            }
            
            {
				GL11.glPushMatrix();//glstart
		    	GL11.glDisable(GL11.GL_LIGHT1);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_BLEND);
		    	int i = 15728880;
		        int j = i % 65536;
		        int k = i / 65536;
		      //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
				
				{
					//GL11.glTranslatef(-0.35F, 0.9F , 1.35F);
					//float size = partialTicks * 0.4F + 0.5F;
                	//GlStateManager.scale(size, size, size);
                	//GL11.glTranslatef(0.35F, -0.9F , -1.35F);
					GL11.glPushMatrix();//glstart
					GL11.glColor4f(1F, 1F, 1F, 1F);
					entity.model.renderPart("bullet_light_3");
					GL11.glColor4f(1F, 1F, 1F, 0.5F);
					entity.model.renderPart("bullet_light_2");
					GL11.glColor4f(1F, 1F, 1F, 0.35F);
					entity.model.renderPart("bullet_light_1");
					GL11.glPopMatrix();//glend
				}
				GL11.glDisable(GL11.GL_BLEND);
		        GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_LIGHT1);
		    	GL11.glPopMatrix();//glend
			}
        }
        /*
        if(entity.time < 255){
    		for(int ti =0; ti < entity.time; ++ti){
    			 GlStateManager.pushMatrix();
    			 double d0 = entity.posX - entity.trace_posX[ti];
    		     double d1 = entity.posY - entity.trace_posY[ti];
    		     double d2 = entity.posZ - entity.trace_posZ[ti];
    	    	 GlStateManager.translate(-(float)d0, (float)d1, -(float)d2);
    	    	 this.bindTexture(boatTextures0);
    	    	 tankk.renderPart("mat1");
    	    	 tankk.renderPart("obj1");
    	    	 tankk.renderPart("bullet");
    	    	 GlStateManager.popMatrix();
    		}
    	}*/
        
        GlStateManager.translate((float)-x, (float)-y, (float)-z);
        GlStateManager.popMatrix();
        
        //*/
        //if(entity instanceof EntityB_Shell)
        {
        	this.bindTexture(boatTextures0);
        	
        	//if(!entity.onGround && !entity.inGround)
        	{
        		//renderCrystalBeams(x, y, z, partialTicks, entity.posX, entity.posY, entity.posZ, 
                // 		entity.ticksExisted, entity.startposX, entity.startposY, entity.startposZ);
        		/*if(entity.time > 0) {
        			for(int ti = 1; ti < entity.time; ++ti) {
                    	//		if(ti > entity.time - 5) 
                    			{
                    				renderCrystalBeams(x, y, z, partialTicks, entity.start_posX[ti], entity.start_posY[ti], entity.start_posZ[ti], 
                                    		entity.ticksExisted, entity.start_posX[ti-1], entity.start_posY[ti-1], entity.start_posZ[ti-1]);
                    			}
                    		}
        		}*/
        	}
        	
        	/*{
        		Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();
                
                GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                bufferbuilder.pos(-7.0D, -2.0D, -2.0D).tex(0.0D, 0.15625D).endVertex();
                bufferbuilder.pos(-7.0D, -2.0D, 2.0D).tex(0.15625D, 0.15625D).endVertex();
                bufferbuilder.pos(-7.0D, 2.0D, 2.0D).tex(0.15625D, 0.3125D).endVertex();
                bufferbuilder.pos(-7.0D, 2.0D, -2.0D).tex(0.0D, 0.3125D).endVertex();
                tessellator.draw();
                GlStateManager.glNormal3f(-0.05625F, 0.0F, 0.0F);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                bufferbuilder.pos(-7.0D, 2.0D, -2.0D).tex(0.0D, 0.15625D).endVertex();
                bufferbuilder.pos(-7.0D, 2.0D, 2.0D).tex(0.15625D, 0.15625D).endVertex();
                bufferbuilder.pos(-7.0D, -2.0D, 2.0D).tex(0.15625D, 0.3125D).endVertex();
                bufferbuilder.pos(-7.0D, -2.0D, -2.0D).tex(0.0D, 0.3125D).endVertex();
                tessellator.draw();

                for (int j = 0; j < 4; ++j)
                {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.glNormal3f(0.0F, 0.0F, 0.05625F);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos(-8.0D, -2.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
                    bufferbuilder.pos(8.0D, -2.0D, 0.0D).tex(0.5D, 0.0D).endVertex();
                    bufferbuilder.pos(8.0D, 2.0D, 0.0D).tex(0.5D, 0.15625D).endVertex();
                    bufferbuilder.pos(-8.0D, 2.0D, 0.0D).tex(0.0D, 0.15625D).endVertex();
                    tessellator.draw();
                }
        	}*/
        	
        	/*if(entity.getThrower() != null) {
        		double xx = entity.getThrower().posX;
            	double yy = entity.getThrower().posY;
            	double zz = entity.getThrower().posZ;
            	 renderCrystalBeams(x, y, z, partialTicks, entity.posX, 
                 		entity.posY, 
                 		entity.posZ, 
                 		entity.ticksExisted, xx, yy, zz);
        	}*/
        	
        	/*if(entity.startposX == 0 && entity.startposY == 0 && entity.startposZ == 0)
        	{
        		entity.startposX = entity.posX;
        		entity.startposY = entity.posY;
        		entity.startposZ = entity.posZ;
        	}else */
/*        	{
        		entity.start_posX[entity.time] = entity.posX;
        		entity.start_posY[entity.time] = entity.posY;
        		entity.start_posZ[entity.time] = entity.posZ;
        		for(int ti = 1; ti < entity.time; ++ti) {
        	//		if(ti > entity.time - 5) 
        			{
        				renderCrystalBeams(x, y, z, partialTicks, entity.start_posX[ti], entity.start_posY[ti], entity.start_posZ[ti], 
                        		entity.ticksExisted, entity.start_posX[ti-1], entity.start_posY[ti-1], entity.start_posZ[ti-1]);
        			}
        		}*/
        		/*renderCrystalBeams(x, y, z, partialTicks, entity.posX, 
                		entity.posY, 
                		entity.posZ, 
                		entity.ticksExisted, entity.prevPosX, entity.prevPosY, entity.prevPosZ);///
        	}*/
        }
        
        /*{//flash
        	GlStateManager.pushMatrix();
            //GlStateManager.translate((float)entity.startposX, (float)entity.startposY, (float)entity.startposZ);
        	GlStateManager.translate((float)x, (float)y, (float)z);
        	float xx = (float) (x - entity.startposX);
        	float yy = (float) (y - entity.startposY);
        	float zz = (float) (z - entity.startposZ);
        	//GlStateManager.translate((float)x - xx, (float)y - yy, (float)z - zz);
        	//GlStateManager.translate((float)-entity.startposX, (float)-entity.startposY, (float)-entity.startposZ);
            GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
            //GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-entity.rotationPitch, 1.0F, 0.0F, 0.0F);
            if(entity.time <= 3)
            {
            	if(entity.modelf == null && entity.getModelF() != null){
        			{
        				entity.modelf = AdvancedModelLoader
        						.loadModel(new ResourceLocation(entity.getModelF()));
        			}
        		}
            	if(entity.modelf != null){
					if (entity.time <= 1) {
						minecraft.getTextureManager().bindTexture(new ResourceLocation(entity.getTexF()));
						entity.modelf.renderPart("mat1");
						entity.modelf.renderPart("bullet");
					}
					if (entity.time == 2) {
						minecraft.getTextureManager().bindTexture(new ResourceLocation(entity.getTexF()));
						entity.modelf.renderPart("bullet2");
					}
                }
            }
            GlStateManager.popMatrix();
        }*/
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    
    public static void renderCrystalBeams(double p_188325_0_, double p_188325_2_, double p_188325_4_, float p_188325_6_, 
    		double p_188325_7_, double p_188325_9_, double p_188325_11_, int p_188325_13_, double p_188325_14_, double p_188325_16_, double p_188325_18_)
    {
        float f = (float)(p_188325_14_ - p_188325_7_);
        float f1 = (float)(p_188325_16_ - 1.0D - p_188325_9_);
        float f2 = (float)(p_188325_18_ - p_188325_11_);
        float f3 = MathHelper.sqrt(f * f + f2 * f2);
        float f4 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_188325_0_, (float)p_188325_2_, (float)p_188325_4_);
        GlStateManager.rotate((float)(-Math.atan2((double)f2, (double)f)) * (180F / (float)Math.PI) - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(-Math.atan2((double)f3, (double)f1)) * (180F / (float)Math.PI) - 90.0F, 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        //RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        GlStateManager.shadeModel(7425);
        float f5 = 0.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
        float f6 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
        int i = 8;

        for (int j = 0; j <= 8; ++j)
        {
            float f7 = MathHelper.sin((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.25F;
            float f8 = MathHelper.cos((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.25F;
            float f9 = (float)(j % 8) / 8.0F;
            bufferbuilder.pos((double)(f7 * 0.2F), (double)(f8 * 0.2F), 0.0D).tex((double)f9, (double)f5).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)f7, (double)f8, (double)f4).tex((double)f9, (double)f6).color(255, 255, 255, 255).endVertex();
            
        }

        tessellator.draw();
        
        /*{
        	bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        	bufferbuilder.pos((double)1, (double)1, (double)f4).tex((double)1, (double)f6).color(255, 255, 255, 255).endVertex(); 
            bufferbuilder.pos((double)-1, (double)1, (double)f4).tex((double)1, (double)f6).color(255, 255, 255, 255).endVertex(); 
            bufferbuilder.pos((double)1, (double)-1, (double)f4).tex((double)1, (double)f6).color(255, 255, 255, 255).endVertex(); 
            bufferbuilder.pos((double)-1, (double)-1, (double)f4).tex((double)1, (double)f6).color(255, 255, 255, 255).endVertex(); 
            tessellator.draw();
        }*/
        
        
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        //RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return boatTextures0;
	}
}