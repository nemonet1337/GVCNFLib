package gvclib.event;

import org.lwjgl.opengl.GL11;

import gvclib.mod_GVCLib;
import gvclib.event.vehicle.client.RenderHUDEvent;
import gvclib.item.ItemArmor_Base;
import gvclib.item.ItemArmor_NewObj;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GVCEventOverlay {

	public boolean zoomtype;
	
	
	
	/*@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void tes(DrawScreenEvent.Pre event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		  World world = FMLClientHandler.instance().getWorldClient();
		  ScaledResolution scaledresolution = new ScaledResolution(minecraft);
	        int i = scaledresolution.getScaledWidth();
	        int j = scaledresolution.getScaledHeight();
			FontRenderer fontrenderer = minecraft.fontRenderer;
	        minecraft.entityRenderer.setupOverlayRendering();
	        
	       // fontrenderer.drawStringWithShadow("TEST", i /2- 40,  j/2 + 35, 0xFFFFFF);
	  }*/
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void renderfovGun(FOVUpdateEvent event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = event.getEntity();
		ItemStack itemstack = entityplayer.getHeldItemMainhand();
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			if ((entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null)
					&& (entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemArmor_NewObj)) {
				ItemArmor_NewObj armor = (ItemArmor_NewObj) entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
				
			}
		}
	  }
	
	@SideOnly(Side.CLIENT)
	  @SubscribeEvent
	  public void onEvent(RenderGameOverlayEvent.Text event)
	  {
		  Minecraft minecraft = FMLClientHandler.instance().getClient();
		  World world = FMLClientHandler.instance().getWorldClient();
		  ScaledResolution scaledresolution = new ScaledResolution(minecraft);
	        int i = scaledresolution.getScaledWidth();
	        int j = scaledresolution.getScaledHeight();
			EntityPlayer entityplayer = minecraft.player;
			ItemStack itemstack = ((EntityPlayer)(entityplayer)).getHeldItemMainhand();
			FontRenderer fontrenderer = minecraft.fontRenderer;
	        minecraft.entityRenderer.setupOverlayRendering();
	        //OpenGlHelper.
	        
	        boolean cx = true;
	        if(entityplayer != null && mod_GVCLib.cfg_debag) {
	        	RenderHUDEvent renderevent = new RenderHUDEvent(minecraft);
	        	renderevent.renderBenri(minecraft, entityplayer, i, j);
	        }
	        
	        
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) 
		{
        	if ((entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null)
					&& (entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemArmor_NewObj)) {
				ItemArmor_NewObj armor = (ItemArmor_NewObj) entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
				if(armor.gazo &&armor. gazotex != null){
					if(minecraft.gameSettings.thirdPersonView != 0){
						if(armor.tps){
							//this.renderPumpkinBlur(minecraft,scaledresolution, "handmadearmors:textures/misc/" + armor.gazotex, armor.tou);
							this.renderPumpkinBlur(minecraft,scaledresolution, armor.gazotex, armor.tou);
						}
					}else{
						this.renderPumpkinBlur(minecraft,scaledresolution, armor.gazotex, armor.tou);
					}
				}
			}
        	if ((entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null)
					&& (entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemArmor_Base)) {
        		ItemArmor_Base armor = (ItemArmor_Base) entityplayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
				if(armor.gazo &&armor. gazotex != null){
					if(minecraft.gameSettings.thirdPersonView != 0){
						if(armor.tps){
							//this.renderPumpkinBlur(minecraft,scaledresolution, "handmadearmors:textures/misc/" + armor.gazotex, armor.tou);
							this.renderPumpkinBlur(minecraft,scaledresolution, armor.gazotex, armor.tou);
						}
					}else{
						this.renderPumpkinBlur(minecraft,scaledresolution, armor.gazotex, armor.tou);
					}
				}
			}
        	
        	GuiIngame g  = minecraft.ingameGUI;
        	GL11.glPushMatrix();
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    		NBTTagCompound nbts = entityplayer.getEntityData();
    		int nb = nbts.getInteger("hitentity");
    		if(nb >= 1)
    		{
    			minecraft.entityRenderer.setupOverlayRendering();
    			minecraft.renderEngine.bindTexture(new ResourceLocation("gvclib:textures/items/hit.png"));
    			GL11.glTranslatef(0.5F,0F, 0F);
    			GL11.glScalef(0.0625f, 0.0625f, 1);
    			g.drawTexturedModalRect((scaledresolution.getScaledWidth()/2-8)*16,
    					(scaledresolution.getScaledHeight()/2-8)*16, 0,0, 256, 256);
    		nbts.setInteger("hitentity", nb-1);
    		}
    		GL11.glPopMatrix();
    		
    		
    		GL11.glPushMatrix();
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    		int nbd = nbts.getInteger("hitentitydead");
    		if(nbd >= 1)
    		{
    			minecraft.entityRenderer.setupOverlayRendering();
    			minecraft.renderEngine.bindTexture(new ResourceLocation("gvclib:textures/items/hitdead.png"));
    			GL11.glTranslatef(0.5F,0F, 0F);
    			GL11.glScalef(0.0625f, 0.0625f, 1);
    			g.drawTexturedModalRect((scaledresolution.getScaledWidth()/2-8)*16,
    					(scaledresolution.getScaledHeight()/2-8)*16, 0,0, 256, 256);
    		nbts.setInteger("hitentitydead", nbd-1);
    		}
    		GL11.glPopMatrix();
    		
    		GL11.glPushMatrix();
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    		int nbdh = nbts.getInteger("hitentity_headshot");
    		if(nbdh >= 1)
    		{
    			fontrenderer.drawStringWithShadow("HEADSHOT", scaledresolution.getScaledWidth()/2 - 15,  scaledresolution.getScaledHeight()/2+15, 0xFFFFFF);
    		nbts.setInteger("hitentity_headshot", nbdh-1);
    		}
    		GL11.glPopMatrix();
    		
    		
    		//lockon
    		/*{
    			GL11.glPushMatrix();
        		GL11.glEnable(GL11.GL_BLEND);
        		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Entity entity = null;
            List llist = entityplayer.world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.getEntityBoundingBox()
            		.expand(entityplayer.motionX, entityplayer.motionY, entityplayer.motionZ).grow(100.0D));
            if(llist!=null){
                for (int lj = 0; lj < llist.size(); lj++) {
                	
                	Entity entity1 = (Entity)llist.get(lj);
                	if (entity1.canBeCollidedWith() && (entity1 != entityplayer))
                    {
                		if (!(entity1 instanceof EntityPlayer) && (entity1 != entityplayer)
                				&& entity1 != null)
                        {
                			if (entity1 instanceof EntityLivingBase)
                            {
    								if(entity1 instanceof EntityLivingBase){
    		            				EntityLivingBase en = (EntityLivingBase) entity1;
    		            				if(en.isPotionActive(MobEffects.GLOWING)){
    								minecraft.renderEngine.bindTexture(new ResourceLocation("gvclib:textures/items/hit.png"));
    		            	    			//GL11.glTranslatef(0.5F,0F, 0F);
    		            	    			//GL11.glScalef(0.0625f, 0.0625f, 1);
    		            	    			g.drawTexturedModalRect((scaledresolution.getScaledWidth()/2-8),
    		            	    					(scaledresolution.getScaledHeight()/2-8), 0,0, 256, 256);
    								}
                                 }
                            }
                        }
                    }
                }
            }
        		
        		GL11.glPopMatrix();
    		}*/
        	
		}
        
	  }
	
	@SideOnly(Side.CLIENT)
	protected void renderPumpkinBlur(Minecraft minecraft, ScaledResolution scaledRes, String adss, float tou) {
		//GL11.glPushMatrix();// 12
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		//GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, tou);
		GlStateManager.disableAlpha();
		minecraft.getTextureManager().bindTexture(new ResourceLocation(adss));
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0.0D, (double)scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
		GlStateManager.enableAlpha();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		//GL11.glPopMatrix();// 12
	}
	
	
	
	
	protected void thisrenderCrosshairs()
  {
      bind(Gui.ICONS);
      GL11.glEnable(GL11.GL_BLEND);
      OpenGlHelper.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR, 1, 0);
      OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
      GL11.glDisable(GL11.GL_BLEND);
  }
	private void bind(ResourceLocation res)
  {
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
  }
	
}