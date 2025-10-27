package gvclib.block;


import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockScaffold extends Block
{

	protected static final AxisAlignedBB SC_AABB = new AxisAlignedBB(0.01D, 0.0D, 0.01D, 0.99D, 1.0D, 0.99D);
	
    public BlockScaffold()
    {
        super(Material.WOOD);
        setHardness(1F);
        setResistance(50.0F);
        //setLightOpacity(1);
        //setLightLevel(1.0F);
    }
    
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
    	BlockPos posy1 = new BlockPos(pos.getX(), pos.getY() + 1,pos.getZ());
    	BlockPos posy0 = new BlockPos(pos.getX(), pos.getY() - 1,pos.getZ());
    	BlockPos posx1 = new BlockPos(pos.getX() + 1, pos.getY(),pos.getZ());
    	BlockPos posx0 = new BlockPos(pos.getX() - 1, pos.getY(),pos.getZ());
    	BlockPos posz1 = new BlockPos(pos.getX(), pos.getY(),pos.getZ() + 1);
    	BlockPos posz0 = new BlockPos(pos.getX(), pos.getY(),pos.getZ() - 1);
    	
    	if(worldIn.getBlockState(posy1).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posy1).getBlock();
    		///scaffold.breakBlock(worldIn, posy1, state);
    		scaffold.dropBlockAsItem(worldIn, posy1, state, 0);
    		worldIn.setBlockToAir(posy1);
    	}
    	if(worldIn.getBlockState(posy0).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posy0).getBlock();
    		//scaffold.breakBlock(worldIn, posy0, state);
    		scaffold.dropBlockAsItem(worldIn, posy0, state, 0);
    		worldIn.setBlockToAir(posy0);
    	}
    	if(worldIn.getBlockState(posx1).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posx1).getBlock();
    		//scaffold.breakBlock(worldIn, posx1, state);
    		scaffold.dropBlockAsItem(worldIn, posx1, state, 0);
    		worldIn.setBlockToAir(posx1);
    	}
    	if(worldIn.getBlockState(posx0).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posx0).getBlock();
    		//scaffold.breakBlock(worldIn, posx0, state);
    		scaffold.dropBlockAsItem(worldIn, posx0, state, 0);
    		worldIn.setBlockToAir(posx0);
    	}
    	if(worldIn.getBlockState(posz1).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posz1).getBlock();
    		//scaffold.breakBlock(worldIn, posz1, state);
    		scaffold.dropBlockAsItem(worldIn, posz1, state, 0);
    		worldIn.setBlockToAir(posz1);
    	}
    	if(worldIn.getBlockState(posz0).getBlock() instanceof BlockScaffold) {
    		BlockScaffold scaffold = (BlockScaffold) worldIn.getBlockState(posz0).getBlock();
    		//scaffold.breakBlock(worldIn, posz0, state);
    		scaffold.dropBlockAsItem(worldIn, posz0, state, 0);
    		worldIn.setBlockToAir(posz0);
    	}
    }
    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    /*public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }*/
    
    //　本来isLadderで実装出来るはずがなぜかうまくいかなかったので追加
    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	double x = pos.getX();
    	double y = pos.getY();
    	double z = pos.getZ();
    	//if (!worldIn.isRemote) 
    	if(entityIn != null && entityIn instanceof EntityLivingBase ){
    		EntityLivingBase en = (EntityLivingBase) entityIn;
    		{
    			float f9 = 0.15F;
                en.motionX = MathHelper.clamp(en.motionX, -0.15000000596046448D, 0.15000000596046448D);
                en.motionZ = MathHelper.clamp(en.motionZ, -0.15000000596046448D, 0.15000000596046448D);
                en.fallDistance = 0.0F;

                if (en.motionY < -0.15D)
                {
                    en.motionY = -0.15D;
                }

                boolean flag = en.isSneaking() && en instanceof EntityPlayer;

                if (flag && en.motionY < 0.0D)
                {
                    en.motionY = 0.0D;
                }
                if(entityIn instanceof EntityPlayer){
                	if(en.moveForward > 0.0F) {
            			en.motionY = 0.2D;
            		}
                }else {
                	en.motionY = 0.2D;
                }
    		}
    	}
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return SC_AABB;
    }
    /*@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return SC_AABB;
    }*/
    
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    /**
     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
    @Override 
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) { return true; }
}
