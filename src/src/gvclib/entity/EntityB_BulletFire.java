package gvclib.entity;

import gvclib.world.GVCExplosionBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityB_BulletFire extends EntityBBase {
	public EntityB_BulletFire(World worldIn) {
		super(worldIn);
	}

	public EntityB_BulletFire(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityB_BulletFire(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void func_189662_a(DataFixer p_189662_0_) {
		EntityBBase.func_189661_a(p_189662_0_, "Snowball");
	}

	public void onUpdate()
    {
        super.onUpdate();
        float sizee = time / 50;
        this.setSize(0.5F + sizee, 0.5F + sizee);
        //if(this.smoke)
        {
        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
        //if (this.isFireballFiery())
        if(this.time > 3){
            this.setFire(1);
        }
        //if(this.onGround)
        /*{
        	if (this.world.isAirBlock(new BlockPos(this.posX + 0, this.posY + 0, this.posZ)) 
        			&& !this.world.isAirBlock(new BlockPos(this.posX + 0, this.posY -0.1, this.posZ))
        			&& this.world.rand.nextInt(5) == 0)
        	{
        		//this.worldObj.setBlockState(new BlockPos(this.posX + 0, this.posY + 0, this.posZ), Blocks.FIRE.getDefaultState());
        		//this.worldObj.createExplosion(this, this.posX + 0, this.posY + 0, this.posZ + 0, 0, false);
        	}
        }*/
    }
	
	protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (!result.entityHit.isImmuneToFire())
                {
                    boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);

                    if (flag)
                    {
                        this.applyEnchantments(this.getThrower(), result.entityHit);
                        result.entityHit.setFire(5);
                    }
                }
                result.entityHit.hurtResistantTime = 0;
                int i = Bdamege;
				result.entityHit.attackEntityFrom(GVCDamageSource.causeBulletAP(this, this.getThrower()), (float) i);
				
				if(exlevel > 0)GVCExplosionBase.ExplosionKai(this.getThrower(), 
						this, this.posX + 0, this.posY + 0, this.posZ + 0, this.exlevel, this.exfire,  this.ex);
            }
            //else
            {
                /*boolean flag1 = true;

                if (this.getThrower() != null && this.getThrower() instanceof EntityLiving)
                {
                    flag1 = this.world.getGameRules().getBoolean("mobGriefing");
                }

                if (flag1)
                {
                    BlockPos blockpos = result.getBlockPos().offset(result.sideHit);

                    if (this.world.isAirBlock(blockpos) && this.ex)
                    {
                        this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                    }
                }*/
            }

            this.setDead();
        }
    }
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	/*protected void onImpact(RayTraceResult result) {
		if (result.entityHit != this.friend && result.entityHit != this.getThrower()) {
			if (result.entityHit != null) {
				int i = Bdamege;
				if (this.muteki) {
					result.entityHit.hurtResistantTime = 0;
				}
				if (!result.entityHit.isImmuneToFire())
                {
                    boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);

                    if (flag)
                    {
                        this.applyEnchantments(this.getThrower(), result.entityHit);
                        result.entityHit.setFire(5);
                    }
                }
			}else
            {
            	if (!this.worldObj.isRemote)
            	{
                boolean flag1 = true;
                //if (flag1)
                {
                    BlockPos blockpos = result.getBlockPos().offset(result.sideHit);
                    if (this.worldObj.isAirBlock(blockpos))
                    {
                        this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                    }
                }
            	}
            	if (!this.worldObj.isRemote)
            	{
                this.setDead();
            	}
            }

			if (!this.worldObj.isRemote) {
				this.setDead();
			}
		}
		
		//if (!this.worldObj.isRemote)
        /*{
            if (result.entityHit != null)
            {
            	if (!this.worldObj.isRemote)
            	{
            	result.entityHit.hurtResistantTime = 0;
                if (!result.entityHit.isImmuneToFire())
                {
                    boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);

                    if (flag)
                    {
                        this.applyEnchantments(this.getThrower(), result.entityHit);
                        result.entityHit.setFire(5);
                    }
                }
            	}
            	if (!this.worldObj.isRemote)
            	{
                this.setDead();
            	}
            }
            else
            {
            	if (!this.worldObj.isRemote)
            	{
                boolean flag1 = true;
                //if (flag1)
                {
                    BlockPos blockpos = result.getBlockPos().offset(result.sideHit);
                    if (this.worldObj.isAirBlock(blockpos))
                    {
                        this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                    }
                }
            	}
            	if (!this.worldObj.isRemote)
            	{
                this.setDead();
            	}
            }
            
        }*/
	//}
	
	/**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }
	
	protected void onBreak(RayTraceResult result) {
		if (this.world.getBlockState(result.getBlockPos()).getBlock() == Blocks.GLASS_PANE){
			Block block = this.world.getBlockState(result.getBlockPos()).getBlock();
			int blockmeta = this.world.getBlockState(result.getBlockPos())
					.getBlock().getMetaFromState(this.world.getBlockState(result.getBlockPos()));
			BlockPos blockpos = result.getBlockPos();
			IBlockState iblock = this.world.getBlockState(result.getBlockPos()).getBlock().getStateFromMeta(blockmeta);

			block.onBlockDestroyedByPlayer(this.world, blockpos, iblock);
			block.dropBlockAsItem(this.world, blockpos, iblock, blockmeta);
			this.world.setBlockToAir(blockpos);
		}
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}