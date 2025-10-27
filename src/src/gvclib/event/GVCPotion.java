package gvclib.event;

import gvclib.mod_GVCLib;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class GVCPotion {

	public static boolean getVaniraPotion(Potion potion) {
		boolean flag;
		if (potion != MobEffects.SPEED
				&&potion != MobEffects.SLOWNESS
				&&potion != MobEffects.HASTE
				&&potion != MobEffects.MINING_FATIGUE
				&&potion != MobEffects.STRENGTH
				&&potion != MobEffects.INSTANT_HEALTH
				&&potion != MobEffects.INSTANT_DAMAGE
				&&potion != MobEffects.JUMP_BOOST
				&&potion != MobEffects.NAUSEA
				&&potion != MobEffects.REGENERATION
				&&potion != MobEffects.RESISTANCE
				&&potion != MobEffects.FIRE_RESISTANCE
				&&potion != MobEffects.WATER_BREATHING
				&&potion != MobEffects.INVISIBILITY
				&&potion != MobEffects.BLINDNESS
				&&potion != MobEffects.NIGHT_VISION
				&&potion != MobEffects.HUNGER
				&&potion != MobEffects.WEAKNESS
				&&potion != MobEffects.POISON
				&&potion != MobEffects.WITHER
				&&potion != MobEffects.HEALTH_BOOST
				&&potion != MobEffects.ABSORPTION
				&&potion != MobEffects.SATURATION
				&&potion != MobEffects.GLOWING
				&&potion != MobEffects.LEVITATION
				&&potion != MobEffects.LUCK
				&&potion != MobEffects.LUCK)
		{
			flag = false;
		}else {
			flag = true;
		}
		/*
		 * switch (potion.) { case MobEffects.POISON: break; case MobEffects.POISON:
		 * break; }
		 */
		return flag;
	}

}