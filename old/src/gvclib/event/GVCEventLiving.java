package gvclib.event;

import java.util.List;

import gvclib.mod_GVCLib;
import gvclib.entity.living.EntityGVCLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GVCEventLiving {
	
	/*@SubscribeEvent
	public void onUpdateEvent_ChunkDebag(ChunkEvent.Unload event) {
		Chunk chunk = event.getChunk();
		ClassInheritanceMultiMap<Entity>[] entitylist = chunk.getEntityLists();
		if (entitylist != null) {
			for (int lj = 0; lj < entitylist.length; lj++) {
				for (Entity entity1 : chunk.getEntityLists()[lj])
	            {
					if(entity1 != null && entity1 instanceof EntityLivingBase) {
						if(entity1.getRidingEntity() != null && entity1.getRidingEntity() instanceof EntityLivingBase) {
							EntityLivingBase vehicle = (EntityLivingBase) entity1.getRidingEntity();
							entity1.posX = vehicle.posX;
				        	entity1.posZ = vehicle.posZ;
				        	System.out.println(entity1.getCustomNameTag() + " :Ridding :" + vehicle.getCustomNameTag());
						}
					}
	            }
			}
		}
	}*/
	
	
	
	@SubscribeEvent
	public void onUpdateEvent_Debag(WorldEvent.Unload event) {
		
		//if(mod_GVCLib.cfg_mobdismount_insave)
		{
			World world = event.getWorld();
			List<Entity> entitylist = world.loadedEntityList;
			if (entitylist != null) {
				for (int lj = 0; lj < entitylist.size(); lj++) {
					Entity entity1 = (Entity) entitylist.get(lj);
					if (entity1.canBeCollidedWith()) {
						/*if(entity1 != null && entity1 instanceof EntityGVCLivingBase) {
							if (entity1.isRiding()) {
								entity1.dismountRidingEntity();
							}
						}*/
						if(entity1 != null && entity1 instanceof EntityLivingBase) {
							if(entity1.getRidingEntity() != null && entity1.getRidingEntity() instanceof EntityLivingBase) {
								EntityLivingBase vehicle = (EntityLivingBase) entity1.getRidingEntity();
								int ixx = MathHelper.floor(entity1.posX / 16.0D);
						        int jxx = MathHelper.floor(entity1.posZ / 16.0D);
						        
						        int iyy = MathHelper.floor(vehicle.posX / 16.0D);
						        int jyy = MathHelper.floor(vehicle.posZ / 16.0D);

						        if (ixx != iyy || jxx != jyy)
						        {
						        	/*entity1.dismountRidingEntity();
						        	entity1.posX = vehicle.posX;
						        	entity1.posZ = vehicle.posZ;
						        	entity1.posY = vehicle.posY;
						        	entity1.chunkCoordX = vehicle.chunkCoordX;
						        	entity1.chunkCoordY = vehicle.chunkCoordY;
						        	entity1.chunkCoordZ = vehicle.chunkCoordZ;*/
						        	
		//				        	 System.out.println(entity1.getCustomNameTag() + "  dismountRidingEntity  " + vehicle.getCustomNameTag());
						        }
		//				        System.out.println(entity1.getCustomNameTag() + "isRidding");
							}
						}
					}
				}
			}
		}
	}
	
	/*@SubscribeEvent
	public void onUpdateEvent_Debag3(WorldEvent.Load event) {
		
		//if(mod_GVCLib.cfg_mobdismount_insave)
		{
			World world = event.getWorld();
			List<Entity> entitylist = world.loadedEntityList;
			if (entitylist != null) {
				for (int lj = 0; lj < entitylist.size(); lj++) {
					Entity entity1 = (Entity) entitylist.get(lj);
					if (entity1.canBeCollidedWith()) {
						/*if(entity1 != null && entity1 instanceof EntityGVCLivingBase) {
							if (entity1.isRiding()) {
								entity1.dismountRidingEntity();
							}
						}***
						if(entity1 != null && entity1 instanceof EntityLivingBase) {
							if(entity1.getRidingEntity() != null && entity1.getRidingEntity() instanceof EntityLivingBase) {
								EntityLivingBase vehicle = (EntityLivingBase) entity1.getRidingEntity();
								int ixx = MathHelper.floor(entity1.posX / 16.0D);
						        int jxx = MathHelper.floor(entity1.posZ / 16.0D);
						        
						        int iyy = MathHelper.floor(vehicle.posX / 16.0D);
						        int jyy = MathHelper.floor(vehicle.posZ / 16.0D);

						        if (ixx != iyy || jxx != jyy)
						        {
						        	entity1.posX = vehicle.posX;
						        	entity1.posZ = vehicle.posZ;
						        	entity1.dismountRidingEntity();
						        	System.out.println(entity1.getCustomNameTag() + "  dismountRidingEntity  " + vehicle.getCustomNameTag());
						        }
						        System.out.println(entity1.getCustomNameTag() + "isRidding");
							}
						}
					}
				}
			}
		}
	}*/
	
	
	
	
	
public void onUpdateEvent_Debag2(WorldEvent.Unload event) {
		
		{
			World world = event.getWorld();
			List<Entity> entitylist = world.loadedEntityList;
			if (entitylist != null) {
				for (int lj = 0; lj < entitylist.size(); lj++) {
					Entity entity1 = (Entity) entitylist.get(lj);
					if (entity1.canBeCollidedWith()) {
						if(entity1 != null && entity1 instanceof EntityGVCLivingBase) {
							Chunk chunk = world.getChunkFromBlockCoords(entity1.getPosition());
							entity1.posX = chunk.x*16;
							entity1.posZ = chunk.z*16;
						}
					}
				}
			}
		}
		
	}
}
