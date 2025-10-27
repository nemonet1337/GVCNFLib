package gvclib.event;


import gvclib.mod_GVCLib;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

/**
 * Registers this mod's {@link SoundEvent}s.
 *
 * @author Choonster
 */
//@SuppressWarnings("WeakerAccess")
public class GVCSoundEvent {
	/*public static SoundEvent Fire_Bullet;
	public static SoundEvent Fire_BulletHG;
	public static SoundEvent Reload;
	public static SoundEvent Shot2;
	public static SoundEvent Jet1;
	public static SoundEvent Jet2;
	public static SoundEvent Lock;
	public static SoundEvent Fireee;
	public static SoundEvent Fire_GL;
	public static SoundEvent Fire_Cannon;
	public static SoundEvent Fire_Rocket;*/
	
	public static SoundEvent fire_rifle;
	public static SoundEvent fire_rifle2;
	public static SoundEvent fire_rifle3;
	public static SoundEvent fire_rifle4;
	public static SoundEvent fire_rifle5;
	public static SoundEvent fire_rifle_ak;
	public static SoundEvent fire_hg;
	public static SoundEvent fire_hg2;
	public static SoundEvent fire_mg;
	public static SoundEvent fire_mg2;
	public static SoundEvent fire_lmg;
	public static SoundEvent fire_hmg;
	public static SoundEvent fire_arrow;
	public static SoundEvent fire_supu;
	public static SoundEvent fire_cannon;
	public static SoundEvent fire_cannon2;
	public static SoundEvent fire_rail;
	public static SoundEvent fire_amr;
	public static SoundEvent fire_missile;
	public static SoundEvent fire_roket;
	public static SoundEvent fire_grenade;
	public static SoundEvent fire_gl;
	public static SoundEvent fire_20mm;
	public static SoundEvent fire_30mm;
	public static SoundEvent fire_havrycannon;
	public static SoundEvent fire_fire;
	public static SoundEvent throw_grenade;
	
	public static SoundEvent reload_cannon;
	public static SoundEvent reload_rail;
	public static SoundEvent reload_mg;
	public static SoundEvent reload_mag;
	public static SoundEvent reload_clip;
	public static SoundEvent reload_cocking;
	public static SoundEvent reload_shell;
	
	public static SoundEvent sound_tank;
	public static SoundEvent sound_car;
	public static SoundEvent sound_heli;
	public static SoundEvent sound_pera;
	public static SoundEvent sound_call;
	
	public static SoundEvent sound_jet1;
	public static SoundEvent sound_jet2;
	public static SoundEvent sound_lock;
	
	public static SoundEvent sound_fall_shell;
	public static SoundEvent sound_robo_step;
	public static SoundEvent sound_robo_boost;
	public static SoundEvent sound_hit;
	public static SoundEvent sound_alert1;
	public static SoundEvent sound_alert2;

	/**
	 * Register the {@link SoundEvent}s.
	 */
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		/*Fire_Bullet = registerSound("/gvclib.fire_rifle");
		Fire_BulletHG = registerSound("/gvclib.fire_hg");
		Reload = registerSound("/gvclib.reload_mag");
		Shot2 = registerSound("/gvclib.shot2");
		Jet1 = registerSound("/gvclib.sound_jet1");
		Jet2 = registerSound("/gvclib.sound_jet2");
		Lock = registerSound("/gvclib.lock");
		Fireee = registerSound("/gvclib.fire_fire");
		Fire_GL = registerSound("/gvclib.fire_grenade");
		Fire_Cannon = registerSound("/gvclib.fire_cannon");
		Fire_Rocket = registerSound("/gvclib.fire_roket");*/
		
		fire_rifle = registerSound("gvclib.fire_rifle", event);
		fire_rifle2 = registerSound("gvclib.fire_rifle2", event);
		fire_rifle3 = registerSound("gvclib.fire_rifle3", event);
		fire_rifle4 = registerSound("gvclib.fire_rifle4", event);
		fire_rifle5 = registerSound("gvclib.fire_rifle5", event);
		fire_rifle_ak = registerSound("gvclib.fire_rifle_ak", event);
		fire_hg = registerSound("gvclib.fire_hg", event);
		fire_hg2 = registerSound("gvclib.fire_hg2", event);
		fire_mg = registerSound("gvclib.fire_mg", event);
		fire_mg2 = registerSound("gvclib.fire_mg2", event);
		fire_lmg = registerSound("gvclib.fire_lmg", event);
		fire_hmg = registerSound("gvclib.fire_hmg", event);
		fire_arrow = registerSound("gvclib.fire_arrow", event);
		fire_supu = registerSound("gvclib.fire_supu", event);
		fire_cannon = registerSound("gvclib.fire_cannon", event);
		fire_cannon2 = registerSound("gvclib.fire_cannon2", event);
		fire_rail = registerSound("gvclib.fire_rail", event);
		fire_amr = registerSound("gvclib.fire_amr", event);
		fire_missile = registerSound("gvclib.fire_missile", event);
		fire_roket = registerSound("gvclib.fire_roket", event);
		fire_grenade = registerSound("gvclib.fire_grenade", event);
		fire_gl = registerSound("gvclib.fire_gl", event);
		fire_20mm = registerSound("gvclib.fire_20mm", event);
		fire_30mm = registerSound("gvclib.fire_30mm", event);
		fire_havrycannon = registerSound("gvclib.fire_havrycannon", event);
		fire_fire = registerSound("gvclib.fire_fire", event);
		throw_grenade = registerSound("gvclib.throw_grenade", event);
		reload_cannon = registerSound("gvclib.reload_cannon", event);
		reload_rail = registerSound("gvclib.reload_rail", event);
		reload_mg = registerSound("gvclib.reload_mg", event);
		reload_mag = registerSound("gvclib.reload_mag", event);
		reload_clip = registerSound("gvclib.reload_clip", event);
		reload_cocking = registerSound("gvclib.reload_cocking", event);
		reload_shell = registerSound("gvclib.reload_shell", event);
		sound_tank = registerSound("gvclib.sound_tank", event);
		sound_car = registerSound("gvclib.sound_car", event);
		sound_heli = registerSound("gvclib.sound_heli", event);
		sound_pera = registerSound("gvclib.sound_pera", event);
		sound_call = registerSound("gvclib.sound_call", event);
		sound_jet1 = registerSound("gvclib.sound_jet1", event);
		sound_jet2 = registerSound("gvclib.sound_jet2", event);
		
		sound_lock = registerSound("gvclib.sound_lock", event);
		
		sound_fall_shell = registerSound("gvclib.sound_fall_shell", event);
		sound_robo_step = registerSound("gvclib.sound_robo_step", event);
		sound_robo_boost = registerSound("gvclib.sound_robo_boost", event);
		sound_hit = registerSound("gvclib.sound_hit", event);
		sound_alert1 = registerSound("gvclib.sound_alert1", event);
		sound_alert2 = registerSound("gvclib.sound_alert2", event);
		
		event.getRegistry().register(fire_rifle);
		event.getRegistry().register(fire_rifle2);
		event.getRegistry().register(fire_rifle3);
		event.getRegistry().register(fire_rifle4);
		event.getRegistry().register(fire_rifle5);
		event.getRegistry().register(fire_rifle_ak);
		event.getRegistry().register(fire_hg);
		event.getRegistry().register(fire_hg2);
		event.getRegistry().register(fire_lmg);
		event.getRegistry().register(fire_hmg);
		event.getRegistry().register(fire_mg2);
		event.getRegistry().register(fire_mg);
		event.getRegistry().register(fire_arrow);
		event.getRegistry().register(fire_supu);
		event.getRegistry().register(fire_cannon);
		event.getRegistry().register(fire_cannon2);
		event.getRegistry().register(fire_rail);
		event.getRegistry().register(fire_amr);
		event.getRegistry().register(fire_missile);
		event.getRegistry().register(fire_roket);
		event.getRegistry().register(fire_grenade);
		event.getRegistry().register(fire_gl);
		event.getRegistry().register(fire_20mm);
		event.getRegistry().register(fire_30mm);
		event.getRegistry().register(fire_havrycannon);
		event.getRegistry().register(fire_fire);
		event.getRegistry().register(throw_grenade);
		event.getRegistry().register(reload_cannon);
		event.getRegistry().register(reload_rail);
		event.getRegistry().register(reload_mg);
		event.getRegistry().register(reload_mag);
		event.getRegistry().register(reload_clip);
		event.getRegistry().register(reload_cocking);
		event.getRegistry().register(reload_shell);
		event.getRegistry().register(sound_tank);
		event.getRegistry().register(sound_car);
		event.getRegistry().register(sound_heli);
		event.getRegistry().register(sound_pera);
		event.getRegistry().register(sound_call);
		event.getRegistry().register(sound_jet1);
		event.getRegistry().register(sound_jet2);
		event.getRegistry().register(sound_lock);
		
		event.getRegistry().register(sound_fall_shell);
		event.getRegistry().register(sound_robo_step);
		event.getRegistry().register(sound_robo_boost);
		event.getRegistry().register(sound_hit);
		event.getRegistry().register(sound_alert1);
		event.getRegistry().register(sound_alert2);
	}

	/**
	 * Register a {@link SoundEvent}.
	 *
	 * @param soundName The SoundEvent's name without the testmod3 prefix
	 * @return The SoundEvent
	 */
	private static SoundEvent registerSound(String soundName, RegistryEvent.Register<SoundEvent> event) {
		/*final ResourceLocation soundID = new ResourceLocation(mod_GVCLib.MOD_ID, soundName);
		//return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
		event.getRegistry().register(new SoundEvent(soundID).setRegistryName(soundID));		
	//	System.out.println(String.format("GVCLib-" + soundName));
		return new SoundEvent(new ResourceLocation(mod_GVCLib.MOD_ID, soundName)).setRegistryName(soundName);*/
		ResourceLocation soundID = new ResourceLocation(mod_GVCLib.MOD_ID, soundName);
	//	event.getRegistry().register(new SoundEvent(soundID).setRegistryName(soundID));		
		return new SoundEvent(soundID).setRegistryName(soundID);
	}
}