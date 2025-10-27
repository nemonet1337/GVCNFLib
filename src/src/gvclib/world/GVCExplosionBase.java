package gvclib.world;

import gvclib.mod_GVCLib;
import gvclib.network.GVCLClientMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class GVCExplosionBase {
	
	public static void ExplosionKai(Entity entityIn, Entity en, double x, double y, double z, float strength, boolean isFlaming, boolean isSmoking){
		
		if(en == null && entityIn == null)return;
		if(en != null){
			en.world.playSound((EntityPlayer)null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, 
					(1.0F + (en.world.rand.nextFloat() - en.world.rand.nextFloat()) * 0.2F) * 0.7F);
			/*if(FMLCommonHandler.instance().getMinecraftServerInstance() != null) {
				for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers())
	            {
		//			GVCLPacketHandler.INSTANCE2.sendTo((new GVCLClientMessageKeyPressed(0, en.getEntityId(), strength, x, y, z)), player);
	            }
			}*/
		}
		
		if(entityIn != null && !entityIn.world.isRemote){
			GVCExplosion explosion = new GVCExplosion(entityIn.world, entityIn, x, y, z, strength, isFlaming, isSmoking);
			explosion.doExplosionA();
			explosion.doExplosionB(true);
		}
	}
	
}