package gvclib.event.gun;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.mod_GVCLib;
import gvclib.item.ItemAttachment;
import gvclib.item.ItemGunBase;
import gvclib.item.ItemGun_Grenade;
import gvclib.item.ItemGun_SWORD;
import gvclib.item.ItemGun_Shield;
import gvclib.item.gunbase.IGun_Sword;
import gvclib.network.GVCLMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GVCEventsGunRender_First {
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergun(RenderHandEvent event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		ItemStack itemstackl = ((EntityPlayer) (entityplayer)).getHeldItemOffhand();
		
		
		if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase && itemstack.hasTagCompound()) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
			{
				this.rendermain(entityplayer, itemstack, true);
			}
		}//item
		if (!itemstackl.isEmpty()  && itemstackl.getItem() instanceof ItemGunBase && itemstackl.hasTagCompound()) {//item
			ItemGunBase gun = (ItemGunBase) itemstackl.getItem();
			{
				this.rendermain(entityplayer, itemstackl, false);
			}
		}//item
	  }
	
	float usemax;
	
	private void rendermain(EntityPlayer entityplayer, ItemStack itemstack, boolean side){
		int xxx = 1;
		if(!side){
			xxx = -1;
		}
		
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		ItemGunBase gun = (ItemGunBase) itemstack.getItem();
		gun.ModelLoad();
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		
		boolean am_sight = nbt.getBoolean("am_sight");////
		
			/*byte lb = nbt.getByte("Bolt");
			if (lb <= 0) {
//				if (pReset) resetBolt(pItemstack);
				recoiled =  false;//true
			} else {
//				nbt.setByte("Bolt", --lb);
				recoiled =  true;//false
			}*/
		
		
		
		boolean left = nbt.getBoolean("LeftClick");
		boolean right = nbt.getBoolean("RightClick");
		boolean sidel =false;
		boolean sider = false;
		/*if(!side && (left || entityplayer.isSneaking()) && itemstack.getItem() instanceof ItemGun_Shield){
			sidel = true;
		}*/
		/*if(!side && (entityplayer.isSneaking()) && itemstack.getItem() instanceof ItemGun_Shield){
			sidel = true;
		}
		if(side &&  itemstack.getItem() instanceof ItemGun_Shield && entityplayer.getActiveItemStack() == itemstack){
			sider = true;
		}*/
		boolean can_sd = nbt.getBoolean("CAN_SD");
		if(side && can_sd) {
			sider = true;
		}else if(!side && can_sd){
			sidel = true;
		}
		
		if(minecraft.gameSettings.thirdPersonView == 0 && gun.obj_true){
			if(mod_GVCLib.cfg_optifine)
			{
				//GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180-entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
				//GL11.glRotatef(-entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
				if(entityplayer.isSneaking()){
					GL11.glTranslatef(0F,(float)mod_GVCLib.cfg_optifineys,0F);
					GL11.glRotatef(-entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0F,-(float)mod_GVCLib.cfg_optifineys,0F);
					GL11.glTranslatef(0F,(float)mod_GVCLib.cfg_optifineys,-0.05F);
				}else{
	        	GL11.glTranslatef(0F,(float)mod_GVCLib.cfg_optifiney,0F);
	        	GL11.glRotatef(-entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F,-(float)mod_GVCLib.cfg_optifiney,0F);
				GL11.glTranslatef(0F,(float)mod_GVCLib.cfg_optifiney,-0.05F);
				}
			}
			GL11.glPushMatrix();//guns
			
			GL11.glColor4f(1F, 1F, 1F, 1F);
			
			
			//GlStateManager.enableLighting();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
	//		GL11.glScalef(0.5F, 0.5F, 0.5F);
	//		GL11.glScalef(0.5F, 0.5F, 0.5F);
	//		GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			//8/18
		/*	if(side && entityplayer.getHeldItemOffhand() == null && !(itemstack.getItem() instanceof ItemGun_SWORD))
			{
				float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*5);
				GL11.glRotatef(f1*30, -1.0F, 0.0F, 1.0F);
			}*/
			/*if(side && (itemstack.getItem() instanceof ItemGun_SWORD || itemstack.getItem() instanceof IGun_Sword))
			{
				/*float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*5);
				GL11.glRotatef(f1*30, -1.0F, 0.0F, 1.0F);****
				float f1 = entityplayer.swingProgress;
				if(gun.newreload){
					swingmotion(gun, f1, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
							gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
							gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
				}
			}*/
			/*if(!side && (itemstack.getItem() instanceof ItemGun_SWORD || itemstack.getItem() instanceof IGun_Sword))
			{
				float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*5);
				GL11.glRotatef(f1*30, -1.0F, 0.0F, -1.0F);
			}*/
		/*	if(side && gun.knife)
			{
				float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*6);
				GL11.glRotatef(f1*15, -1.0F, 0.0F, -1.0F);
			}*/
			/*if(!side && gun.knife)
			{
				if(entityplayer.getHeldItemMainhand().isEmpty()) {
					float f1 = entityplayer.swingProgress;
					GL11.glTranslatef(0,0, -f1*6);
					GL11.glRotatef(f1*15, -1.0F, 0.0F, -1.0F);
				}
				else if( (!(entityplayer.getHeldItemMainhand().getItem() instanceof ItemSword) 
						&& !(entityplayer.getHeldItemMainhand().getItem() instanceof ItemGun_SWORD))){
					float f1 = entityplayer.swingProgress;
					GL11.glTranslatef(0,0, -f1*6);
					GL11.glRotatef(f1*15, -1.0F, 0.0F, -1.0F);
				}else if(entityplayer.getHeldItemMainhand().getItem() instanceof ItemGunBase) {
					ItemGunBase gun2 = (ItemGunBase) entityplayer.getHeldItemMainhand().getItem();
					if(!gun2.knife) {
						float f1 = entityplayer.swingProgress;
						GL11.glTranslatef(0,0, -f1*6);
						GL11.glRotatef(f1*15, -1.0F, 0.0F, -1.0F);
					}
				}
				
			}*/
			if(itemstack.getItem() instanceof ItemGun_Grenade)
			{
				float use = 0;
				use = (float)(itemstack.getMaxItemUseDuration() - entityplayer.getItemInUseCount()) / 1.0F;
				if(use > 30 && entityplayer.isHandActive()) {
					use = 30;
				}
				GL11.glRotatef(use, 0.0F, -1.0F * xxx, 0.0F);
				GL11.glRotatef(use, 1.0F, 0.0F, 0.0F);
			}
			
			if(!recoiled) {
				GL11.glTranslatef(0.0F, 0.0F, -gun.model_cock_z*0.1F);
				GL11.glRotatef(-gun.jump * 1, 1.0F, 0.0F, 0.0F);
			}
			//if (lb == gun.getCycleCount(itemstack))
			{
				//GL11.glRotatef(gun.jump * 20, 1.0F, 0.0F, 0.0F);
			}
			
			NBTTagCompound nbt_entity = entityplayer.getEntityData();
			int setting = nbt_entity.getInteger("Gun_Setting");
			if(nbt_entity != null && setting == 5) {
				GL11.glTranslatef(gun.model_x * xxx -0.5F,gun.model_y, gun.model_z);
				GL11.glTranslatef(gun.Sprintoffsetx * xxx, gun.Sprintoffsety, gun.Sprintoffsetz);
				GL11.glRotatef(-gun.Sprintrotationx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(gun.Sprintrotationy * xxx+180, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(gun.Sprintrotationz, 0.0F, 0.0F, 1.0F);
			}
			else
			if (itemstack.getItemDamage() == itemstack.getMaxDamage()) {//1s
				GL11.glTranslatef(gun.model_x * xxx ,gun.model_y, gun.model_z + 1);//1.5,-2,-2.5
				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				if(gun.newreload){
					if (gun.reloadmotion == 0) {
						for (int kais = 0; kais <= gun.mat_rk_0; ++kais) {
							renderreload(gun, kais, gun.mat_r_0);
						}
					} else {
						newrenderreload(gun, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
								gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
								gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
					}
				}else{
					GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
				}
				
			}
			else if(sidel || sider){//ADS
				GL11.glTranslatef(ADS_X(am_sight, gun, nbt) * xxx,ADS_Y(am_sight, gun, nbt), ADS_Z(am_sight, gun, nbt));//0,-1.7,-1.5
				GL11.glRotatef(gun.rotationx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(gun.rotationy * xxx, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(gun.rotationz * xxx, 0.0F, 0.0F, 1.0F);
			}
			else if(entityplayer.isSneaking()){//ADS
				if(side && !entityplayer.getHeldItemOffhand().isEmpty()){
					GL11.glTranslatef(gun.model_x * xxx ,gun.model_y, gun.model_z + 1);//1.5,-2,-2.5
					GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(00F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
				}else if(!side && !entityplayer.getHeldItemMainhand().isEmpty()){
					GL11.glTranslatef(gun.model_x * xxx ,gun.model_y, gun.model_z + 1);//1.5,-2,-2.5
					GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(00F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
				}else{
					GL11.glTranslatef(ADS_X(am_sight, gun, nbt) * xxx,ADS_Y(am_sight, gun, nbt), ADS_Z(am_sight, gun, nbt));//0,-1.7,-1.5
					GL11.glRotatef(gun.rotationx, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(gun.rotationy * xxx, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(gun.rotationz * xxx, 0.0F, 0.0F, 1.0F);
				}
			}else if(entityplayer.isSprinting()){
				GL11.glTranslatef(gun.model_x * xxx -0.5F,gun.model_y, gun.model_z);
				GL11.glTranslatef(gun.Sprintoffsetx * xxx, gun.Sprintoffsety, gun.Sprintoffsetz);
				GL11.glRotatef(-gun.Sprintrotationx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(gun.Sprintrotationy * xxx+180, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(gun.Sprintrotationz, 0.0F, 0.0F, 1.0F);
				
			}
			else{
				
			GL11.glTranslatef(gun.model_x * xxx ,gun.model_y, gun.model_z + 1);//1.5,-2,-2.5
			
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-3F, 1.0F, 0.0F, 0.0F);
			}//1e
			
			
			if(side && entityplayer.getHeldItemOffhand() == null && !(itemstack.getItem() instanceof ItemGun_SWORD|| itemstack.getItem() instanceof IGun_Sword))
			{
				float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*5);
				GL11.glRotatef(f1*30, -1.0F, 0.0F, 1.0F);
			}
			if(side && (itemstack.getItem() instanceof ItemGun_SWORD || itemstack.getItem() instanceof IGun_Sword))
			{
				/*float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(0,0, -f1*5);
				GL11.glRotatef(f1*30, -1.0F, 0.0F, 1.0F);*/
				float f1 = entityplayer.swingProgressInt;
				if(gun.newreload && f1 != 0){
					swingmotion(gun, f1, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
							gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
							gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
				}else {
					GL11.glTranslatef(0,0, -f1*5);
					GL11.glRotatef(f1*30, -1.0F, 0.0F, 1.0F);
				}
			}
			
			
			boolean zoomrender;
			if(am_sight){
				//zoomrender = !gun.am_zoomrender;
				zoomrender = !(nbt.getBoolean("am_zoomrender"));
			}else{
				if(gun.true_mat4){
					zoomrender = !gun.model_zoomrenderr;
				}else if(gun.true_mat5){
					zoomrender = !gun.model_zoomrenders;
				}else{
					zoomrender = !gun.model_zoomrender;
				}
			}
			
			if(gun.recoilmotion && !cocking && itemstack.getItemDamage() != itemstack.getMaxDamage()) {
				newrenderrecoil(gun, itemstack, gun.recoilmat0, gun.recoilmat0_time, gun.recoilmat0_xpoint, gun.recoilmat0_ypoint,
						gun.recoilmat0_zpoint, gun.recoilmat0_xrote, gun.recoilmat0_yrote, gun.recoilmat0_zrote,
						gun.recoilmat0_xmove, gun.recoilmat0_ymove, gun.recoilmat0_zmove);
			}
			
			GL11.glPushMatrix();//guns
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
			if(entityplayer.isSneaking()){
				//if(gun.scopezoom > 1.1F && zoomrender){
				if(zoomrender){
				}else{
					if(itemstack.getItemDamage() == itemstack.getMaxDamage()) {
						this.rendermat_reload(entityplayer, itemstack, gun);//rendermat
					}else {
						if(gun.recoilmotion && !cocking) {
							this.rendermat_recoil(entityplayer, itemstack, gun);//rendermat
						}else {
							this.rendermat(entityplayer, itemstack, gun);//rendermat
						}
					}
				}
			}else{
				if(gun.reloadmotion_test) {
					this.rendermat_reloadtest(entityplayer, itemstack, gun);//rendermat
				}else {
					if(itemstack.getItemDamage() == itemstack.getMaxDamage()) {
						this.rendermat_reload(entityplayer, itemstack, gun);//rendermat
					}else {
						if(gun.recoilmotion && !cocking) {
							this.rendermat_recoil(entityplayer, itemstack, gun);//rendermat
						}else {
							this.rendermat(entityplayer, itemstack, gun);//rendermat
						}
					}
				}
			}
			GL11.glPopMatrix();//gune
			
			GL11.glPushMatrix();//arms
			ResourceLocation resourcelocation = ((AbstractClientPlayer)entityplayer).getLocationSkin();
			if (resourcelocation == null)
	        {
	            resourcelocation = new ResourceLocation("textures/entity/steve.png");
	        }
			if(mod_GVCLib.arm_lmm) {
				 resourcelocation = new ResourceLocation("gvclib:textures/model/arm/lmm_tex.png");
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			if(entityplayer.isSneaking()){
				//if(gun.scopezoom > 1.1F && zoomrender){
				if(zoomrender){
				}else{
					if(gun.canarm) {
						this.renderarm(entityplayer, itemstack, gun);//renderarm
					}
				}
			}else{
				if(gun.canarm) {
					if(gun.reloadmotion_test) {
						this.renderarm_reloattest(entityplayer, itemstack, gun);//renderarm
					}else {
						this.renderarm(entityplayer, itemstack, gun);//renderarm
					}
				}
			}
			
			GL11.glPopMatrix();//arme
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			//GlStateManager.disableLighting();
			
			GL11.glColor4f(1F, 1F, 1F, 1F);
			
			GL11.glPopMatrix();//gune
			
			
			nbt.setBoolean("Recoiled", true);
		}
	}
	
	
	public static void rendermat(EntityLivingBase entityplayer, ItemStack itemstack, ItemGunBase gun){
		NBTTagCompound nbt = itemstack.getTagCompound();
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		GL11.glPushMatrix();//glstrt0
		if(gun.rendering_light) {
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT1);
		}
		
		/*byte lb = nbt.getByte("Bolt");
		if (lb <= 0) {
//			if (pReset) resetBolt(pItemstack);
			recoiled =  true;//true
		} else {
//			nbt.setByte("Bolt", --lb);
			recoiled =  false;//false
		}*/
		
		
		GL11.glPushMatrix();//glstrt2
		
		{
			GL11.glPushMatrix();//glstrt2
            gun.obj_model.renderPart("mat1");
            gun.obj_model.renderPart("mat100");
            {
            	if(gun.rendering_light) {
        			GL11.glDisable(GL11.GL_LIGHT1);
        			GL11.glDisable(GL11.GL_LIGHTING);
        		}
            	gun.obj_model.renderPart("mat1_dot");
            	if(gun.rendering_light) {
            		GL11.glEnable(GL11.GL_LIGHTING);
        			GL11.glEnable(GL11.GL_LIGHT1);
        		}
            }
			GL11.glPopMatrix();//glend2
		}
		//gun.obj_model.renderPart("mat1");
		GL11.glPopMatrix();//glend2
		
		Layermat.renderattachment(entityplayer, itemstack, gun);
		if (!recoiled)
		{
			Layermat.mat31mat32(gun, itemstack, true);
			
			{
				GL11.glTranslatef(0.0F, 0.0F, gun.model_cock_z);//0, 0, -0.4
				gun.obj_model.renderPart("mat2");
				{
	            	if(gun.rendering_light) {
	        			GL11.glDisable(GL11.GL_LIGHT1);
	        			GL11.glDisable(GL11.GL_LIGHTING);
	        		}
	            	gun.obj_model.renderPart("mat2_dot");
	            	if(gun.rendering_light) {
	            		GL11.glEnable(GL11.GL_LIGHTING);
	        			GL11.glEnable(GL11.GL_LIGHT1);
	        		}
	            }
				if(gun.mat2) {
					Layermat.rendersight(entityplayer, itemstack, gun);
				}
				GL11.glTranslatef(0.0F, 0.0F, -gun.model_cock_z);
			}
			if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
				Layermat.mat25(itemstack, gun, false, cockingtime);
			}else {
				Layermat.mat25(itemstack, gun, true, cockingtime);
			}
			if(!gun.mat2) {
				Layermat.rendersight(entityplayer, itemstack, gun);
			}
		}else{
			Layermat.mat31mat32(gun, itemstack, false);
			if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
				gun.obj_model.renderPart("mat2");
				{
	            	if(gun.rendering_light) {
	        			GL11.glDisable(GL11.GL_LIGHT1);
	        			GL11.glDisable(GL11.GL_LIGHTING);
	        		}
	            	gun.obj_model.renderPart("mat2_dot");
	            	if(gun.rendering_light) {
	            		GL11.glEnable(GL11.GL_LIGHTING);
	        			GL11.glEnable(GL11.GL_LIGHT1);
	        		}
	            }
				Layermat.mat25(itemstack, gun, false, cockingtime);
				Layermat.rendersight(entityplayer, itemstack, gun);
			}
		}
		
		if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
			gun.obj_model.renderPart("mat3");
		}
		if(gun.rendering_light) {
			GL11.glDisable(GL11.GL_LIGHT1);
			GL11.glDisable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();//glend0
	}
	public static void rendermat_reload(EntityLivingBase entityplayer, ItemStack itemstack, ItemGunBase gun){
		NBTTagCompound nbt = itemstack.getTagCompound();
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		GL11.glPushMatrix();//glstrt0
		if(gun.rendering_light) {
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT1);
		}
		
		/*
		newrenderreload(gun, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
				gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
				gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
		*/
		
		{
			GL11.glPushMatrix();//glstrt2
			Layermat.renderattachment(entityplayer, itemstack, gun);
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat1, gun.remat1_time, gun.remat1_xpoint, gun.remat1_ypoint,
					gun.remat1_zpoint, gun.remat1_xrote, gun.remat1_yrote, gun.remat1_zrote,
					gun.remat1_xmove, gun.remat1_ymove, gun.remat1_zmove);
			gun.obj_model.renderPart("mat1");
			gun.obj_model.renderPart("mat2_dot");
			gun.obj_model.renderPart("mat100");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat2, gun.remat2_time, gun.remat2_xpoint, gun.remat2_ypoint,
					gun.remat2_zpoint, gun.remat2_xrote, gun.remat2_yrote, gun.remat2_zrote,
					gun.remat2_xmove, gun.remat2_ymove, gun.remat2_zmove);
			gun.obj_model.renderPart("mat2");
			gun.obj_model.renderPart("mat2_dot");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat3, gun.remat3_time, gun.remat3_xpoint, gun.remat3_ypoint,
					gun.remat3_zpoint, gun.remat3_xrote, gun.remat3_yrote, gun.remat3_zrote,
					gun.remat3_xmove, gun.remat3_ymove, gun.remat3_zmove);
			gun.obj_model.renderPart("mat3");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat22, gun.remat22_time, gun.remat22_xpoint, gun.remat22_ypoint,
					gun.remat22_zpoint, gun.remat22_xrote, gun.remat22_yrote, gun.remat22_zrote,
					gun.remat22_xmove, gun.remat22_ymove, gun.remat22_zmove);
			gun.obj_model.renderPart("mat22");
			Layermat.rendersight(entityplayer, itemstack, gun);
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat24, gun.remat24_time, gun.remat24_xpoint, gun.remat24_ypoint,
					gun.remat24_zpoint, gun.remat24_xrote, gun.remat24_yrote, gun.remat24_zrote,
					gun.remat24_xmove, gun.remat24_ymove, gun.remat24_zmove);
			gun.obj_model.renderPart("mat24");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat25, gun.remat25_time, gun.remat25_xpoint, gun.remat25_ypoint,
					gun.remat25_zpoint, gun.remat25_xrote, gun.remat25_yrote, gun.remat25_zrote,
					gun.remat25_xmove, gun.remat25_ymove, gun.remat25_zmove);
			gun.obj_model.renderPart("mat25");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat311, gun.remat31_time, gun.remat31_xpoint, gun.remat31_ypoint,
					gun.remat31_zpoint, gun.remat31_xrote, gun.remat31_yrote, gun.remat31_zrote,
					gun.remat31_xmove, gun.remat31_ymove, gun.remat31_zmove);
			gun.obj_model.renderPart("mat31");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat32, gun.remat32_time, gun.remat32_xpoint, gun.remat32_ypoint,
					gun.remat32_zpoint, gun.remat32_xrote, gun.remat32_yrote, gun.remat32_zrote,
					gun.remat32_xmove, gun.remat32_ymove, gun.remat32_zmove);
			gun.obj_model.renderPart("mat32");
			GL11.glPopMatrix();//glend2
		}
		
		if(gun.rendering_light) {
			GL11.glDisable(GL11.GL_LIGHT1);
			GL11.glDisable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();//glend0
	}
	
	
	public static void rendermat_recoil(EntityLivingBase entityplayer, ItemStack itemstack, ItemGunBase gun){
		NBTTagCompound nbt = itemstack.getTagCompound();
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		GL11.glPushMatrix();//glstrt0
		if(gun.rendering_light) {
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT1);
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			Layermat.renderattachment(entityplayer, itemstack, gun);
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat1, gun.recoilmat1_time, gun.recoilmat1_xpoint, gun.recoilmat1_ypoint,
					gun.recoilmat1_zpoint, gun.recoilmat1_xrote, gun.recoilmat1_yrote, gun.recoilmat1_zrote,
					gun.recoilmat1_xmove, gun.recoilmat1_ymove, gun.recoilmat1_zmove);
			gun.obj_model.renderPart("mat1");
			gun.obj_model.renderPart("mat1_dot");
			gun.obj_model.renderPart("mat100");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat2, gun.recoilmat2_time, gun.recoilmat2_xpoint, gun.recoilmat2_ypoint,
					gun.recoilmat2_zpoint, gun.recoilmat2_xrote, gun.recoilmat2_yrote, gun.recoilmat2_zrote,
					gun.recoilmat2_xmove, gun.recoilmat2_ymove, gun.recoilmat2_zmove);
			gun.obj_model.renderPart("mat2");
			gun.obj_model.renderPart("mat2_dot");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat3, gun.recoilmat3_time, gun.recoilmat3_xpoint, gun.recoilmat3_ypoint,
					gun.recoilmat3_zpoint, gun.recoilmat3_xrote, gun.recoilmat3_yrote, gun.recoilmat3_zrote,
					gun.recoilmat3_xmove, gun.recoilmat3_ymove, gun.recoilmat3_zmove);
			gun.obj_model.renderPart("mat3");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat22, gun.recoilmat22_time, gun.recoilmat22_xpoint, gun.recoilmat22_ypoint,
					gun.recoilmat22_zpoint, gun.recoilmat22_xrote, gun.recoilmat22_yrote, gun.recoilmat22_zrote,
					gun.recoilmat22_xmove, gun.recoilmat22_ymove, gun.recoilmat22_zmove);
			gun.obj_model.renderPart("mat22");
			Layermat.rendersight(entityplayer, itemstack, gun);
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat24, gun.recoilmat24_time, gun.recoilmat24_xpoint, gun.recoilmat24_ypoint,
					gun.recoilmat24_zpoint, gun.recoilmat24_xrote, gun.recoilmat24_yrote, gun.recoilmat24_zrote,
					gun.recoilmat24_xmove, gun.recoilmat24_ymove, gun.recoilmat24_zmove);
			gun.obj_model.renderPart("mat24");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat25, gun.recoilmat25_time, gun.recoilmat25_xpoint, gun.recoilmat25_ypoint,
					gun.recoilmat25_zpoint, gun.recoilmat25_xrote, gun.recoilmat25_yrote, gun.recoilmat25_zrote,
					gun.recoilmat25_xmove, gun.recoilmat25_ymove, gun.recoilmat25_zmove);
			gun.obj_model.renderPart("mat25");
			if(gun.mat25){
				GL11.glPushMatrix();//glstrt2
				Layermat.mat25on(itemstack, gun);
				GL11.glPopMatrix();//glend2
			}
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat311, gun.recoilmat31_time, gun.recoilmat31_xpoint, gun.recoilmat31_ypoint,
					gun.recoilmat31_zpoint, gun.recoilmat31_xrote, gun.recoilmat31_yrote, gun.recoilmat31_zrote,
					gun.recoilmat31_xmove, gun.recoilmat31_ymove, gun.recoilmat31_zmove);
			gun.obj_model.renderPart("mat31");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderrecoil(gun, itemstack, gun.recoilmat32, gun.recoilmat32_time, gun.recoilmat32_xpoint, gun.recoilmat32_ypoint,
					gun.recoilmat32_zpoint, gun.recoilmat32_xrote, gun.recoilmat32_yrote, gun.recoilmat32_zrote,
					gun.recoilmat32_xmove, gun.recoilmat32_ymove, gun.recoilmat32_zmove);
			gun.obj_model.renderPart("mat32");
			GL11.glPopMatrix();//glend2
		}
		
		if(gun.rendering_light) {
			GL11.glDisable(GL11.GL_LIGHT1);
			GL11.glDisable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();//glend0
	}
	
	
	public static void rendermat_reloadtest(EntityLivingBase entityplayer, ItemStack itemstack, ItemGunBase gun){
		NBTTagCompound nbt = itemstack.getTagCompound();
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		
		
		newrenderreload(gun, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
				gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
				gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat1, gun.remat1_time, gun.remat1_xpoint, gun.remat1_ypoint,
					gun.remat1_zpoint, gun.remat1_xrote, gun.remat1_yrote, gun.remat1_zrote,
					gun.remat1_xmove, gun.remat1_ymove, gun.remat1_zmove);
			gun.obj_model.renderPart("mat1");
			gun.obj_model.renderPart("mat100");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat2, gun.remat2_time, gun.remat2_xpoint, gun.remat2_ypoint,
					gun.remat2_zpoint, gun.remat2_xrote, gun.remat2_yrote, gun.remat2_zrote,
					gun.remat2_xmove, gun.remat2_ymove, gun.remat2_zmove);
			gun.obj_model.renderPart("mat2");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat3, gun.remat3_time, gun.remat3_xpoint, gun.remat3_ypoint,
					gun.remat3_zpoint, gun.remat3_xrote, gun.remat3_yrote, gun.remat3_zrote,
					gun.remat3_xmove, gun.remat3_ymove, gun.remat3_zmove);
			gun.obj_model.renderPart("mat3");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat22, gun.remat22_time, gun.remat22_xpoint, gun.remat22_ypoint,
					gun.remat22_zpoint, gun.remat22_xrote, gun.remat22_yrote, gun.remat22_zrote,
					gun.remat22_xmove, gun.remat22_ymove, gun.remat22_zmove);
			gun.obj_model.renderPart("mat22");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat24, gun.remat24_time, gun.remat24_xpoint, gun.remat24_ypoint,
					gun.remat24_zpoint, gun.remat24_xrote, gun.remat24_yrote, gun.remat24_zrote,
					gun.remat24_xmove, gun.remat24_ymove, gun.remat24_zmove);
			gun.obj_model.renderPart("mat24");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat25, gun.remat25_time, gun.remat25_xpoint, gun.remat25_ypoint,
					gun.remat25_zpoint, gun.remat25_xrote, gun.remat25_yrote, gun.remat25_zrote,
					gun.remat25_xmove, gun.remat25_ymove, gun.remat25_zmove);
			gun.obj_model.renderPart("mat25");
			GL11.glPopMatrix();//glend2
		}
		
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat311, gun.remat31_time, gun.remat31_xpoint, gun.remat31_ypoint,
					gun.remat31_zpoint, gun.remat31_xrote, gun.remat31_yrote, gun.remat31_zrote,
					gun.remat31_xmove, gun.remat31_ymove, gun.remat31_zmove);
			gun.obj_model.renderPart("mat31");
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.remat32, gun.remat32_time, gun.remat32_xpoint, gun.remat32_ypoint,
					gun.remat32_zpoint, gun.remat32_xrote, gun.remat32_yrote, gun.remat32_zrote,
					gun.remat32_xmove, gun.remat32_ymove, gun.remat32_zmove);
			gun.obj_model.renderPart("mat32");
			GL11.glPopMatrix();//glend2
		}
		
	}
	private void renderarm_reloattest(EntityPlayer entityplayer, ItemStack itemstack, ItemGunBase gun){
		ItemStack leftitem = ((EntityPlayer) (entityplayer)).getHeldItemOffhand();
		newrenderreload(gun, gun.remat0, gun.remat0_time, gun.remat0_xpoint, gun.remat0_ypoint,
				gun.remat0_zpoint, gun.remat0_xrote, gun.remat0_yrote, gun.remat0_zrote,
				gun.remat0_xmove, gun.remat0_ymove, gun.remat0_zmove);
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.rematrighthand, gun.rematrighthand_time, gun.rematrighthand_xpoint, gun.rematrighthand_ypoint,
					gun.rematrighthand_zpoint, gun.rematrighthand_xrote, gun.rematrighthand_yrote, gun.rematrighthand_zrote,
					gun.rematrighthand_xmove, gun.rematrighthand_ymove, gun.rematrighthand_zmove);
			if(mod_GVCLib.arm_lmm && gun.arm_model_lmm != null) {
				gun.arm_model_lmm.renderPart("rightarm");
			}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
				gun.arm_model_alex.renderPart("rightarm");
			}else {
				gun.arm_model.renderPart("rightarm");
			}
			GL11.glPopMatrix();//glend2
		}
		{
			GL11.glPushMatrix();//glstrt2
			newrenderreload(gun, gun.rematlefthand, gun.rematlefthand_time, gun.rematlefthand_xpoint, gun.rematlefthand_ypoint,
					gun.rematlefthand_zpoint, gun.rematlefthand_xrote, gun.rematlefthand_yrote, gun.rematlefthand_zrote,
					gun.rematlefthand_xmove, gun.rematlefthand_ymove, gun.rematlefthand_zmove);
			if(mod_GVCLib.arm_lmm && gun.arm_model_lmm != null) {
				gun.arm_model_lmm.renderPart("leftarm");
			}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
				gun.arm_model_alex.renderPart("leftarm");
			}else {
				gun.arm_model.renderPart("leftarm");
			}
			GL11.glPopMatrix();//glend2
		}
	}
	
	
	
	public static float ADS_X(boolean am_sight, ItemGunBase gun, NBTTagCompound nbt){
		if(am_sight){
			float model_x_ads = nbt.getFloat("model_x_ads");
			return model_x_ads;
		}else{
			if(gun.true_mat4){
				return gun.model_x_adsr;
			}else if(gun.true_mat5){
				return gun.model_x_adss;
			}else{
				return gun.model_x_ads;
			}
		}
	}
	public static float ADS_Y(boolean am_sight, ItemGunBase gun, NBTTagCompound nbt){
		if(am_sight){
			float model_y_ads = nbt.getFloat("model_y_ads");
			return model_y_ads;
		}else{
			if(gun.true_mat4){
				return gun.model_y_adsr;
			}else if(gun.true_mat5){
				return gun.model_y_adss;
			}else{
				return gun.model_y_ads;
			}
		}
	}
	public static float ADS_Z(boolean am_sight, ItemGunBase gun, NBTTagCompound nbt){
		if(am_sight){
			float model_z_ads = nbt.getFloat("model_z_ads");
			return model_z_ads;
		}else{
			if(gun.true_mat4){
				return gun.model_z_adsr;
			}else if(gun.true_mat5){
				return gun.model_z_adss;
			}else{
				return gun.model_z_ads;
			}
		}
	}
	
	
	private void renderarm(EntityPlayer entityplayer, ItemStack itemstack, ItemGunBase gun){
		ItemStack leftitem = ((EntityPlayer) (entityplayer)).getHeldItemOffhand();
		
		
		
		NBTTagCompound nbt = itemstack.getTagCompound();
		boolean cocking = nbt.getBoolean("Cocking");
		int cockingtime = nbt.getInteger("CockingTime");
		boolean recoiled = nbt.getBoolean("Recoiled");
		int recoiledtime = nbt.getInteger("RecoiledTime");
		{
			if(gun.newreload){
				GL11.glPushMatrix();//glstart1
				if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
					if(!cocking && gun.recoilmotion) {
						newrenderrecoil(gun, itemstack, gun.recoilmatrighthand, gun.recoilmatrighthand_time, gun.recoilmatrighthand_xpoint, gun.recoilmatrighthand_ypoint,
								gun.recoilmatrighthand_zpoint, gun.recoilmatrighthand_xrote, gun.recoilmatrighthand_yrote, gun.recoilmatrighthand_zrote,
								gun.recoilmatrighthand_xmove, gun.recoilmatrighthand_ymove, gun.recoilmatrighthand_zmove);
					}else {
						GL11.glTranslatef(gun.arm_r_posx,gun.arm_r_posy, gun.arm_r_posz);
					}
				}else{
					if (gun.reloadmotion == 0) {
						for (int kais = 0; kais <= gun.mat_rk_rightarm; ++kais) {
							renderreload(gun, kais, gun.mat_r_rightarm);
						}
					} else {
						newrenderreload(gun, gun.rematrighthand, gun.rematrighthand_time, gun.rematrighthand_xpoint, gun.rematrighthand_ypoint,
								gun.rematrighthand_zpoint, gun.rematrighthand_xrote, gun.rematrighthand_yrote, gun.rematrighthand_zrote,
								gun.rematrighthand_xmove, gun.rematrighthand_ymove, gun.rematrighthand_zmove);
					}
				}
				GL11.glScalef(gun.arm_scale_r, gun.arm_scale_r, gun.arm_scale_r);
				if(mod_GVCLib.arm_lmm && gun.arm_model_lmm != null) {
					gun.arm_model_lmm.renderPart("rightarm");
				}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
					gun.arm_model_alex.renderPart("rightarm");
				}else {
					gun.arm_model.renderPart("rightarm");
				}
				GL11.glPopMatrix();//glend1
			}else{
				GL11.glPushMatrix();//glstart1
				if(!recoiled && gun.recoilmotion) {
					newrenderrecoil(gun, itemstack, gun.recoilmatrighthand, gun.recoilmatrighthand_time, gun.recoilmatrighthand_xpoint, gun.recoilmatrighthand_ypoint,
							gun.recoilmatrighthand_zpoint, gun.recoilmatrighthand_xrote, gun.recoilmatrighthand_yrote, gun.recoilmatrighthand_zrote,
							gun.recoilmatrighthand_xmove, gun.recoilmatrighthand_ymove, gun.recoilmatrighthand_zmove);
				}else {
					GL11.glTranslatef(gun.arm_r_posx,gun.arm_r_posy, gun.arm_r_posz);
				}
				GL11.glScalef(gun.arm_scale_r, gun.arm_scale_r, gun.arm_scale_r);
				if(mod_GVCLib.arm_lmm && gun.arm_model_lmm != null) {
					gun.arm_model_lmm.renderPart("rightarm");
				}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
					gun.arm_model_alex.renderPart("rightarm");
				}else {
					gun.arm_model.renderPart("rightarm");
				}
				GL11.glPopMatrix();//glend1
			}
			
		
		
		if (itemstack.getItemDamage() != itemstack.getMaxDamage()) {
			boolean underbarrelweapon = false;
			if(gun.true_mat100){
    			if(!gun.underbarrel.isEmpty() && gun.underbarrel.getItem() instanceof ItemGunBase) {
    				underbarrelweapon = true;
    				ItemGunBase under = (ItemGunBase) gun.underbarrel.getItem();
    				GL11.glTranslatef(-0.2F, -0.225F, -3.2F);
    				GL11.glTranslatef(gun.gripposx, gun.gripposy, gun.gripposz);
    				GL11.glTranslatef(-under.gun_underbarrel_x, -under.gun_underbarrel_y, -under.gun_underbarrel_z);
    				GL11.glTranslatef(under.arm_r_posx, under.arm_r_posy, under.arm_r_posz);
    			}
			}
			if(gun.true_mat9){
				boolean am_grip = nbt.getBoolean("am_grip");
				if(am_grip) {
					/*if(!gun.grip_item.isEmpty() && gun.grip_item.getItem() instanceof ItemAttachment) {
						ItemAttachment am = (ItemAttachment) gun.grip_item.getItem();
						if(am.grip_gripping_point) {
							underbarrelweapon = true;
							GL11.glTranslatef(-0.2F, -0.225F, -3.2F);
							GL11.glTranslatef(gun.gripposx, gun.gripposy, gun.gripposz);
							GL11.glTranslatef(am.grip_gripping_point_x, am.grip_gripping_point_y, am.grip_gripping_point_z);
						}
					}*/
					{
						if(nbt.getBoolean("grip_gripping_point")) {
							underbarrelweapon = !gun.mat25;//true
							GL11.glTranslatef(-0.2F, -0.225F, -3.2F);
							GL11.glTranslatef(nbt.getFloat("gripposx"), nbt.getFloat("gripposy"), nbt.getFloat("gripposz"));
							GL11.glTranslatef(nbt.getFloat("grip_gripping_point_x"), nbt.getFloat("grip_gripping_point_y"), nbt.getFloat("grip_gripping_point_z"));
						}
					}
				}
			}
			if(!underbarrelweapon) {
				if(!cocking && gun.recoilmotion) {
					newrenderrecoil(gun, itemstack, gun.recoilmatlefthand, gun.recoilmatlefthand_time, gun.recoilmatlefthand_xpoint, gun.recoilmatlefthand_ypoint,
							gun.recoilmatlefthand_zpoint, gun.recoilmatlefthand_xrote, gun.recoilmatlefthand_yrote, gun.recoilmatlefthand_zrote,
							gun.recoilmatlefthand_xmove, gun.recoilmatlefthand_ymove, gun.recoilmatlefthand_zmove);
				}else {
					GL11.glTranslatef(gun.arm_l_posx,gun.arm_l_posy, gun.arm_l_posz);
				}
			}
			if(gun.cock_left && !gun.recoilmotion){
	        	if(cockingtime <= 0){
				}else{
					if(cockingtime > 0 && cockingtime < (gun.cocktime/2)){
						GL11.glTranslatef(0F, 0F, -cockingtime*0.1F);
					}else{
						GL11.glTranslatef(0F, 0F, (cockingtime-gun.cocktime)*0.1F);
					}
				}
	        }
			
			if(gun.knife && entityplayer.swingProgress > 0.0F)
			{
				float f1 = entityplayer.swingProgress;
				GL11.glTranslatef(f1*2,0, f1*3);
				GL11.glRotatef(60, 0.0F, 1.0F, 0.0F);
			}
			if(leftitem.isEmpty()){
				GL11.glScalef(gun.arm_scale_l, gun.arm_scale_l, gun.arm_scale_l);
				if(mod_GVCLib.arm_lmm  && gun.arm_model_lmm != null) {
					gun.arm_model_lmm.renderPart("leftarm");
				}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
					gun.arm_model_alex.renderPart("leftarm");
				}else {
					gun.arm_model.renderPart("leftarm");
				}
			}
			
		}else{
			if(gun.newreload){
				if (gun.reloadmotion == 0) {
					for (int kais = 0; kais <= gun.mat_rk_leftarm; ++kais) {
						renderreload(gun, kais, gun.mat_r_leftarm);
					}
				} else {
					newrenderreload(gun, gun.rematlefthand, gun.rematlefthand_time, gun.rematlefthand_xpoint, gun.rematlefthand_ypoint,
							gun.rematlefthand_zpoint, gun.rematlefthand_xrote, gun.rematlefthand_yrote, gun.rematlefthand_zrote,
							gun.rematlefthand_xmove, gun.rematlefthand_ymove, gun.rematlefthand_zmove);
				}
				GL11.glScalef(gun.arm_scale_l, gun.arm_scale_l, gun.arm_scale_l);
				if(mod_GVCLib.arm_lmm  && gun.arm_model_lmm != null) {
					gun.arm_model_lmm.renderPart("leftarm");
				}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
					gun.arm_model_alex.renderPart("leftarm");
				}else {
					gun.arm_model.renderPart("leftarm");
				}
			}else{
				GL11.glTranslatef(gun.arm_mag_l_posx,gun.arm_mag_l_posy, gun.arm_mag_l_posz);
				if(gun.retime < gun.reloadtime / 4){
					GL11.glTranslatef(0.0F, -gun.retime * 0.5F, 0F);
				}else if(gun.retime < gun.reloadtime / 2){
					GL11.glTranslatef(0.0F, gun.retime * 0.5F - (gun.reloadtime/2) * 0.5F, 0F);
				}else if(gun.retime < (gun.reloadtime * 3) / 4){
					GL11.glTranslatef(0.0F,0F, -gun.retime * 0.05F);
				}
				if(leftitem.isEmpty()){
					GL11.glScalef(gun.arm_scale_l, gun.arm_scale_l, gun.arm_scale_l);
					if(mod_GVCLib.arm_lmm && gun.arm_model_lmm != null) {
						gun.arm_model_lmm.renderPart("leftarm");
					}else if(DefaultPlayerSkin.getSkinType(((AbstractClientPlayer)entityplayer).getUniqueID()).equals("slim") && gun.arm_model_alex != null) {
						gun.arm_model_alex.renderPart("leftarm");
					}else {
						gun.arm_model.renderPart("leftarm");
					}
				}
			}
			
		}
		}
	}
	private static void renderreload(ItemGunBase gun, int kaisu, String[] mat){
		if(kaisu != 0){
			kaisu = kaisu - 1;
		}
		int ka = 20 * kaisu; 
		if(mat != null){
			float bai = Float.parseFloat(mat[18 + ka]);
			float bairote = Float.parseFloat(mat[19 + ka]);
			if(gun.retime >= Integer.parseInt(mat[1 + ka]) && gun.retime <= Integer.parseInt(mat[2 + ka])){
				int time = gun.retime - Integer.parseInt(mat[1 + ka]);
				GL11.glTranslatef(Float.parseFloat(mat[3 + ka]),Float.parseFloat(mat[4 + ka]),Float.parseFloat(mat[5 + ka]));
				GL11.glTranslatef(Float.parseFloat(mat[6 + ka]),Float.parseFloat(mat[7 + ka]),Float.parseFloat(mat[8 + ka]));
				GL11.glRotatef(Float.parseFloat(mat[9 + ka]) + (time * bairote * Float.parseFloat(mat[15 + ka])), 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(Float.parseFloat(mat[10 + ka]) + (time * bairote * Float.parseFloat(mat[16 + ka])), 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(Float.parseFloat(mat[11 + ka]) + (time * bairote * Float.parseFloat(mat[17 + ka])), 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(
						time * Float.parseFloat(mat[12 + ka]) * bai,
						time * Float.parseFloat(mat[13 + ka]) * bai,
						time * Float.parseFloat(mat[14 + ka]) * bai
						);
				GL11.glTranslatef(-Float.parseFloat(mat[3 + ka]),-Float.parseFloat(mat[4 + ka]),-Float.parseFloat(mat[5 + ka]));
			}
		}
	}
	private static void newrenderreload(ItemGunBase gun, int remat, int[] remattime
			, float[] remat_xpoint, float[] remat_ypoint, float[] remat_zpoint
			, float[] remat_xrote, float[] remat_yrote, float[] remat_zrote
			, float[] remat_xmove, float[] remat_ymove, float[] remat_zmove){
		//if(mat != null)
		{
			for(int i = 1; i < 200; ++i) {
				if(gun.retime >= remattime[i - 1] && gun.retime < remattime[i]){
					int time = remattime[i] - remattime[i - 1];
					int nowtime = gun.retime - remattime[i - 1];
					
					float xpoint = remat_xpoint[i] - remat_xpoint[i - 1];
					xpoint = xpoint / time;
					float ypoint = remat_ypoint[i] - remat_ypoint[i - 1];
					ypoint = ypoint / time;
					float zpoint = remat_zpoint[i] - remat_zpoint[i - 1];
					zpoint = zpoint / time;
					
					float xrote = remat_xrote[i] - remat_xrote[i - 1];
					xrote = xrote / time;
					float yrote = remat_yrote[i] - remat_yrote[i - 1];
					yrote = yrote / time;
					float zrote = remat_zrote[i] - remat_zrote[i - 1];
					zrote = zrote / time;
					
					float xmove = remat_xmove[i] - remat_xmove[i - 1];
					xmove = xmove / time;
					float ymove = remat_ymove[i] - remat_ymove[i - 1];
					ymove = ymove / time;
					float zmove = remat_zmove[i] - remat_zmove[i - 1];
					zmove = zmove / time;
					
					GL11.glTranslatef((remat_xpoint[i - 1] + xpoint * nowtime)
							,(remat_ypoint[i - 1] + ypoint * nowtime)
							,(remat_zpoint[i - 1] + zpoint * nowtime));
					GL11.glRotatef(remat_xrote[i - 1] + xrote * nowtime, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(remat_yrote[i - 1] + yrote * nowtime, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(remat_zrote[i - 1] + zrote * nowtime, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef((remat_xmove[i - 1] + xmove * nowtime)
							,(remat_ymove[i - 1] + ymove * nowtime)
							,(remat_zmove[i - 1] + zmove * nowtime));
					GL11.glTranslatef(-(remat_xpoint[i - 1] + xpoint * nowtime)
							,-(remat_ypoint[i - 1] + ypoint * nowtime)
							,-(remat_zpoint[i - 1] + zpoint * nowtime));
				}
			}
			
		}
	}
	
	private static void newrenderrecoil(ItemGunBase gun, ItemStack itemstack, int remat, int[] remattime
			, float[] remat_xpoint, float[] remat_ypoint, float[] remat_zpoint
			, float[] remat_xrote, float[] remat_yrote, float[] remat_zrote
			, float[] remat_xmove, float[] remat_ymove, float[] remat_zmove){
		//if(mat != null)
		{
			NBTTagCompound nbt = itemstack.getTagCompound();
			int cockingtime = nbt.getInteger("CockingTime");
			for(int i = 1; i < 200; ++i) {
				if(cockingtime >= remattime[i - 1] && cockingtime < remattime[i]){
					int time = remattime[i] - remattime[i - 1];
					int nowtime = cockingtime - remattime[i - 1];
					
					float xpoint = remat_xpoint[i] - remat_xpoint[i - 1];
					xpoint = xpoint / time;
					float ypoint = remat_ypoint[i] - remat_ypoint[i - 1];
					ypoint = ypoint / time;
					float zpoint = remat_zpoint[i] - remat_zpoint[i - 1];
					zpoint = zpoint / time;
					
					float xrote = remat_xrote[i] - remat_xrote[i - 1];
					xrote = xrote / time;
					float yrote = remat_yrote[i] - remat_yrote[i - 1];
					yrote = yrote / time;
					float zrote = remat_zrote[i] - remat_zrote[i - 1];
					zrote = zrote / time;
					
					float xmove = remat_xmove[i] - remat_xmove[i - 1];
					xmove = xmove / time;
					float ymove = remat_ymove[i] - remat_ymove[i - 1];
					ymove = ymove / time;
					float zmove = remat_zmove[i] - remat_zmove[i - 1];
					zmove = zmove / time;
					
					GL11.glTranslatef((remat_xpoint[i - 1] + xpoint * nowtime)
							,(remat_ypoint[i - 1] + ypoint * nowtime)
							,(remat_zpoint[i - 1] + zpoint * nowtime));
					GL11.glRotatef(remat_xrote[i - 1] + xrote * nowtime, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(remat_yrote[i - 1] + yrote * nowtime, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(remat_zrote[i - 1] + zrote * nowtime, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef((remat_xmove[i - 1] + xmove * nowtime)
							,(remat_ymove[i - 1] + ymove * nowtime)
							,(remat_zmove[i - 1] + zmove * nowtime));
					GL11.glTranslatef(-(remat_xpoint[i - 1] + xpoint * nowtime)
							,-(remat_ypoint[i - 1] + ypoint * nowtime)
							,-(remat_zpoint[i - 1] + zpoint * nowtime));
				}
			}
			
		}
	}
	
	
	private static void swingmotion(ItemGunBase gun, float swing, int remat, int[] remattime
			, float[] remat_xpoint, float[] remat_ypoint, float[] remat_zpoint
			, float[] remat_xrote, float[] remat_yrote, float[] remat_zrote
			, float[] remat_xmove, float[] remat_ymove, float[] remat_zmove){
		//if(mat != null)
		{
			for(int i = 1; i < 200; ++i) {
				if(swing >= remattime[i - 1] && swing < remattime[i]){
					int time = remattime[i] - remattime[i - 1];
					int nowtime = (int)swing - remattime[i - 1];
					
					float xpoint = remat_xpoint[i] - remat_xpoint[i - 1];
					xpoint = xpoint / time;
					float ypoint = remat_ypoint[i] - remat_ypoint[i - 1];
					ypoint = ypoint / time;
					float zpoint = remat_zpoint[i] - remat_zpoint[i - 1];
					zpoint = zpoint / time;
					
					float xrote = remat_xrote[i] - remat_xrote[i - 1];
					xrote = xrote / time;
					float yrote = remat_yrote[i] - remat_yrote[i - 1];
					yrote = yrote / time;
					float zrote = remat_zrote[i] - remat_zrote[i - 1];
					zrote = zrote / time;
					
					float xmove = remat_xmove[i] - remat_xmove[i - 1];
					xmove = xmove / time;
					float ymove = remat_ymove[i] - remat_ymove[i - 1];
					ymove = ymove / time;
					float zmove = remat_zmove[i] - remat_zmove[i - 1];
					zmove = zmove / time;
					
					GL11.glTranslatef((remat_xpoint[i - 1] + xpoint * nowtime)
							,(remat_ypoint[i - 1] + ypoint * nowtime)
							,(remat_zpoint[i - 1] + zpoint * nowtime));
					GL11.glRotatef(remat_xrote[i - 1] + xrote * nowtime, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(remat_yrote[i - 1] + yrote * nowtime, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(remat_zrote[i - 1] + zrote * nowtime, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef((remat_xmove[i - 1] + xmove * nowtime)
							,(remat_ymove[i - 1] + ymove * nowtime)
							,(remat_zmove[i - 1] + zmove * nowtime));
					GL11.glTranslatef(-(remat_xpoint[i - 1] + xpoint * nowtime)
							,-(remat_ypoint[i - 1] + ypoint * nowtime)
							,-(remat_zpoint[i - 1] + zpoint * nowtime));
				}
			}
			
		}
	}
}
