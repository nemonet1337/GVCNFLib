package gvclib.event.gun;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import gvclib.mod_GVCLib;
import gvclib.item.ItemGunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GVCEventsGunRender_Third {

	
	/*@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void test(ColorHandlerEvent.Item event)
	  {
		
	  }*/
	public boolean on = true;
	public boolean onlmm = true;
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthird(RenderLivingEvent.Pre event)
	  {
		/*EntityLivingBase entity = (EntityLivingBase) event.getEntity();
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ItemStack itemstack = entity.getHeldItemMainhand();
		ItemStack itemstackl = entity.getHeldItemOffhand();
		
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
	//		this.rendergunthirdMob2(entityplayer, itemstack, true, entity, event.getX(), event.getY(), event.getZ(), EnumHandSide.RIGHT);
	//		event.getRenderer().addLayer(new LayerGunBase(event.getRenderer()));
		}//item
		if (itemstackl != null && itemstackl.getItem() instanceof ItemGunBase) {//item
	//		this.rendergunthirdMob2(entityplayer, itemstackl, false, entity, event.getX(), event.getY(), event.getZ(), EnumHandSide.LEFT);
	//		event.getRenderer().addLayer(new LayerGunBase(event.getRenderer()));
		}//item
	//	event.getRenderer().addLayer(new LayerGunBase(event.getRenderer()));
		*/
		
		//20/01/18試作
		Entity entity = event.getEntity();
		if(entity != null) {
			String name = entity.getName();
			Render render = event.getRenderer();
			boolean flag = false;
			if ((render instanceof RenderLivingBase))
	        {
				RenderLivingBase renderbase = (RenderLivingBase) render;
				if ((renderbase.getMainModel() instanceof ModelBiped)) {
					for(int ii = 0; ii < 512; ++ii) {
			    		if(mod_GVCLib.gunlayer_mob_name[ii] != null && mod_GVCLib.gunlayer_mob_name[ii].equals(name)) {
			    			flag = true;
			    			break;
			    		}
			    	}
					if(!flag) {
							renderbase.addLayer(new LayerGunBase(renderbase));
							++mod_GVCLib.gunlayer_mob;
							mod_GVCLib.gunlayer_mob_name[mod_GVCLib.gunlayer_mob] = name;
							System.out.println(String.format("road"));
					}
				}
				if(entity instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase)entity;
					ItemStack itemstack = ((EntityLivingBase) (living)).getHeldItemMainhand();
					ItemStack itemstackl = ((EntityLivingBase) (living)).getHeldItemOffhand();
					if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
						ItemGunBase gun = (ItemGunBase) itemstack.getItem();
						gun.ModelLoad();
					}
					if (!itemstackl.isEmpty()  && itemstackl.getItem() instanceof ItemGunBase) {//item
						ItemGunBase gun = (ItemGunBase) itemstackl.getItem();
						gun.ModelLoad();
					}
				}
				
				
			}
		}
		
		
		
		//試験的にRender_S_Bipedで対応させる方式に変更
		//他モブに対応できないか要検証
		/*
		if(on) {
		for (Render render : Minecraft.getMinecraft().getRenderManager().entityRenderMap.values()) {
	          if ((render instanceof RenderLivingBase))
	          {
	            RenderLivingBase renderbase = (RenderLivingBase)render;
	            if ((renderbase.getMainModel() instanceof ModelBiped))
	            {
	//            	renderbase.addLayer(new LayerGunBase(renderbase));
	//            	System.out.println(String.format("road"));
	              on = false;
	            }
	          }
	     }
		}
		*/
		
		
		
		/*if(onlmm) {
			for (Render render : Minecraft.getMinecraft().getRenderManager().entityRenderMap.values()) {
		          if ((render instanceof RenderLivingBase))
		          {
		            RenderLivingBase renderbase = (RenderLivingBase)render;
		            if(entity instanceof EntityLittleMaid && (renderbase.getMainModel() instanceof ModelBaseSolo)) {
		            	{
							renderbase.addLayer(new LayerGunBaseLMM(renderbase));
							onlmm = false;
						}
		  	        }
		          }
		     }
			}
		*/
		/*RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		Render render = rendermanager.getEntityRenderObject(entity);
		{
  	      if (render instanceof RenderLivingBase && entity != entityplayer && entity != null)
  	      {
  	        RenderLivingBase renderbase = (RenderLivingBase)render;
  	      NBTTagCompound nbtgun = entity.getEntityData();
			boolean on = nbtgun.getBoolean("gunlayer");
  	        if ((renderbase.getMainModel() instanceof ModelBiped)) 
  	        {
  	        //	if(renderbase.l)
				if(!on){
			//		renderbase.addLayer(new LayerGunBase(renderbase));
					nbtgun.setBoolean("gunlayer", true);
				}
  	        }
  	        if(entity instanceof EntityLittleMaid) {
  	        	if(!on){
					//renderbase.addLayer(new LayerGunBaseLMM(renderbase));
					nbtgun.setBoolean("gunlayer", true);
				}
  	        }
  	      }
		}*/
  	}
	
	 public void rendergunthirdMob2(EntityPlayer entityplayer, ItemStack itemstack, boolean side, EntityLivingBase entity, double x, double y, double z, EnumHandSide handSide){
			/*int xxx = 1;
			if(!side){
				xxx = -1;
			}
			if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				GL11.glPushMatrix();
				GL11.glTranslatef((float)x,(float)y,(float)z);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				
				if(entity != entityplayer && entity.getHealth() > 0.0F){
					GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(180-entity.rotationYawHead, 0.0F, 1.0F, 0.0F);
		        	GL11.glTranslatef(-0.36F * xxx,1.40F,0F);
		        	GL11.glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		        	GL11.glTranslatef(0,0F,0.50F);
		        	GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.75F, 0.75F, 0.75F);
				gun.obj_model.renderPart("mat1");
				gun.obj_model.renderPart("mat2");
				gun.obj_model.renderPart("mat3");
				gun.obj_model.renderPart("mat20");
				gun.obj_model.renderPart("mat21");
				gun.obj_model.renderPart("mat31");
				gun.obj_model.renderPart("mat32");
				
				GL11.glPushMatrix();//flashs
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.flash_tex));
				GL11.glTranslatef(gun.flash_posx,gun.flash_posy, gun.flash_posz);//0,1,6
				if ((gun.flash))
				{
					{
		//				gun.flash_model.renderPart("flash");
					}
				}
				GL11.glPopMatrix();//flashe
				}
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
			}//item*/
		 if (itemstack != null && entity != entityplayer)
	        {
			 RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			 Render render = rendermanager.getEntityRenderObject(entity);
			 RenderLivingBase renderbase = (RenderLivingBase)render;
	            GlStateManager.pushMatrix();
	            GL11.glTranslatef((float)x,(float)y,(float)z);
	            if (entity.isSneaking())
	            {
	                GlStateManager.translate(0.0F, 0.2F, 0.0F);
	            }
	            // Forge: moved this call down, fixes incorrect offset while sneaking.
	            ((ModelBiped)renderbase.getMainModel()).postRenderArm(0.0625F, handSide);
	            //GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
	            //GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
	            GL11.glScalef(0.1875F, 0.1875F, 0.1875F);
	            boolean flag = handSide == EnumHandSide.LEFT;
	            GlStateManager.translate((float)((flag ? -1 : 1) / 16.0F) * -5.33F, 0.125F * -5.33F, -0.625F * -5.33F);
	            //GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
	            //Minecraft.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
	            if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
					ItemGunBase gun = (ItemGunBase) itemstack.getItem();
					Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				gun.obj_model.renderPart("mat1");
				gun.obj_model.renderPart("mat2");
				gun.obj_model.renderPart("mat3");
				gun.obj_model.renderPart("mat20");
				gun.obj_model.renderPart("mat21");
				gun.obj_model.renderPart("mat31");
				gun.obj_model.renderPart("mat32");
	            }
	            GlStateManager.popMatrix();
	        }
	 }
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthirdtest(RenderPlayerEvent.Post event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				RenderPlayer renderplayer = event.getRenderer();
				//renderplayer.getMainModel().bipedRightArm
				//renderplayer.getMainModel().bipedRightArm.rotateAngleY = -0.1F + renderplayer.getMainModel().bipedHead.rotateAngleY;
				//renderplayer.getMainModel().bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + renderplayer.getMainModel().bipedHead.rotateAngleX;
				//renderplayer.getMainModel().bipedRightArm.rotateAngleY = -0.1F + entityplayer.rotationPitch;
				//renderplayer.getMainModel().bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + entityplayer.rotationYawHead;
			//	ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
			//	modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
			//	renderplayer.getMainModel().rightArmPose = modelbiped$armpose;
		}
	  }
	
	/*@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthirdtest(RenderPlayerEvent.Pre event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.thePlayer;
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				GL11.glPushMatrix();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				{
					EnumHandSide handSide = EnumHandSide.RIGHT;
					RenderLivingBase livingEntityRenderer = event.getRenderer();
					if (entityplayer.isSneaking())
		            {
		                GlStateManager.translate(0.0F, 0.2F, 0.0F);
		            }
		            // Forge: moved this call down, fixes incorrect offset while sneaking.
		            ((ModelBiped)livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, handSide);
		            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
		            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		            boolean flag = handSide == EnumHandSide.LEFT;
		            GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
		           // Minecraft.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
		            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
		            gun.obj_model.renderPart("mat1");
		        	gun.obj_model.renderPart("mat2");
		        	gun.obj_model.renderPart("mat3");
		        	gun.obj_model.renderPart("mat20");
		        	gun.obj_model.renderPart("mat21");
				}
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
		}
	  }*/
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthirdmain(RenderPlayerEvent.Pre event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.player;
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		ItemStack itemstackl = ((EntityPlayer) (entityplayer)).getHeldItemOffhand();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase && itemstack.hasTagCompound()) {//item
	//		this.rendergunthird2(entityplayer, itemstack, true, event.getPartialRenderTick());
		}//item
		if (itemstackl != null && itemstackl.getItem() instanceof ItemGunBase && itemstackl.hasTagCompound()) {//item
	//		this.rendergunthird2(entityplayer, itemstackl, false, event.getPartialRenderTick());
		}//item
	//	event.getRenderer().addLayer(new LayerGunBase(event.getRenderer()));
	  }
	
	
	  public void rendergunthird2(EntityPlayer entityplayer, ItemStack itemstack, boolean side, float tick){
			int xxx = 1;
			if(!side){
				xxx = -1;
			}
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				GL11.glPushMatrix();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				{
					float f5 = 0.0F;
		            float f6 = 0.0F;
		            f5 = this.F5(entityplayer, tick);
		            f6 = this.F6(entityplayer, tick);
					
					boolean flag = entityplayer instanceof EntityLivingBase && ((EntityLivingBase)entityplayer).getTicksElytraFlying() > 4;
					float f = 1.0F;
			        if (flag)
			        {
			            f = (float)(entityplayer.motionX * entityplayer.motionX + entityplayer.motionY * entityplayer.motionY + entityplayer.motionZ * entityplayer.motionZ);
			            f = f / 0.2F;
			            f = f * f * f;
			        }
			        if (f < 1.0F)
			        {
			            f = 1.0F;
			        }
			        float x = MathHelper.cos(f6 * 0.6662F + (float)Math.PI) * 2.0F * f5 * 0.5F / f;
			        if(entityplayer.isHandActive()){
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(180-entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
						if(entityplayer.isSneaking()){
							GL11.glTranslatef(-0.36F * xxx,1.2F,0F);
						}else{
			        	GL11.glTranslatef(-0.36F * xxx,1.45F,0F);
						}
			        	GL11.glRotatef(entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
			        	GL11.glTranslatef(0,0F,0.30F);
			        }else{
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(180-entityplayer.renderYawOffset, 0.0F, 1.0F, 0.0F);
						if(entityplayer.isSneaking()){
							GL11.glTranslatef(-0.36F * xxx,1.2F,0.0F);
						}else{
			        	GL11.glTranslatef(-0.36F * xxx,1.375F,0.0F);
						}
	                    {
	                        GlStateManager.rotate(x * (180F / (float)Math.PI) * xxx, 1.0F, 0.0F, 0.0F);
	                        GL11.glTranslatef(0,-0.6F,0.2F);
	                    }
						{
						GL11.glRotatef(gun.modek_third_offset_rote_x, 1.0F, 0.0F, 0.0F);//GL11.glRotatef(70F, 1.0F, 0.0F, 0.0F);
						}
			        }
			        GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.75F, 0.75F, 0.75F);
			        
			        
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				gun.obj_model.renderPart("mat1");
				{
					GL11.glPushMatrix();//flashs
					NBTTagCompound nbt = itemstack.getTagCompound();
					boolean cocking = nbt.getBoolean("Cocking");
					int cockingtime = nbt.getInteger("CockingTime");
					boolean recoiled = nbt.getBoolean("Recoiled");
					int recoiledtime = nbt.getInteger("RecoiledTime");
					if (!recoiled)
					{
						this.mat31mat32(gun, true);
						GL11.glTranslatef(0.0F, 0.0F, gun.model_cock_z);//0, 0, -0.4
						gun.obj_model.renderPart("mat2");
						GL11.glTranslatef(0.0F, 0.0F, -gun.model_cock_z);
						this.mat25(gun, false, cockingtime);
						this.rendersight(entityplayer, itemstack, gun);
					}else{
						this.mat31mat32(gun, true);
						gun.obj_model.renderPart("mat2");
						this.mat25(gun, false, cockingtime);
						this.rendersight(entityplayer, itemstack, gun);
					}
					GL11.glPopMatrix();//flashe
				}
				gun.obj_model.renderPart("mat3");
				gun.obj_model.renderPart("mat20");
				gun.obj_model.renderPart("mat21");
				GL11.glPushMatrix();//flashs
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.flash_tex));
				GL11.glTranslatef(gun.flash_posx,gun.flash_posy, gun.flash_posz);//0,1,6
				//if ((!gun.recoilreBolts(itemstack) || !gun.cycleBolt(itemstack)))
				if ((!gun.recoilreBolts(itemstack)))
				{
					if(entityplayer.isSneaking()){
						if(!gun.model_zoomrender){
	//						gun.flash_model.renderPart("flash");
						}
					}else{
	//					gun.flash_model.renderPart("flash");
					}
				}
				GL11.glPopMatrix();//flashe
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
		}//item
	  }
	
	public float F6(EntityLivingBase entity, float partialTicks){
 		float f6 = 0;
 		if (!entity.isRiding())
        {
            f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
            if (entity.isChild())
            {
                f6 *= 3.0F;
            }
        }
 		return f6;
 	}
 	public float F5(EntityLivingBase entity, float partialTicks){
 		float f5 = 0;
 		if (!entity.isRiding())
        {
            f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
            if (f5 > 1.0F)
            {
                f5 = 1.0F;
            }
        }
 		return f5;
 	}
	
	/*@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthird(RenderPlayerEvent.Pre event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.thePlayer;
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				GL11.glPushMatrix();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				{
					float f5 = 0.0F;
		            float f6 = 0.0F;

		            /*if (!entityplayer.isRiding())
		            {
		                f5 = entityplayer.prevLimbSwingAmount + (entityplayer.limbSwingAmount - entityplayer.prevLimbSwingAmount) * event.getPartialRenderTick();
		                f6 = entityplayer.limbSwing - entityplayer.limbSwingAmount * (1.0F - event.getPartialRenderTick());

		                if (entityplayer.isChild())
		                {
		                    f6 *= 3.0F;
		                }

		                if (f5 > 1.0F)
		                {
		                    f5 = 1.0F;
		                }
		            }*
		            f5 = this.F5(entityplayer, event.getPartialRenderTick());
		            f6 = this.F6(entityplayer, event.getPartialRenderTick());
					
					boolean flag = entityplayer instanceof EntityLivingBase && ((EntityLivingBase)entityplayer).getTicksElytraFlying() > 4;
					float f = 1.0F;
			        if (flag)
			        {
			            f = (float)(entityplayer.motionX * entityplayer.motionX + entityplayer.motionY * entityplayer.motionY + entityplayer.motionZ * entityplayer.motionZ);
			            f = f / 0.2F;
			            f = f * f * f;
			        }
			        if (f < 1.0F)
			        {
			            f = 1.0F;
			        }
			        float x = MathHelper.cos(f6 * 0.6662F + (float)Math.PI) * 2.0F * f5 * 0.5F / f;
			        if(entityplayer.isHandActive()){
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(180-entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
						if(entityplayer.isSneaking()){
							GL11.glTranslatef(-0.36F,1.2F,0F);
						}else{
			        	GL11.glTranslatef(-0.36F,1.45F,0F);
						}
			        	GL11.glRotatef(entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
			        	GL11.glTranslatef(0,0F,0.30F);
			        }else{
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(180-entityplayer.renderYawOffset, 0.0F, 1.0F, 0.0F);
						if(entityplayer.isSneaking()){
							GL11.glTranslatef(-0.36F,1.2F,0.0F);
						}else{
			        	GL11.glTranslatef(-0.36F,1.375F,0.0F);
						}
			        	//GL11.glRotatef(entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
						//if (x != 0.0F)
	                    {
	                    	//GL11.glTranslatef(0,-0.6F,0.0F);
	                    	//GL11.glTranslatef(0F,0F,-0.1F);
	                        GlStateManager.rotate(x * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
	                        //GL11.glTranslatef(0F,0F,0.1F);
	                        GL11.glTranslatef(0,-0.6F,0.2F);
	                    }
						/*if(entityplayer.isSprinting()){
							GL11.glRotatef(70F, 0.0F, 1.0F, 0.0F);
						}else *
						{
						GL11.glRotatef(70F, 1.0F, 0.0F, 0.0F);
						}
			        }
			        GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glScalef(0.75F, 0.75F, 0.75F);
			        
			        
				}
				/*float partialTicks = minecraft.getRenderPartialTicks();
				GL11.glRotatef((180.0F - entityplayer.renderYawOffset), 0.0F, 1.0F, 0.0F);
				float limbSwing = this.F6(entityplayer, partialTicks);
				float limbSwingAmount = this.F5(entityplayer, partialTicks);
				float Ax = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
				GL11.glTranslatef(0.35F, 1.375F, 0.0F);
				GL11.glRotatef(Ax * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(-0.35F, -1.375F, 0.0F);
				
				
				{
					EnumHandSide handSide = EnumHandSide.RIGHT;
					RenderLivingBase livingEntityRenderer = event.getRenderer();
					((ModelBiped)livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, handSide);
				}
				if(entityplayer.isSneaking()){
					GL11.glTranslatef(-0.36F,1.2F,0F);
				}else{
	        	GL11.glTranslatef(-0.36F,1.45F,0F);
				}
				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.75F, 0.75F, 0.75F);*
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				gun.obj_model.renderPart("mat1");
				{
					GL11.glPushMatrix();//flashs
					NBTTagCompound nbt = itemstack.getTagCompound();
					boolean cocking = nbt.getBoolean("Cocking");
					int cockingtime = nbt.getInteger("CockingTime");
					boolean recoiled = nbt.getBoolean("Recoiled");
					int recoiledtime = nbt.getInteger("RecoiledTime");
					if (!recoiled)
					{
						this.mat31mat32(gun, true);
						GL11.glTranslatef(0.0F, 0.0F, gun.model_cock_z);//0, 0, -0.4
						gun.obj_model.renderPart("mat2");
						GL11.glTranslatef(0.0F, 0.0F, -gun.model_cock_z);
						this.mat25(gun, false, cockingtime);
						this.rendersight(entityplayer, itemstack, gun);
					}else{
						this.mat31mat32(gun, true);
						gun.obj_model.renderPart("mat2");
						this.mat25(gun, false, cockingtime);
						this.rendersight(entityplayer, itemstack, gun);
					}
					GL11.glPopMatrix();//flashe
				}
				gun.obj_model.renderPart("mat3");
				gun.obj_model.renderPart("mat20");
				gun.obj_model.renderPart("mat21");
				GL11.glPushMatrix();//flashs
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.flash_tex));
				GL11.glTranslatef(gun.flash_posx,gun.flash_posy, gun.flash_posz);//0,1,6
				//if ((!gun.recoilreBolts(itemstack) || !gun.cycleBolt(itemstack)))
				if ((!gun.recoilreBolts(itemstack)))
				{
					if(entityplayer.isSneaking()){
						if(!gun.model_zoomrender){
	//						gun.flash_model.renderPart("flash");
						}
					}else{
	//					gun.flash_model.renderPart("flash");
					}
				}
				GL11.glPopMatrix();//flashe
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
		}//item
	  }*/
	
	
	
	
	
	/*@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendergunthird(RenderPlayerEvent.Pre event)
	  {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer entityplayer = minecraft.thePlayer;
		boolean jump = mod_GVCR.proxy.jumped();
		boolean left = mod_GVCR.proxy.leftclick();
		boolean right = mod_GVCR.proxy.rightclick();
		ItemStack itemstack = ((EntityPlayer) (entityplayer)).getHeldItemMainhand();
		if (itemstack != null && itemstack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				GL11.glPushMatrix();
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				{
					float ix2 = 0;
					float iz2 = 0;
					float f12 = entityplayer.prevRotationYaw * (2 * (float) Math.PI / 360);
					ix2 -= (float) (MathHelper.sin(f12+1.3F) * 1.5);
					iz2 += (float) (MathHelper.cos(f12+1.3F) * 1.5);
					GL11.glTranslatef(ix2,3F,iz2);
					GL11.glRotatef(180F - entityplayer.prevRotationYaw, 0.0F, 1.0F, 0.0F);
					
				
				{
					float f5 = 0.0F;
		            float f6 = 0.0F;

		            if (!entityplayer.isRiding())
		            {
		                f5 = entityplayer.prevLimbSwingAmount + (entityplayer.limbSwingAmount - entityplayer.prevLimbSwingAmount) * event.getPartialRenderTick();
		                f6 = entityplayer.limbSwing - entityplayer.limbSwingAmount * (1.0F - event.getPartialRenderTick());

		                if (entityplayer.isChild())
		                {
		                    f6 *= 3.0F;
		                }

		                if (f5 > 1.0F)
		                {
		                    f5 = 1.0F;
		                }
		            }
					
					boolean flag = entityplayer instanceof EntityLivingBase && ((EntityLivingBase)entityplayer).getTicksElytraFlying() > 4;
					float f = 1.0F;
			        if (flag)
			        {
			            f = (float)(entityplayer.motionX * entityplayer.motionX + entityplayer.motionY * entityplayer.motionY + entityplayer.motionZ * entityplayer.motionZ);
			            f = f / 0.2F;
			            f = f * f * f;
			        }
			        if (f < 1.0F)
			        {
			            f = 1.0F;
			        }
			        float x = MathHelper.cos(f6 * 0.6662F + (float)Math.PI) * 2.0F * f5 * 0.5F / f;
			        if(entityplayer.isHandActive()){
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			        	GL11.glTranslatef(0,1.5F,2.5F);
			        	GL11.glTranslatef(0,0F,-2.5F);
			        	GL11.glRotatef(entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
			        	GL11.glTranslatef(0,0F,2.5F);
			        }else{
			        	GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(00F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(70F, 1.0F, 0.0F, 0.0F);
						if (x != 0.0F)
	                    {
				        	
				        	GL11.glTranslatef(0,0F,-3);
	                        GlStateManager.rotate(x * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
	                        GL11.glTranslatef(0,0F,3);
	                    }
			        }
			        
			        
			        
				}
				
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				gun.obj_model.renderPart("mat1");
				gun.obj_model.renderPart("mat2");
				gun.obj_model.renderPart("mat3");
				gun.obj_model.renderPart("mat20");
				gun.obj_model.renderPart("mat21");
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
		}//item
	  }*/
 	private void mat31mat32(ItemGunBase gun, boolean recoil){
		if(gun.rote < 360F || gun.rotex < 360F || gun.rotey < 360F)
		{
			GL11.glPushMatrix();//glstart1
		GL11.glTranslatef(gun.mat31posx, gun.mat31posy, gun.mat31posz);//0,0.7,0
		GL11.glRotatef(gun.rotey, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(gun.rotex, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(gun.rote, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-gun.mat31posx, -gun.mat31posy, -gun.mat31posz);
		{
			gun.obj_model.renderPart("mat31");
		}
		GL11.glRotatef(-gun.rotey, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-gun.rotex, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-gun.rote, 0.0F, 1.0F, 0.0F);
		if(recoil){
			gun.rote = gun.rote + gun.mat31rotez;//60F
			gun.rotex = gun.rotex + gun.mat31rotex;
			gun.rotey = gun.rotey + gun.mat31rotey;
		}
		GL11.glPopMatrix();//glend1
		GL11.glPushMatrix();//glstrt2
		GL11.glTranslatef(gun.mat32posx, gun.mat32posy, gun.mat32posz);//0,0.5,0
		GL11.glRotatef(gun.mat32rotey, 0.0F, 1.0F, 0.0F);//90
		GL11.glRotatef(gun.mat32rotez, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(gun.mat32rotex, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(-gun.mat32posx, -gun.mat32posy, -gun.mat32posz);
		gun.obj_model.renderPart("mat32");
		GL11.glPopMatrix();//glend2
		}else{
			gun.rote = 0;
			gun.rotey = 0;
			gun.rotex = 0;
		}
	}
 	private void rendersight(EntityPlayer entityplayer, ItemStack itemstack, ItemGunBase gun){
		if(gun.true_mat4){
			gun.obj_model.renderPart("mat4");
		}else if(gun.true_mat5){
			gun.obj_model.renderPart("mat5");
		}else{
			gun.obj_model.renderPart("mat20");
		}
		gun.obj_model.renderPart("mat22");
	}
	
	private void mat25(ItemGunBase gun, boolean recoil, int cockingtime){
		if(recoil){
			GL11.glPushMatrix();//glstart11
			{
				GL11.glTranslatef(gun.mat25offsetx, gun.mat25offsety, gun.mat25offsetz);
				GL11.glRotatef(gun.mat25rotationx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(gun.mat25rotationy, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(gun.mat25rotationz, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-gun.mat25offsetx, -gun.mat25offsety, -gun.mat25offsetz);
				GL11.glTranslatef(0F, 0F, -(gun.cocktime/2)*0.1F);
				gun.obj_model.renderPart("mat25");
			}
			GL11.glPopMatrix();//glend11
		}else{

			GL11.glPushMatrix();//glstart11
			if(cockingtime <= 0){
				gun.obj_model.renderPart("mat25");
			}else{
				GL11.glTranslatef(gun.mat25offsetx, gun.mat25offsety, gun.mat25offsetz);
				GL11.glRotatef(gun.mat25rotationx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(gun.mat25rotationy, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(gun.mat25rotationz, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-gun.mat25offsetx, -gun.mat25offsety, -gun.mat25offsetz);
				if(cockingtime > 0 && cockingtime < (gun.cocktime/2)){
					GL11.glTranslatef(0F, 0F, -cockingtime*0.1F);
				}else{
					GL11.glTranslatef(0F, 0F, (cockingtime-gun.cocktime)*0.1F);
				}
				gun.obj_model.renderPart("mat25");
			}
			GL11.glPopMatrix();//glend11
		}
		
	}
}
