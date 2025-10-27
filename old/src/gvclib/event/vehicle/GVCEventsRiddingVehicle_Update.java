package gvclib.event.vehicle;

import java.util.List;

import gvclib.mod_GVCLib;
import gvclib.entity.EntityBBase;
import gvclib.entity.EntityB_Missile;
import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.entity.living.EntityVehicleBase;
import gvclib.event.GVCSoundEvent;
import gvclib.network.GVCLClientMessageKeyPressed;
import gvclib.network.GVCLMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GVCEventsRiddingVehicle_Update {
	@SubscribeEvent
	public void onRiddingMountEvent(LivingUpdateEvent event) {
		Entity target = event.getEntityLiving();
		//Entity ride = event.getEntityBeingMounted();
		if (target != null && target instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) target;
			if (player.getRidingEntity() instanceof EntityVehicleBase && player.getRidingEntity() != null) {// 1
				EntityVehicleBase vehicle = (EntityVehicleBase) player.getRidingEntity();
				if (vehicle.night_vision) 
				{
					if (vehicle.getBoosttime() == 1) 
					{
						//if (!player.isPotionActive(MobEffects.NIGHT_VISION))
							player.addPotionEffect(
									new PotionEffect(MobEffects.NIGHT_VISION, 401, 1, false, false));
					}else {
						if (player.isPotionActive(MobEffects.NIGHT_VISION) && player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() < 400) {
							player.removeActivePotionEffect(MobEffects.NIGHT_VISION);
						}
					}
				}
				
				//240224追加
				/*{
					vehicle.posX =player.posX;
					vehicle.prevPosX =player.prevPosX;
					vehicle.serverPosX =player.serverPosX;
					vehicle.lastTickPosX =player.lastTickPosX;
					vehicle.posY =player.posY;
					vehicle.prevPosY =player.prevPosY;
					vehicle.serverPosY =player.serverPosY;
					vehicle.lastTickPosY =player.lastTickPosY;
					vehicle.posZ =player.posZ;
					vehicle.prevPosZ =player.prevPosZ;
					vehicle.serverPosZ =player.serverPosZ;
					vehicle.lastTickPosZ =player.lastTickPosZ;
				}*/
				
				
			}
		}
		//240224追加
		if (target != null && target instanceof EntityGVCLivingBase) 
		{
			EntityGVCLivingBase player = (EntityGVCLivingBase) target;
			if (player.getRidingEntity() instanceof EntityVehicleBase && player.getRidingEntity() != null) {// 1
				EntityVehicleBase vehicle = (EntityVehicleBase) player.getRidingEntity();
				/*{
					player.posX =vehicle.posX;
					player.prevPosX =vehicle.prevPosX;
					player.serverPosX =vehicle.serverPosX;
					player.lastTickPosX =vehicle.lastTickPosX;
					player.posY =vehicle.posY;
					player.prevPosY =vehicle.prevPosY;
					player.serverPosY =vehicle.serverPosY;
					player.lastTickPosY =vehicle.lastTickPosY;
					player.posZ =vehicle.posZ;
					player.prevPosZ =vehicle.prevPosZ;
					player.serverPosZ =vehicle.serverPosZ;
					player.lastTickPosZ =vehicle.lastTickPosZ;
				}*/
				
				
			}
		}
		
		//EntityVehicleBase onUpDateに移動
		/*Entity target2 = event.getEntityLiving();
		if (target2 != null && target2 instanceof EntityVehicleBase) 
		{
			EntityVehicleBase vehi = (EntityVehicleBase) target2;
			if (vehi.canBeSteered() && vehi.getControllingPassenger() != null && vehi.getHealth() > 0.0F)
			{
				if(vehi.getControllingPassenger() instanceof EntityPlayer)
				{
					EntityPlayer player2 = (EntityPlayer) vehi.getControllingPassenger();
					{
						vehi.setMoveForward_PL(player2.moveForward);
						vehi.setMoveStrafing_PL(player2.moveStrafing);
				//		GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(0, vehi.getEntityId()));
				//		GVCLPacketHandler.INSTANCE.sendToServer(new GVCLMessageKeyPressed(600, vehi.getEntityId(), player2.moveForward, player2.moveStrafing, 0));
					}
				}
			}
		}*/
		
		
		
		
	}
	
	@SubscribeEvent
	public void onRiddingAntiLockOn(LivingUpdateEvent event) {
		Entity target = event.getEntityLiving();
		if (target != null && target instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) target;
			if (player.getRidingEntity() instanceof EntityVehicleBase && player.getRidingEntity() != null) {// 1
				EntityVehicleBase vehicle = (EntityVehicleBase) player.getRidingEntity();
				if(vehicle.render_rader && vehicle.renderhud) {
					double x = vehicle.posX;
					double y = vehicle.posY;
					double z = vehicle.posZ;
					double han = 80;
					/*AxisAlignedBB aabb = (new AxisAlignedBB((double) (x - han), (double) (y - han),
							(double) (z - han),
							(double) (x + han), (double) (y + han), (double) (z + han)))
									.grow(han);
			        List llist = vehicle.world.getEntitiesWithinAABB(Entity.class, aabb);*/
					List llist = vehicle.world.getEntitiesWithinAABBExcludingEntity(vehicle, vehicle.getEntityBoundingBox()
		            		.expand(vehicle.motionX, vehicle.motionY, vehicle.motionZ).grow(100.0D));
					boolean alert1 = false;
					boolean alert2 = false;
		            if(llist!=null){
		                for (int lj = 0; lj < llist.size(); lj++) {
		                	
		                	Entity entity1 = (Entity)llist.get(lj);
		                	if(entity1 != null ) {
		                		if ((entity1 instanceof EntityBBase))
			                    {
			                		if(entity1 instanceof EntityB_Missile) {
			                			EntityB_Missile missile = (EntityB_Missile) entity1;
			                			if(missile.mitarget != null && (missile.mitarget == vehicle || (vehicle.getControllingPassenger() != null && missile.mitarget == vehicle.getControllingPassenger()))){
			                				alert2 = true;
			                				break;
			                				
			                			}
			                		}
			                    }
		                		else if (entity1 instanceof EntityGVCLivingBase)
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
		            }
		            if(alert2) {
		            	if(vehicle.world.getWorldTime() %5 == 0) 
		            	{
        					vehicle.playSound(GVCSoundEvent.sound_alert2, 1F, 1.0F);
        			   	 }
		            }else if(alert1){
		            	if(vehicle.world.getWorldTime() %10 == 0) {
        					vehicle.playSound(GVCSoundEvent.sound_alert1, 1F, 1.0F);
        			   	 }
		            }
		            
		            
		            
				}
			}
		}
	}
	
	
}