package gvclib.event.client;

import gvclib.mod_GVCLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GVCEventsClientWorld {
	
	/**24/08/07削除　特に効果はなかった...orz*/
	@SideOnly(Side.CLIENT)
	  @SubscribeEvent
		  public void renderLivingPost(RenderWorldLastEvent event)
		  {
			World world = Minecraft.getMinecraft().world;//event.getEntity().world;
			ParticleManager pm = Minecraft.getMinecraft().effectRenderer;
			int partkazu = Integer.parseInt(pm.getStatistics());
			/*if(world.getWorldTime() %50 == 0 ) {
				System.out.println(String.format(pm.getStatistics()));
				pm.clearEffects(world);
			}*/
			if(mod_GVCLib.cfg_particle_limiter && partkazu >= mod_GVCLib.cfg_particle_limiter_kazu) {
	//			System.out.println(String.format("GVCLibSystem_clearEffectsLimit:" + pm.getStatistics()));
	//			pm.clearEffects(world);
			}
			/*if(world.getWorldTime() %50 == 0 ) {
				System.out.println(String.format(pm.getStatistics()));
				//Minecraft.getMinecraft().gameSettings.particleSetting = 0;
				if(Minecraft.getMinecraft().effectRenderer == null) {
					Minecraft.getMinecraft().effectRenderer = new ParticleManager(Minecraft.getMinecraft().world, Minecraft.getMinecraft().renderEngine);
				}
				//pm.clearEffects(world);
			}*/
		  }
}
