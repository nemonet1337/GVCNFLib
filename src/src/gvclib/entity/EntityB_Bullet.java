package gvclib.entity;

import gvclib.mod_GVCLib;
import gvclib.entity.living.EntityVehicleBase;
import gvclib.entity.living.ISoldier;
import gvclib.event.GVCSoundEvent;
import gvclib.network.GVCLClientMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityB_Bullet extends EntityBBase {
	public EntityB_Bullet(World worldIn) {
		super(worldIn);
	}

	public EntityB_Bullet(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityB_Bullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void func_189662_a(DataFixer p_189662_0_) {
		EntityB_Bullet.func_189661_a(p_189662_0_, "EntityB_Bullet");
	}

	public void onUpdate()
    {
        super.onUpdate();
        //if(this.smoke)
        if (!this.inGround) {
        	 if(mod_GVCLib.cfg_bullet_smoke)
             {
             //this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0D, this.posZ, 0.0D, 0.0D, 0.0D);
             }else {
        		// if (this.world.isRemote) this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX, this.posY + 0D, this.posZ, 0.0D, 0.0D, 0.0D);
            	 if (!this.world.isRemote && this.world instanceof WorldServer)
                 {
            		 WorldServer server = (WorldServer) this.world;
        //    		 server.spawnParticle(EnumParticleTypes.CRIT, true, this.posX, this.posY, this.posZ, 1, 0.0D, 0.0D, 0.0D, 0D, 0);
                 }
             }
        	 if(this.world.getWorldTime() %2 == 0 && time > 5 && fly_sound) {
        		 if(!this.inGround)
        		 this.playSound(GVCSoundEvent.sound_hit, 0.5F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        	 }
        }
       
    }
	
	public void setDead(){
		this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY + 0D, this.posZ, 0.0D, 0.0D, 0.0D);
		super.setDead();
	}
	
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		Entity entity = result.entityHit;
		if (entity != null){
			boolean ap = false;
			//if (result.entityHit != null && result.entityHit != this.friend && result.entityHit != this.getThrower()) 
			//if () 
			{
				int i = Bdamege;
				if (this.muteki) {
					result.entityHit.hurtResistantTime = 0;
				}
				if(this.bulletDameID == 1 || this.bulletDameID == 3){
					//i = (int) (i *0.75);
					if(i < 0) {
						if(result.entityHit instanceof EntityLivingBase) {
							EntityLivingBase en = (EntityLivingBase) result.entityHit;
							if(en.getHealth() < en.getMaxHealth()) {
								en.setHealth(en.getHealth() + (i * -1));
								GVCLPacketHandler.INSTANCE2.sendToAll(new GVCLClientMessageKeyPressed(801, result.entityHit.getEntityId()));
		        			}
						}
					}else if(i > 0){
					result.entityHit.attackEntityFrom(GVCDamageSource.causeBulletAP(this, this.getThrower()), (float) i);
					ap = true;
					}else {
						
					}
				}else if(this.P_ID != 0){
					if(result.entityHit instanceof EntityLivingBase) {
						EntityLivingBase en = (EntityLivingBase) result.entityHit;
						//en.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 10));
						en.addPotionEffect(new PotionEffect(Potion.getPotionById(this.P_ID), this.P_TIME, this.P_LEVEL));
					}
					if(i < 0) {
						if(result.entityHit instanceof EntityLivingBase) {
							EntityLivingBase en = (EntityLivingBase) result.entityHit;
							if(en.getHealth() < en.getMaxHealth()) {
								en.setHealth(en.getHealth() + (i * -1));
								GVCLPacketHandler.INSTANCE2.sendToAll(new GVCLClientMessageKeyPressed(801, result.entityHit.getEntityId()));
		        			}
						}
					}else {
						result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);//1
					}
					
				}else {
					if(i < 0) {
						if(result.entityHit instanceof EntityLivingBase) {
							EntityLivingBase en = (EntityLivingBase) result.entityHit;
							if(en.getHealth() < en.getMaxHealth()) {
								en.setHealth(en.getHealth() + (i * -1));
								GVCLPacketHandler.INSTANCE2.sendToAll(new GVCLClientMessageKeyPressed(801, result.entityHit.getEntityId()));
		        			}
						}
					}else {
						result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
					}
				}
			}
			
			this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY + 0D, this.posZ, 0.0D, 0.0D, 0.0D);

			boolean dead_flag = true;
			if(result.entityHit instanceof EntityVehicleBase) {
				EntityVehicleBase vehicle = (EntityVehicleBase) result.entityHit;
				if(Bdamege > vehicle.normal_anti_bullet) {
					dead_flag = false;
				}
			}
			if(result.entityHit instanceof ISoldier && (this.getThrower() != null && this.getThrower() instanceof ISoldier)) {
				dead_flag = false;
			}
			
			if (!this.world.isRemote && !ap) {
				if(dead_flag)this.setDead();
			}
			if(this.bulletDameID == 4 || this.exlevel >= 1) {
				if (!this.world.isRemote) {
					this.world.createExplosion(this, this.posX + 0, this.posY + 0, this.posZ + 0, this.exlevel, false);
				}
			}
		}else {
			{
				if(!this.inGround) {
					if(this.bulletDameID == 4 || this.exlevel >= 1) {
						if (!this.world.isRemote) {
							this.world.createExplosion(this, this.posX + 0, this.posY + 0, this.posZ + 0, this.exlevel, false);
						}
					}
				}
	            BlockPos blockpos = result.getBlockPos();
	            this.xTile = blockpos.getX();
	            this.yTile = blockpos.getY();
	            this.zTile = blockpos.getZ();
	            IBlockState iblockstate = this.world.getBlockState(blockpos);
	            this.inTile = iblockstate.getBlock();
	            this.inData = this.inTile.getMetaFromState(iblockstate);
	            this.motionX = (double)((float)(result.hitVec.x - this.posX));
	            this.motionY = (double)((float)(result.hitVec.y - this.posY));
	            this.motionZ = (double)((float)(result.hitVec.z - this.posZ));
	            float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
	            this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
	            this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
	            this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
	            //this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	            this.playSound(GVCSoundEvent.sound_hit, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	            this.inGround = true;
	           // this.arrowShake = 7;
	           // this.setIsCritical(false);

	            if (iblockstate.getMaterial() != Material.AIR)
	            {
	                this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
	            }
	        }
		}
	}
	
	protected void onBreak(RayTraceResult result) {
		/*BlockPos blockpos = result.getBlockPos();
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block2 = iblockstate.getBlock();
		if(block2 != null){
			if (block2 == Blocks.GLASS_PANE || block2 == Blocks.TALLGRASS){
				Block block = this.world.getBlockState(result.getBlockPos()).getBlock();
				int blockmeta = this.world.getBlockState(result.getBlockPos())
						.getBlock().getMetaFromState(this.world.getBlockState(result.getBlockPos()));
			//	BlockPos blockpos = result.getBlockPos();
				IBlockState iblock = this.world.getBlockState(result.getBlockPos()).getBlock().getStateFromMeta(blockmeta);

				block.onBlockDestroyedByPlayer(this.world, blockpos, iblock);
				block.dropBlockAsItem(this.world, blockpos, iblock, blockmeta);
				this.world.setBlockToAir(blockpos);
			}
		}
		*/
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public void writeSpawnData(ByteBuf buffer){
		super.writeSpawnData(buffer);
	}
	public void readSpawnData(ByteBuf additionalData){
		super.readSpawnData(additionalData);
	}
}