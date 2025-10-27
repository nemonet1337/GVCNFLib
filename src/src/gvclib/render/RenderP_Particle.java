package gvclib.render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.mod_GVCLib;
import gvclib.entity.EntityP_Particle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

@SideOnly(Side.CLIENT)
public class RenderP_Particle<T extends EntityP_Particle> extends Render<T>
{
    private static final IModelCustom tankk = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/entity/mflash.mqo"));
    private static final ResourceLocation boatTextures0 = new ResourceLocation("gvclib:textures/entity/mflash.png");

    public RenderP_Particle(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        //this.rendermanager = renderManagerIn;
    }

   // public  int moi = 0;
	//public  String[] modelt = new String[64];
	//public  IModelCustom[] model = new IModelCustom[64];
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	Minecraft minecraft = Minecraft.getMinecraft();
        
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
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
        
        
        if(entity.model != null){
        	
			//GL11.glEnable(GL11.GL_BLEND);
        	//GL11.glColor4f(1F, 1F, 1F, 1.0F);
        	GL11.glRotatef(180.0F - minecraft.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-minecraft.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GlStateManager.disableLighting();
            
            minecraft.getTextureManager().bindTexture(new ResourceLocation(entity.getTex()));
            entity.model.renderPart("mat1");
            
            GlStateManager.enableLighting();
            
            //GL11.glDisable(GL11.GL_BLEND);
        }
        GlStateManager.translate((float)-x, (float)-y, (float)-z);
        GlStateManager.popMatrix();
        
        
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return boatTextures0;
	}
	
}