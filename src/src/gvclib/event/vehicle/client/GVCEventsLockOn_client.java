package gvclib.event.vehicle.client;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.mod_GVCLib;
import gvclib.entity.EntityBBase;
import gvclib.entity.EntityB_Missile;
import gvclib.entity.Entity_Flare;
import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.entity.living.EntityVehicleBase;
import gvclib.event.GVCSoundEvent;
import gvclib.item.ItemGunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

public class GVCEventsLockOn_client {
	
	
	private static final ResourceLocation lockon = new ResourceLocation("gvclib:textures/marker/lockon.png");
	private static final ResourceLocation lock = new ResourceLocation("gvclib:textures/marker/lock.png");
    private static final IModelCustom doll_class = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/marker/class.mqo"));
    
   
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void antiLockOn(RenderGameOverlayEvent.Text event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ScaledResolution scaledresolution = new ScaledResolution(minecraft);
		int i = scaledresolution.getScaledWidth();
		int j = scaledresolution.getScaledHeight();
		//if(target != null && target instanceof EntityLivingBase) 
		{
			if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {// 1
				EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
				if(vehicle.render_rader  && vehicle.renderhud) {
					double x = vehicle.posX;
					double y = vehicle.posY;
					double z = vehicle.posZ;
					double han = 80;
					AxisAlignedBB aabb = (new AxisAlignedBB((double) (x - han), (double) (y - han),
							(double) (z - han),
							(double) (x + han), (double) (y + han), (double) (z + han)))
									.grow(han);
			        List llist = vehicle.world.getEntitiesWithinAABB(Entity.class, aabb);
					/*List llist = vehicle.world.getEntitiesWithinAABBExcludingEntity(vehicle, vehicle.getEntityBoundingBox()
		            		.expand(vehicle.motionX, vehicle.motionY, vehicle.motionZ).grow(100.0D));*/
			        boolean alert1 = false;
					boolean alert2 = false;
		            if(llist!=null){
		                for (int lj = 0; lj < llist.size(); lj++) {
		                	
		                	Entity entity1 = (Entity)llist.get(lj);
		                	if (entity1 != null && (entity1 instanceof EntityBBase))
		                    {
		                		if(entity1 instanceof EntityB_Missile) {
		                			EntityB_Missile missile = (EntityB_Missile) entity1;
		                			if(missile.mitarget != null && (missile.mitarget == vehicle || (vehicle.getControllingPassenger() != null && missile.mitarget == vehicle.getControllingPassenger()))){
		                				alert2 = true;
		                				break;
		                			}
		                		}
		                    }
		                	else if (entity1 != null && entity1 instanceof EntityGVCLivingBase)
                            {
	                			EntityGVCLivingBase enemy = (EntityGVCLivingBase) entity1;
	                			if (entity1.getRidingEntity() instanceof EntityVehicleBase && entity1.getRidingEntity() != null) {
	                				EntityVehicleBase vehicle2 = (EntityVehicleBase) entity1.getRidingEntity();
	                				if((vehicle2.aarader || vehicle2.asrader) && enemy.targetentity != null) {
	                					if(enemy.targetentity == vehicle || (vehicle.getControllingPassenger() != null && enemy.targetentity == vehicle.getControllingPassenger())){
	                						alert1 = true;
	                					}
	                				}
	                			}
                            }
		                }
		            }
		            if(alert2) {
		            	RenderHUDEvent renderevent = new RenderHUDEvent(minecraft);
        				renderevent.renderAntiLockOn(minecraft, vehicle, entityplayer, i, j, 0);
		            }else if(alert1){
		            	RenderHUDEvent renderevent = new RenderHUDEvent(minecraft);
        				renderevent.renderAntiLockOn(minecraft, vehicle, entityplayer, i, j, 1);
		            }
		            
		            
				}
			}
		}
		renderLockOn_new(event);
	  }
	
	
	public void renderLockOn_new(RenderGameOverlayEvent.Text event) {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ScaledResolution scaledresolution = new ScaledResolution(minecraft);
		int i = scaledresolution.getScaledWidth();
		int j = scaledresolution.getScaledHeight();
		{
			List llist = entityplayer.world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.getEntityBoundingBox()
            		.expand(entityplayer.motionX, entityplayer.motionY, entityplayer.motionZ).grow(100.0D));
            if(llist!=null){
                for (int lj = 0; lj < llist.size(); lj++) {
                	
                	Entity entity1 = (Entity)llist.get(lj);
                	if (entity1.canBeCollidedWith() && (entity1 != entityplayer) && entity1 != null)
                    {
                			if (entity1 instanceof EntityLivingBase)
                            {//1
                				EntityLivingBase target = (EntityLivingBase) entity1;
                				
                				ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
                				if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//gun
                					ItemGunBase gun = (ItemGunBase) itemstack.getItem();
                					if (itemstack.hasTagCompound()) {
                						NBTTagCompound nbt = itemstack.getTagCompound();
                						if (nbt.getInteger("missile_target") != 0) {
                							
                							Entity tgtEntity = entityplayer.world.getEntityByID(nbt.getInteger("missile_target"));
                							if (tgtEntity == target) {
                								renderLockOn_render(minecraft, entityplayer, target, scaledresolution, lockon);
                							}
                						}
                					}
                				}///gun
                				
                				{//vehicle
                					NBTTagCompound target_nbt = entity1.getEntityData();
                					if(target_nbt != null && target_nbt.getInteger("lockon") > 0){
                						if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {// 1
                							EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
                							if(vehicle.mitarget == entity1) {
                								renderLockOn_render(minecraft, entityplayer, target, scaledresolution, lockon);
                							}
                						}
                					}
                					
                					/*
                					//　　地上広範囲ロック
                					if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {
                						EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
                						if(vehicle != target && vehicle.aarader && target instanceof IMob) {
                							BlockPos bp = target.world.getHeight(new BlockPos(target.posX, target.posY, target.posZ));
                							if(target.posY > bp.getY() + 10 && !target.onGround)
                							{
                								renderLockOn_render(minecraft, entityplayer, target, scaledresolution, lock);
                							}
                						}
                						if(vehicle != target && vehicle.asrader && target instanceof IMob) {
                							BlockPos bp = target.world.getHeight(new BlockPos(target.posX, target.posY, target.posZ));
                							boolean flag = vehicle.getEntitySenses().canSee(target);
                							if(target.posY < bp.getY() + 5 && flag)
                							{
                								renderLockOn_render(minecraft, entityplayer, target, scaledresolution, lock);
                							}
                						}
                					}
                					/**/
                				}//vehicle
                            }//1
                    }
                }
            }
		}/**/
	}
	
	
	public void renderLockOn_render(Minecraft minecraft, EntityPlayer entityplayer, EntityLivingBase target, ScaledResolution scaledresolution ,ResourceLocation resource) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		RenderManager manager = minecraft.getRenderManager();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		{
			GL11.glPushMatrix();
			{
				double x_offset = 0;
				double y_offset = 0;
				double z_offset = 0;
				Vec3d lock = Vec3d.fromPitchYaw(entityplayer.rotationPitch, entityplayer.rotationYaw);
				if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {
					EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
					float f1 = entityplayer.rotationPitch * (2 * (float) Math.PI / 360);
					if(minecraft.gameSettings.thirdPersonView == 1){
						x_offset = lock.x * (vehicle.ridding_view_x - 60  + 0);//-60
						y_offset = lock.y * (vehicle.ridding_view_y - 40  + 0);//-40
						z_offset = lock.z * (vehicle.ridding_view_z - 60  + 0);//-60
					}else if(minecraft.gameSettings.thirdPersonView == 0){
						x_offset = lock.x * vehicle.ridding_view1_x;
						y_offset = lock.y * vehicle.ridding_view1_y;
						z_offset = lock.z * vehicle.ridding_view1_z;
					}
		//			GL11.glTranslatef((float)x_offset,(float)y_offset, (float)z_offset);
				}
				
				double d5 = target.posX - (entityplayer.posX + x_offset);
				double d7 = target.posZ - (entityplayer.posZ + z_offset);
				double d6 = (target.posY + entityplayer.getEyeHeight()) - (entityplayer.posY + entityplayer.getEyeHeight() + y_offset);
				double ddx = Math.abs(d5);
				double ddz = Math.abs(d7);
				double dxz = MathHelper.sqrt(d5 * d5 + d7 * d7);
				double dyz = MathHelper.sqrt(d6 * d6 + d7 * d7);
				double dxyz = MathHelper.sqrt(dxz * dxz + d6 * d6);
				
				float pitch = -((float) (Math.atan(d6/dxz)* 180.0D / Math.PI));
				float player_pitch = entityplayer.rotationPitch ;//* 180.0F / (float) Math.PI;
				
				float yaw = - (float) (Math.atan(d5/d7) * 180.0D / Math.PI) %360;
				
				float player_yaw = 0;
				{
					player_yaw = entityplayer.rotationYaw %360;
				}
				
				int offset = 1;
				int offset_player = 1;
				if(Math.abs(yaw) >= 90 && Math.abs(yaw) < 270) {
					offset =  -1;
				}
				if(Math.abs(player_yaw) >= 90 && Math.abs(player_yaw) < 270) {
					offset_player =  -1;
				}
				
				float xyaw = (float)((1) * MathHelper.sin((yaw)* ((float) Math.PI / 180F))) * 4000 * offset  - (float)((1) * MathHelper.sin((player_yaw)* ((float) Math.PI / 180F))) * 4000 * offset_player;
				//float ypitch = (float)((dxyz) * MathHelper.sin((pitch)* ((float) Math.PI / 180F))) * 200 - (float)((dxyz) * MathHelper.sin((player_pitch)* ((float) Math.PI / 180F))) * 200;//20M-200//40M-100//10M-400
				float ypitch = (float)((1) * MathHelper.sin((pitch)* ((float) Math.PI / 180F))) * 4000 - (float)((1) * MathHelper.sin((player_pitch)* ((float) Math.PI / 180F))) * 4000;
				/*{
					FontRenderer fontrenderer = minecraft.fontRenderer;
					int color = 0xFF0000;
					String string_yaw = String.format("%1$.2f", player_yaw);
					String string_pitch = String.format("%1$.2f", player_pitch);
					fontrenderer.drawStringWithShadow("player_yaw:::" + string_yaw, scaledresolution.getScaledWidth()/2 - 10,  scaledresolution.getScaledHeight()/2 -50, color);
					fontrenderer.drawStringWithShadow("player_pitch:::" + string_pitch, scaledresolution.getScaledWidth()/2 - 10,  scaledresolution.getScaledHeight()/2 -60, color);
					
					String string_ypitch = String.format("%1$.2f", ypitch);
					fontrenderer.drawStringWithShadow("ypitch:::" + string_ypitch, scaledresolution.getScaledWidth()/2 - 10,  scaledresolution.getScaledHeight()/2 -70, color);
					String string_xyaw = String.format("%1$.2f", xyaw);
					fontrenderer.drawStringWithShadow("xyaw:::" + string_xyaw, scaledresolution.getScaledWidth()/2 - 10,  scaledresolution.getScaledHeight()/2 -80, color);
					
					String string_ppitch = String.format("%1$.2f", pitch);
					fontrenderer.drawStringWithShadow("ppitch:::" + string_ppitch, scaledresolution.getScaledWidth()/2 - 10,  scaledresolution.getScaledHeight()/2 -90, color);
				}*/
				GL11.glPushMatrix();//111
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
				GuiIngame g = minecraft.ingameGUI;
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
						GlStateManager.DestFactor.ZERO);
				float xpoint = (scaledresolution.getScaledWidth()/2-8)*16;
				float ypoint = (scaledresolution.getScaledHeight()/2-8)*16;
				minecraft.renderEngine.bindTexture(resource);
				GL11.glTranslatef(0.5F,0.5F, 0F);
    			GL11.glScalef(0.0625f, 0.0625f, 1);
    			
    			g.drawTexturedModalRect(xpoint + xyaw,ypoint + ypitch, 0,0, 256, 256);
    			
    			GL11.glPopMatrix();//111
			}
			GL11.glPopMatrix();

			
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		// GL11.glPopAttrib();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void renderVehicle_Rader(RenderLivingEvent.Post event)
	  {
		Minecraft minecraft = Minecraft.getMinecraft();//FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		//ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		Entity target = event.getEntity();
		if(target != null && target instanceof EntityLivingBase) {
			if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {// 1
				EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
				if(vehicle != target && vehicle.aarader && target instanceof IMob) {
					BlockPos bp = target.world.getHeight(new BlockPos(target.posX, target.posY, target.posZ));
					if(target.posY > bp.getY() + 10 && !target.onGround)
					{
						render(event, minecraft, target, entityplayer, lock);
					}
				}
				if(vehicle != target && vehicle.asrader && target instanceof IMob) {
					BlockPos bp = target.world.getHeight(new BlockPos(target.posX, target.posY, target.posZ));
					boolean flag = vehicle.getEntitySenses().canSee(target);
					if(target.posY < bp.getY() + 5 && flag)
					{
						render(event, minecraft, target, entityplayer, lock);
					}
				}
			}
		}
	  }
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	 /*///　01/28削除
		@SideOnly(Side.CLIENT)
	    @SubscribeEvent
		  public void renderLockOn(RenderLivingEvent.Post event)
		  {
			Minecraft minecraft = FMLClientHandler.instance().getClient();
			EntityPlayer entityplayer = minecraft.player;
			ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
			Entity target = event.getEntity();
			if(target != null && target instanceof EntityLivingBase) {
				NBTTagCompound target_nbt = target.getEntityData();
				if(target_nbt != null && target_nbt.getInteger("lockon") > 0){
					if (entityplayer.getRidingEntity() instanceof EntityGVCLivingBase && entityplayer.getRidingEntity() != null) {// 1
						EntityGVCLivingBase vehicle = (EntityGVCLivingBase) entityplayer.getRidingEntity();
						if(vehicle.mitarget == target) {
							render(event, minecraft, target, entityplayer, lockon);
						}
					}
					if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {
						ItemGunBase gun = (ItemGunBase) itemstack.getItem();
						if (itemstack.hasTagCompound()) {
							NBTTagCompound nbt = itemstack.getTagCompound();
							if (nbt.getInteger("missile_target") != 0) {
								Entity tgtEntity = entityplayer.world.getEntityByID(nbt.getInteger("missile_target"));
								if (tgtEntity == target) {
									render(event, minecraft, target, entityplayer, lockon);
								}
							}
						}
					}
				}
			}
		  }*/
	
	
	private void render(RenderLivingEvent.Post event, Minecraft minecraft, Entity entity, EntityPlayer entityplayer, ResourceLocation resource) {
		// System.out.println("3");
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		// GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glTranslatef((float) event.getX(), (float) event.getY(), (float) event.getZ());
		RenderManager manager = minecraft.getRenderManager();
		GlStateManager.rotate(-manager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float)(manager.options.thirdPersonView == 2 ? -1 : 1) * manager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		{
			GlStateManager.depthFunc(519);
			
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(0, entity.height / 2, 0);
				//GL11.glScalef(2.0f * entity.height, 2.0f * entity.height, 2.0f * entity.height);
				GL11.glScalef(2.0f * 2, 2.0f * 2, 2.0f * 2);
				double d5 = entity.posX - entityplayer.posX;
				double d7 = entity.posZ - entityplayer.posZ;
				double d6 = entity.posY - entityplayer.posY;
				double ddx = Math.abs(d5);
				double ddz = Math.abs(d7);
				double dxz = MathHelper.sqrt(d5 * d5 + d7 * d7);
				double dxzy = MathHelper.sqrt(dxz * dxz + d6 * d6);
				GL11.glScalef(1.0F + ((float) dxzy * 0.05F), 1.0F + ((float) dxzy * 0.05F),
						1.0F + ((float) dxzy * 0.05F));
				GlStateManager.disableLighting();
				Minecraft.getMinecraft().renderEngine.bindTexture(resource);
				doll_class.renderPart("mat1");
				GlStateManager.enableLighting();
			}
			GL11.glPopMatrix();

			GlStateManager.depthFunc(515);
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		// GL11.glPopAttrib();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
}
	
	/*
	private void render(RenderLivingEvent.Post event, Minecraft minecraft, Entity entity, EntityPlayer entityplayer, ResourceLocation resource) {
//			 System.out.println("3");
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			// GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			GL11.glTranslatef((float) event.getX(), (float) event.getY(), (float) event.getZ());
			RenderManager manager = minecraft.getRenderManager();
			GlStateManager.rotate(-manager.playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate((float)(manager.options.thirdPersonView == 2 ? -1 : 1) * manager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			{
				GlStateManager.depthFunc(519);
				GlStateManager.disableFog();
				// this.entityOutlineFramebuffer.bindFramebuffer(false);
				RenderHelper.disableStandardItemLighting();
				event.getRenderer().getRenderManager().setRenderOutlines(true);
				// GL11.glutInitDisplayMode(GL11.GLUT_SINGLE | GL11.GLUT_RGBA |
				// GL11.GLUT_DEPTH);
				// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				// GL11.glEnable(GL11.GL_DEPTH_TEST);

				
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(0, entity.height / 2, 0);
					GL11.glScalef(2.0f * entity.height, 2.0f * entity.height, 2.0f * entity.height);
					double d5 = entity.posX - entityplayer.posX;
					double d7 = entity.posZ - entityplayer.posZ;
					double d6 = entity.posY - entityplayer.posY;
					double ddx = Math.abs(d5);
					double ddz = Math.abs(d7);
					double dxz = MathHelper.sqrt(d5 * d5 + d7 * d7);
					double dxzy = MathHelper.sqrt(dxz * dxz + d6 * d6);
					GL11.glScalef(1.0F + ((float) dxzy * 0.05F), 1.0F + ((float) dxzy * 0.05F),
							1.0F + ((float) dxzy * 0.05F));
					GlStateManager.disableLighting();
					Minecraft.getMinecraft().renderEngine.bindTexture(resource);
					doll_class.renderPart("mat1");
					GlStateManager.enableLighting();
				}
				GL11.glPopMatrix();

				// GL11.glDisable(GL11.GL_DEPTH_TEST);
				event.getRenderer().getRenderManager().setRenderOutlines(false);
				RenderHelper.enableStandardItemLighting();
				GlStateManager.depthMask(false);
				// this.entityOutlineShader.render(event.getPartialRenderTick());
				GlStateManager.enableLighting();
				GlStateManager.depthMask(true);
				GlStateManager.enableFog();
				GlStateManager.enableBlend();
				GlStateManager.enableColorMaterial();
				GlStateManager.depthFunc(515);
				GlStateManager.enableDepth();
				GlStateManager.enableAlpha();
			}
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			// GL11.glPopAttrib();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
	}
	*/
}
