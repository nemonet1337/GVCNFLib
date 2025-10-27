package gvclib;

import net.minecraftforge.common.config.Configuration;

public class GVCConfig {
	public void init(Configuration config, mod_GVCLib mod)
	{
		
		//debag_mode
		{
			mod.cfg_debag = config.getBoolean("cfg_debag", "debag_mode", false, "debag_mode");
			
			mod.cfg_debag_weather = config.getBoolean("cfg_debag_weather", "debag_mode", false, "debag_weather_mode");
			
			mod.cfg_debag_gun_mugen = config.getBoolean("cfg_debag_gun_mugen", "debag_mode", false, "cfg_debag_gun_mugen");
		}
		
		//optifine
		{
			mod.cfg_optifine	= config.getBoolean("cfg_optifine", "optifine", false, "optifine");
			mod.cfg_optifiney	= config.getFloat("cfg_optifiney", "optifine", (float)1.60D, 0, 20, "optifine");
			mod.cfg_optifineys	= config.getFloat("cfg_optifineys", "optifine", (float)1.54D, 0, 20,  "optifine");
		}
		
		//system
		{
			mod.cfg_particle_limiter = config.getBoolean("cfg_particle_limiter", "system", true, "particle_limiter");
			mod.cfg_particle_limiter_kazu = config.getInt("cfg_particle_limiter_kazu", "system", 9999, 0, 16383, "particle_limiter_kazu");
			
			mod.cfg_multiCoreLoading	= config.getBoolean("cfg_multiCoreLoading", "system", true, "debag_mode");
		}
		
		//entity
		{
			mod.cfg_bullet_smoke	= config.getBoolean("cfg_bullet_smoke", "entity", false, "bullet_smoke");
			mod.cfg_bullet_living	= config.getInt("cfg_bullet_living", "entity", 80, 0, 8000, "bullet_living");
			mod.cfg_Instant_death_avoidance	= config.getBoolean("cfg_Instant_death_avoidance", "entity", true, "Instant_death_avoidance");
			
			mod.cfg_turret_lockpoint	= config.getBoolean("cfg_turret_lockpoint", "entity", true, "turret_lockpoint");
			mod.cfg_mobdismount_insave	= config.getBoolean("cfg_mobdismount_insave", "entity", true, "mobdismount_insave");
			
			mod.cfg_vehicle_server_client_async	= config.getBoolean("cfg_vehicle_server_client_async", "entity", true, "vehicle_server_client_async");
			
			mod.cfg_vehicle_death_fire	= config.getBoolean("cfg_vehicle_death_fire", "entity", true, "vehicle_death_fire");
		}
		
		//entityrender
		{
			mod.cfg_entity_render_range	= config.getFloat("cfg_entity_render_range", "entityrender", (float)20D, 1, 100, "entity_render_range");
		}
		
		//explotion
		{
			mod.cfg_explotion_drop	=config.getFloat("cfg_explotion_drop", "explotion", (float)1D, 0, 1, "explotion_drop");
			mod.cfg_explotion_breakdirt	= config.getBoolean("cfg_explotion_breakdirt", "explotion", true, "explotion_breakdirt");
		}
		
		//gun
		{
			mod.arm_lmm	= config.getBoolean("arm_lmm", "gun", false, "arm");		
			mod.cfg_gun_renderrange_limiter	= config.getBoolean("cfg_gun_renderrange_limiter", "gun", true, "render_range_limiter");		
		}
		
		//key
		{
			mod.cfg_key_x	= config.getString("cfg_key_x", "key", "X", "KEY_X");
			mod.cfg_key_r	= config.getString("cfg_key_r", "key", "R", "KEY_R");
			mod.cfg_key_c	= config.getString("cfg_key_c", "key", "C", "KEY_C");
			mod.cfg_key_z	= config.getString("cfg_key_z", "key", "Z", "KEY_Z");
			mod.cfg_key_g	= config.getString("cfg_key_g", "key", "G", "KEY_G");
			mod.cfg_key_k	= config.getString("cfg_key_k", "key", "K", "KEY_K");
			mod.cfg_key_h	= config.getString("cfg_key_h", "key", "H", "KEY_H");
			mod.cfg_key_f	= config.getString("cfg_key_f", "key", "F", "KEY_F");
			mod.cfg_key_b	= config.getString("cfg_key_b", "key", "B", "KEY_B");
			mod.cfg_key_v	= config.getString("cfg_key_v", "key", "V", "KEY_V");
			
			mod.cfg_key_tab	= config.getString("cfg_key_tab", "key", "TAB", "KEY_TAB");
		}
		
		
	}
}
