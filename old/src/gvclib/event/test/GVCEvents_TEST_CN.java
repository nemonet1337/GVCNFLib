package gvclib.event.test;

import gvclib.mod_GVCLib;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GVCEvents_TEST_CN {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUPEvent(LivingUpdateEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		
		float bai = 1F;
		
		boolean flag = false;//コントローラー使用時はこれをtrueに
		
		if(flag && target != null && target.world.isRemote) {
			//if(target.world.getWorldTime() %5 == 0) 
			{
				if (mod_GVCLib.proxy.left()) {
					mod_GVCLib.test_x = mod_GVCLib.test_x + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_x);
					System.out.println("x : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.right()) {
					mod_GVCLib.test_x = mod_GVCLib.test_x - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_x);
					System.out.println("x : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.up()) {
					mod_GVCLib.test_y = mod_GVCLib.test_y + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_y);
					System.out.println("y : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.down()) {
					mod_GVCLib.test_y = mod_GVCLib.test_y - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_y);
					System.out.println("y : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.LBRACKET()) {
					mod_GVCLib.test_z = mod_GVCLib.test_z + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_z);
					System.out.println("z : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.RBRACKET()) {
					mod_GVCLib.test_z = mod_GVCLib.test_z - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_z);
					System.out.println("z : " + String.format(dd));
				}
				
				if (mod_GVCLib.proxy.numpad_6()) {
					mod_GVCLib.test_x2 = mod_GVCLib.test_x2 + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_x2);
					System.out.println("x2 : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.numpad_4()) {
					mod_GVCLib.test_x2 = mod_GVCLib.test_x2 - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_x2);
					System.out.println("x2 : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.numpad_8()) {
					mod_GVCLib.test_y2 = mod_GVCLib.test_y2 + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_y2);
					System.out.println("y2 : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.numpad_2()) {
					mod_GVCLib.test_y2 = mod_GVCLib.test_y2 - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_y2);
					System.out.println("y2 : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.numpad_3()) {
					mod_GVCLib.test_z2 = mod_GVCLib.test_z2 + bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_z2);
					System.out.println("z2 : " + String.format(dd));
				}
				if (mod_GVCLib.proxy.numpad_1()) {
					mod_GVCLib.test_z2 = mod_GVCLib.test_z2 - bai;
					String dd = String.format("%1$.2f", mod_GVCLib.test_z2);
					System.out.println("z2 : " + String.format(dd));
				}
			}
		}
	}
}
