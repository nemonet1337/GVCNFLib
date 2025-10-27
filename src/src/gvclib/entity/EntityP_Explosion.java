package gvclib.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityP_Explosion extends EntityTNTBase {
	
	public double startposX = 0;
    public double startposY = 0;
    public double startposZ = 0;
    
    public String entity_tex = null;
	
	public EntityP_Explosion(World worldIn) {
		super(worldIn);
	}

	public EntityP_Explosion(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityP_Explosion(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void func_189662_a(DataFixer p_189662_0_) {
		EntityBBase.func_189661_a(p_189662_0_, "Snowball");
	}
	
	protected static final DataParameter<Float> SIZE = 
    		EntityDataManager.<Float>createKey(EntityP_Explosion.class, DataSerializers.FLOAT);
	
	protected void entityInit()
    {
		super.entityInit();
    	this.dataManager.register(SIZE, 1F);
    }
	
	 /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setFloat("expsize", this.getExpSize());
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	this.setExpSize(compound.getFloat("expsize"));
    }
    
    public float getExpSize() {
		return ((this.dataManager.get(SIZE)));
	}
	public void setExpSize(float s) {
		this.dataManager.set(SIZE, Float.valueOf(new Float(s)));
	}
    
	
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness()
    {
        return 1.0F;
    }
    
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }

    public float[] exp_x = new float[16];
    public float[] exp_y = new float[16];
    public float[] exp_z = new float[16];
    
	public void onUpdate()
    {
        super.onUpdate();
       
        /*if(this.getExpSize() >= 2 && time == 0) {
        	/*int exp_size = (int)this.getExpSize() * (int)this.getExpSize();
        	if(exp_size >= 255)exp_size = 255;
        	for(int size = 0; size < exp_size; ++size) {
        		exp_x[size] = this.world.rand.nextInt((int)this.getExpSize())*2 - this.getExpSize();
        		exp_y[size] = this.world.rand.nextInt((int)this.getExpSize())*2 - this.getExpSize();
        		exp_z[size] = this.world.rand.nextInt((int)this.getExpSize())*2 - this.getExpSize();
        	}*
        	 for (int i = 0; i < 6; ++i)
             {
             	exp_x[i] = (float) ((this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D);
         		exp_y[i] = (float) ((this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D);
         		exp_z[i] = (float) ((this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D);
             }
        }*/
       
        
     //   this.setPosition(this.startposX, this.startposY, this.startposZ);
        
        ++time;
        if(time > timemax){
        	if(!this.world.isRemote){
        	this.setDead();
        	}
        }
    }
	
	private void explode()
    {
    }
	
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
	}
}