package gvclib.event.gun;

import gvclib.mod_GVCLib;
import gvclib.item.ItemGunBase;
import gvclib.item.ItemGun_SR;
import gvclib.item.ItemGun_Shield;
import gvclib.network.GVCLClientMessageKeyPressed;
import gvclib.network.GVCLMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GVCGunEvents {

	@SubscribeEvent
	public void LivingDigEvent(PlayerEvent.BreakSpeed event) {
		EntityLivingBase target = event.getEntityLiving();
		ItemStack itemstackl = target.getHeldItemOffhand();
		if(target instanceof EntityPlayer && target != null){
			if (target != null &&! itemstackl.isEmpty() 
					//&& itemstackl.getItem() instanceof ItemGun_Shield 
					&& itemstackl.getItem() instanceof ItemGunBase 
					&& itemstackl.getItemDamage() != itemstackl.getMaxDamage() && itemstackl.hasTagCompound()) {
				NBTTagCompound nbtgun = itemstackl.getTagCompound();
				boolean left = nbtgun.getBoolean("LeftClick");
				if(left || target.isSneaking())
				{
					event.setNewSpeed(0);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onGunMainEvent(LivingHurtEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		ItemStack itemstack = target.getHeldItemMainhand();
		if(target instanceof EntityPlayer  && target != null){
			EntityPlayer entityplayer = (EntityPlayer)target;
			World world = target.world;
			if (target != null && !itemstack.isEmpty()&& itemstack.getItem() instanceof ItemGunBase && itemstack.hasTagCompound()) {
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				NBTTagCompound nbtgun = itemstack.getTagCompound();
				
				//nbtgun.setBoolean("LeftClick", false);
			//	boolean left = nbtgun.getBoolean("LeftClick");
			//	boolean right = nbtgun.getBoolean("RightClick");
				
				/*//1/19なんか鯖でエラー起きてる
				if (mod_GVCLib.proxy.leftclick()) 
				//if(Mouse.isButtonDown(0))
				{
					nbtgun.setBoolean("LeftClick", true);
					GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(102));
				}else{
					nbtgun.setBoolean("LeftClick", false);
					GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(1021));
				}
				if (mod_GVCLib.proxy.rightclick()) {
					nbtgun.setBoolean("RightClick", true);
					GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(112));
				}else{
					nbtgun.setBoolean("RightClick", false);
					GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(1121));
				}*/
			}
		}
		
	}/**/
	
	
	//20/01/24各Gun_ARなどに移動
	/*
	@SubscribeEvent
	public void onGunOffEvent(LivingUpdateEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		ItemStack itemstack = target.getHeldItemOffhand();
		if(target instanceof EntityPlayer  && target != null){
			EntityPlayer entityplayer = (EntityPlayer)target;
			World world = target.world;
			if (target != null && !itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase && itemstack.hasTagCompound()) {
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				NBTTagCompound nbtgun = itemstack.getTagCompound();
				{
					gun.gunbase_recoil(itemstack, world, entityplayer, 0, true, 0);
					gun.gunbase_reload(itemstack, world, entityplayer, 0, true);
					{
						entityplayer.motionX = entityplayer.motionX * gun.motion;
						entityplayer.motionZ = entityplayer.motionZ * gun.motion;
					}
				}
				if(nbtgun != null  && !(itemstack.getItem() instanceof ItemGun_Shield)) {
					boolean left = nbtgun.getBoolean("LeftClick");
					boolean right = nbtgun.getBoolean("RightClick");
					if (mod_GVCLib.proxy.leftclick()) 
					//if(Mouse.isButtonDown(0))
					{
						nbtgun.setBoolean("LeftClick", true);
						GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(101));
						//System.out.println(String.format("input"));
					}
					if (mod_GVCLib.proxy.rightclick()) {
						nbtgun.setBoolean("RightClick", true);
						GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(111));
					}else{
						//nbtgun.setBoolean("RightClick", false);
						//GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(1111));
					}
					
					
					
					
					/*
					if(nbtgun != null){
						// nbtgun.setBoolean("LeftClick", false);
				//		System.out.println(String.format("offhand"));
						
						if (itemstack.getItem() instanceof ItemGun_SR) {// SR
							gun.gunbase_cocking(itemstack, world, entityplayer, 0, true);
							if (left)
							// if(entityplayer.swingProgressInt > 0)
							{
								boolean cocking = nbtgun.getBoolean("Cocking");
								{
									if (itemstack.getItemDamage() == itemstack.getMaxDamage()) {
									} else if (cocking) {
										gun.FireBullet(itemstack, world, (EntityPlayer) entityplayer);
										// recoilreBolt(par1ItemStack);
										// resetBolt(par1ItemStack);
										nbtgun.setBoolean("Cocking", false);
										nbtgun.setBoolean("Recoiled", false);
										
										nbtgun.setBoolean("LeftClick", false);
										GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(1011));
									}
								}
							}
						} // SR
						else //if (itemstack.getItem() instanceof ItemGun_AR) 
						{
							// AR
							{
								boolean cocking = nbtgun.getBoolean("Cocking");
								int cockingtime = nbtgun.getInteger("CockingTime");
								if (!cocking) {
									++cockingtime;
									nbtgun.setInteger("CockingTime", cockingtime);
									if (cockingtime >= gun.cycle) {
										nbtgun.setInteger("CockingTime", 0);
										nbtgun.setBoolean("Cocking", true);
									}
								}
							}
							//System.out.println(String.format("fire"));
							if (left)
							{
								//System.out.println(String.format("fire2"));
								boolean cocking = nbtgun.getBoolean("Cocking");
								{
									if (itemstack.getItemDamage() == itemstack.getMaxDamage()) {
									} else if (cocking) {
										gun.FireBullet(itemstack, world, (EntityPlayer) entityplayer);
										// recoilreBolt(par1ItemStack);
										// resetBolt(par1ItemStack);
										nbtgun.setBoolean("Cocking", false);
										nbtgun.setBoolean("Recoiled", false);
										
										nbtgun.setBoolean("LeftClick", false);
										GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(1011));
									}
								}
							}
						} // AR
					}
					*/
					
					
					
//				}
				
				
				
				
				
//			}
			
			
			
			
			
			
			
			
//		}
//	}
	/**/
	
}
