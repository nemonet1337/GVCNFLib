package net.blacklab.lmr.entity.littlemaid;

import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.world.World;

public class EntityLittleMaid extends EntityGolem
{
    private static final String __OBFID = "CL_00001650";

    private int maidDominantArm;
    
    public EntityLittleMaid(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 1.9F);
    }
    
    public int getDominantArm() {
		return maidDominantArm;
	}
    
}