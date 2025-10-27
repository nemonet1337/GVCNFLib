package gvclib.entity.living.cnt.ai;

import gvclib.entity.living.AI_AirCraftSet;
import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.entity.living.EntityVehicleBase;
import gvclib.entity.living.PL_RoteModel;
import gvclib.event.GVCSoundEvent;
import net.minecraft.entity.EntityLivingBase;

public class AI_EntityMove_HeliBorne {
	public static void heli_airborne(EntityVehicleBase entity) {
				{
					entity.thpower = entity.thmax;
					entity.throttle = entity.thmax;
				}
				if(entity.getFTMode() == 31) {
					entity.rotationp = entity.rotationPitch = 0;
				}
				else if(entity.getFTMode() == 32) {
					entity.rotationp = entity.rotationPitch = 20;
				}
				else {
					entity.rotationp = entity.rotationPitch = 20;
				}
				
				
				
		   	AI_AirCraftSet.setheli_NEW(entity, GVCSoundEvent.sound_heli, 0, entity.sp, 0.1F);
		   	
		   	double d5 = entity.getMoveX() - entity.posX;
				double d7 = entity.getMoveZ() - entity.posZ;
				entity.rote = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;
				if(entity.getFTMode() == 30 || entity.getFTMode() == 35)rote(entity, entity.turnspeed);
				
				double ddx = Math.abs(d5);
				double ddz = Math.abs(d7);
				
				
				if(entity.getFTMode() == 30) {
					if ((ddx < entity.getArmID_S() && ddz < entity.getArmID_S())) {
						entity.setFTMode(31);
					}
					for(int ri = 2; ri < entity.riddng_maximum; ++ri) {
						if(entity.getPassengers().size() > ri) {
							if (entity.getPassengers().get(ri) != null) {
								if (entity.getPassengers().get(ri) instanceof EntityLivingBase) {
									EntityLivingBase entity2 = (EntityLivingBase)entity.getPassengers().get(ri);
									//entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
									entity2.posX =entity.posX;
									entity2.prevPosX =entity.prevPosX;
									entity2.serverPosX =entity.serverPosX;
									entity2.lastTickPosX =entity.lastTickPosX;
									entity2.posY =entity.posY;
									entity2.prevPosY =entity.prevPosY;
									entity2.serverPosY =entity.serverPosY;
									entity2.lastTickPosY =entity.lastTickPosY;
									entity2.posZ =entity.posZ;
									entity2.prevPosZ =entity.prevPosZ;
									entity2.serverPosZ =entity.serverPosZ;
									entity2.lastTickPosZ =entity.lastTickPosZ;
								}
							}
						}
					}
				}
				if(entity.getFTMode() == 31) {
					++entity.tasktime;
					
					if(entity.tasktime > 40) {
						if(entity.tasktime %10 == 0 &&  entity.getPassengers().size() > 1) {
							if (entity.getPassengers().get(1) != null) {
								if (entity.getPassengers().get(1) instanceof EntityLivingBase) {
									EntityLivingBase entity2 = (EntityLivingBase)entity.getPassengers().get(1);
									entity2.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
									entity2.dismountRidingEntity();
									entity2.posX = entity.posX + entity.world.rand.nextInt(4) - 2;
									entity2.posZ = entity.posZ + entity.world.rand.nextInt(4) - 2;
								}
							}
						}
					}
					
					if(entity.tasktime > 100) {
						entity.setFTMode(32);
						entity.tasktime = 0;
					}
				}
				
				
				if(entity.getFTMode() == 32) {
					if ((ddx > 80 || ddz > 80)) {
						if (!entity.world.isRemote) {
							
							for(int m = 0; m < entity.getPassengers().size(); ++m) {
								if (entity.getPassengers().get(m) != null) {
									if (entity.getPassengers().get(m) instanceof EntityLivingBase) {
										EntityLivingBase entity2 = (EntityLivingBase)entity.getPassengers().get(m);
										entity2.setDead();
									}
								}
							}
							
							entity.setDead();
							
						}
					}
				}
		   }
		   
		   
		   private static void rote(EntityGVCLivingBase entity, float turnspeed) {
				if(entity.rote > 360 || entity.rote < -360) {
					entity.rote = entity.rote %360;
				}
				float f3 = (float) (entity.rotationYawHead - entity.rote);
				if(entity.rotationYawHead != entity.rote){
		   		if(f3 > turnspeed){
						if(f3 > 180F){
							PL_RoteModel.rotemodel(entity,+ turnspeed);
						}else{
							PL_RoteModel.rotemodel(entity,- turnspeed);
						}
					}
					else if(f3 < -turnspeed){
						if(f3 < -180F){
							PL_RoteModel.rotemodel(entity,- turnspeed);
						}else{
							PL_RoteModel.rotemodel(entity,+ turnspeed);
						}
					}
		       }
				entity.rotationYaw  = entity.rotationYawHead;
			}
}
