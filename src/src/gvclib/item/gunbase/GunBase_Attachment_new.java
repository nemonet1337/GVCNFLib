package gvclib.item.gunbase;

import java.io.IOException;
import java.io.InputStreamReader;

import gvclib.mod_GVCLib;
import gvclib.gui.GVCInventoryItem;
import gvclib.item.ItemAttachment;
import gvclib.item.ItemGunBase;
import gvclib.item.ItemGun_SWORD;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.resources.IResource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import objmodel.AdvancedModelLoader;

public class GunBase_Attachment_new {

	public static void load(ItemGunBase gun, ItemStack itemstack, World world, EntityPlayer entityplayer, int i, boolean flag) {

		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		
		//boolean recoiled = nbt.getBoolean("Recoiled");
		float model_x_ads = nbt.getFloat("model_x_ads");
		float model_y_ads = nbt.getFloat("model_y_ads");
		float model_z_ads = nbt.getFloat("model_z_ads");
		
		{
			nbt.setInteger("am_light_kazu", gun.am_light_kazu);
			for(int ii = 0; ii < gun.am_light_kazu; ++ii) {
				nbt.setFloat("am_colorlevel" + String.format("%1$3d", ii), gun.am_colorlevel[ii]);
			}
			//nbt.setBoolean("am_light", gun.render_default_light);
		}
		
		//reset
		/*if(!gun.am_sight){
			gun.am_sightbase_x = gun.model_x_ads;
			gun.am_sightbase_y = gun.model_y_ads;
			gun.am_sightbase_z = gun.model_z_ads;
			gun.am_sightbase_z1 = gun.am_sight_z;
		}
		if(!gun.am_bullet){
			gun.p_idbase = gun.p_id;
			gun.exlevelbase = gun.exlevel;
			gun.poworbase = gun.powor;
			gun.pelletbase = gun.shotgun_pellet;
		}
		if(gun.recoilbase == 0.0F){
			gun.recoilbase = gun.recoil;
			gun.burebase = gun.bure;
		}
		{
			gun.am_sight = false;
			gun.model_x_ads = gun.am_sightbase_x;
			gun.model_y_ads = gun.am_sightbase_y;
			gun.model_z_ads = gun.am_sightbase_z;
			gun.am_sight_z = gun.am_sightbase_z1;
			gun.p_id = gun.p_idbase;
			gun.exlevel = gun.exlevelbase;
			gun.powor = gun.poworbase;
			gun.shotgun_pellet = gun.pelletbase;
			
			if(!gun.render_default_light)gun.am_light = false;
			gun.am_supu = false;
			gun.am_grip = false;
			gun.recoil = gun.recoilbase;
			gun.bure = gun.burebase;
			
		}
		if(gun.soundbase == null) {
			gun.soundbase = gun.sound;
		}
		if(gun.firesbase == null && gun.fires != null) {
			gun.firesbase = gun.fires;
		}*/
		
		
		if(!flag)return;
		InventoryPlayer playerInv = entityplayer.inventory;
		GVCInventoryItem inventory = new GVCInventoryItem(playerInv, itemstack);
		//GVCInventoryItem inventory = new GVCInventoryItem(entityplayer);
		inventory.openInventory(entityplayer);
		for (int i1 = 0; i1 < inventory.getSizeInventory(); ++i1) {
			ItemStack itemstacki = inventory.getStackInSlot(i1);

			if (i1 == 1) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.id == 101){
						//gun.scopezoom = am.zoom;
						nbt.setFloat("scopezoom", am.zoom);
						if(gun.render_sight && am.objtrue) {
							if(gun.designated_kazu != 0) {
								if(gun.designated_mat4){
									mat4(itemstack, gun, am);
								}
								for(int ic = 0; ic < gun.designated_attachment_name.length; ++ic) {
									if(gun.designated_attachment_name[ic] != null && gun.designated_attachment_name[ic].equals(am.designated_attachment_name)) {
										mat4(itemstack, gun, am);
										break;
									}
								}
							}
							else if(am.designated_attachment_name == null){
								mat4(itemstack, gun, am);
							}
						}else {
							if(gun.render_mat4_unam){
								gun.scopezoom = gun.scopezoomred;
								gun.true_mat4 = true;
							}
						}
					}else{
						gun.am_sight = false;
						if(gun.designated_kazu != 0) {
							for(int ic = 0; ic < gun.designated_attachment_name.length; ++ic) {
								if(gun.designated_attachment_name[ic] != null && gun.designated_attachment_name[ic].equals(am.designated_attachment_name)) {
									if(am.id == 4){
										gun.scopezoom = gun.scopezoomred;
										gun.true_mat4 = true;
									}else if(am.id == 5){
										gun.scopezoom = gun.scopezoomscope;
										gun.true_mat4 = false;
										gun.true_mat5 = true;
									}else{
										gun.scopezoom = gun.scopezoombase;
										gun.true_mat4 = false;
										gun.true_mat5 = false;
									}
								}
							}
						}else {
							if(am.id == 4){
								gun.scopezoom = gun.scopezoomred;
								gun.true_mat4 = true;
							}else if(am.id == 5){
								gun.scopezoom = gun.scopezoomscope;
								gun.true_mat4 = false;
								gun.true_mat5 = true;
							}else{
								gun.scopezoom = gun.scopezoombase;
								gun.true_mat4 = false;
								gun.true_mat5 = false;
							}
						}
						
					}
					gun.sight_item = itemstacki;
				}else{
					nbt.setBoolean("am_sight", false);
					nbt.setFloat("scopezoom", gun.scopezoombase);
					gun.scopezoom = gun.scopezoombase;
					gun.true_mat4 = false;
					gun.true_mat5 = false;
					gun.sight_item = new ItemStack((Item)null);
				}
			}
			if (i1 == 2) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.id == 102){
						if(gun.render_light && am.objtrue) {
							if(gun.designated_kazu != 0) {
								if(gun.designated_mat6){
									mat6(itemstack, gun, am);
								}
								for(int ic = 0; ic < gun.designated_attachment_name.length; ++ic) {
									if(gun.designated_attachment_name[ic] != null && gun.designated_attachment_name[ic].equals(am.designated_attachment_name)) {
										mat6(itemstack, gun, am);
										 break;
									}
								}
							}else if(am.designated_attachment_name == null){
								mat6(itemstack, gun, am);
							}
						}else if(gun.render_mat6_unam){
							gun.true_mat6 = true;
							gun.true_mat7 = false;
						}
					}else {
						if(am.id == 6){
							gun.true_mat6 = true;
							gun.true_mat7 = false;
						}else if(am.id == 7){
							gun.true_mat6 = false;
							gun.true_mat7 = true;
						}
					}
					gun.light_item = itemstacki;
				}else{
					nbt.setBoolean("am_light", false);
					gun.true_mat6 = false;
					gun.true_mat7 = false;
					gun.light_item = new ItemStack((Item)null);
				}
			}
			if (i1 == 3) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.id == 8){
						gun.sound = gun.soundsu;
						gun.fires = gun.fires_su;
						gun.muzzle = false;
						gun.true_mat8 = true;
						if(am.objtrue && gun.render_muss) {
							if(gun.designated_kazu != 0) {
								if(gun.designated_mat8){
									mat8(itemstack, gun, am);
								}
								for(int ic = 0; ic < gun.designated_attachment_name.length; ++ic) {
									if(gun.designated_attachment_name[ic] != null && gun.designated_attachment_name[ic].equals(am.designated_attachment_name)) {
										mat8(itemstack, gun, am);
										break;
									}
								}
							}else {if(am.designated_attachment_name == null)
								mat8(itemstack, gun, am);
							}
						}else if(gun.render_mat8_unam){
							gun.am_supu = false;
							gun.sound = gun.soundsu;
							gun.fires = gun.fires_su;
							gun.muzzle = true;
							gun.true_mat8 = true;
						}
					}else{
						gun.sound = gun.soundbase;
						gun.fires = gun.firesbase;
						gun.muzzle = true;
						gun.true_mat8 = false;
					}
					gun.supu_item = itemstacki;
				}else{
					nbt.setBoolean("am_supu", false);
					gun.sound = gun.soundbase;
					gun.fires = gun.firesbase;
					gun.muzzle = true;
					gun.true_mat8 = false;
					gun.supu_item = new ItemStack((Item)null);
				}
			}
			
			if (i1 == 5) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.id == 50){
						for(int ic = 0; ic < gun.canuse_bullet.length; ++ic) {
							if(gun.canuse_bullet[ic] != null && gun.canuse_bullet[ic].equals(am.bullet_name)) {
								/*gun.am_bullet = true;
								gun.p_id = am.p_id;
								gun.p_level = am.p_level;
								gun.p_time = am.p_time;
								gun.exlevel = am.exlevel;
								gun.bure = gun.bure * am.bure;
								gun.powor = (int) (gun.powor * am.power);
								gun.bulletDameID = am.bulletid;
								if(am.pellet_priority)gun.shotgun_pellet = am.pellet;
								gun.bullet_item = itemstacki;*/
								
								if(!itemstack.hasTagCompound())return;
								
								boolean am_bullet = nbt.getBoolean("am_bullet");
								nbt.setBoolean("am_bullet", true);
								nbt.setInteger("p_id", am.p_id);
								nbt.setInteger("p_level", am.p_level);
								nbt.setInteger("p_time", am.p_time);
								
								nbt.setFloat("exlevel", am.exlevel);
								nbt.setFloat("bure_bullet", am.bure);
								nbt.setInteger("powor", (int) (gun.powor * am.power));
								nbt.setInteger("bulletDameID", am.bulletid);
								if(am.pellet_priority) {
									nbt.setInteger("shotgun_pellet", am.pellet);
								}else {
									nbt.setInteger("shotgun_pellet", 1);
								}
								
								break;
							}
						}
					}else
					if(am.id == 51){
						gun.am_bullet = true;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 1;
						}
					}else if(am.id == 52){
						gun.am_bullet = true;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 2;
						}
					}else if(am.id == 53){
						gun.am_bullet = true;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 3;
						}
					}else if(am.id == 54){
						gun.am_bullet = true;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 4;
						}
					}else if(am.id == 55){
						gun.am_bullet = true;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 5;
						}
					}else{
						gun.am_bullet = false;
						gun.bullet_item = itemstacki;
						if (!world.isRemote) {
							gun.bulletDameID = 0;
						}
					}
				}else{
					gun.am_bullet = false;
					nbt.setBoolean("am_bullet", false);
					gun.bullet_item = new ItemStack((Item)null);
					if (!world.isRemote) {
						gun.bulletDameID = 0;
					}
				}
			}
			if (i1 == 4) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					gun.true_mat10 = false; 
					gun.true_mat11 = false; 
					gun.true_mat12 = false; 
					gun.underbarrel = new ItemStack((Item)null);
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.id == 9){
						gun.true_mat9 = true;
						if(am.objtrue) {
							if(gun.designated_kazu != 0) {
								if(gun.designated_mat9){
									mat9(gun, am, itemstack, entityplayer);
								}
								for(int ic = 0; ic < gun.designated_attachment_name.length; ++ic) {
									if(gun.designated_attachment_name[ic] != null && gun.designated_attachment_name[ic].equals(am.designated_attachment_name)) {
										mat9(gun, am, itemstack, entityplayer);
										break;
									}
								}
							}else if(am.designated_attachment_name == null){
								mat9(gun, am, itemstack, entityplayer);
							}
						}
					}
				}else if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemGunBase) {
					ItemGunBase under = (ItemGunBase) itemstacki.getItem();
					if(gun.gun_can_use_underbarrel) {
						if(gun.gun_can_get_underbarrel && under.gun_can_set_underbarrel) {
							gun.true_mat9 = false;
							gun.true_mat10 = false;
							gun.true_mat11 = false; 
							gun.true_mat12 = false; 
							gun.true_mat100 = true;
							gun.underbarrel = itemstacki;
						}else {
							gun.true_mat9 = false;
							gun.true_mat12 = false; 
							if(under.gun_type== 0){
								gun.true_mat11 = true;
								gun.true_mat10 = false;
							}else if(under.gun_type== 3 || under.gun_type== 2){
								gun.true_mat10 = true;
								gun.true_mat11 = false;
							}
						}
					}
					
				}
				else if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemGun_SWORD) {
						gun.true_mat9 = false;
						gun.true_mat10 = false;
						gun.true_mat11 = false; 
						{
							gun.true_mat12 = true;
						}
				}else if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemSword) {
					gun.true_mat9 = false;
					gun.true_mat10 = false;
					gun.true_mat11 = false; 
					{
						gun.true_mat12 = true;
					}
				}else{
					nbt.setBoolean("am_grip", false);
					gun.true_mat9 = false; 
					gun.true_mat10 = false; 
					gun.true_mat11 = false; 
					gun.true_mat12 = false; 
					gun.true_mat100 = false; 
					gun.underbarrel = new ItemStack((Item)null);
					gun.grip_item = new ItemStack((Item)null);
				}

			}
		}
		//inventory.closeInventory(entityplayer);
	
	}
	
	public static void mat4(ItemStack itemstack, ItemGunBase gun, ItemAttachment am) {
		/*gun.rendering_light_am_sight = am.rendering_light;
		gun.am_sight = true;
		gun.am_model = am.obj_model;
		gun.am_tex = am.obj_tex;
		gun.am_zoomrender = am.zoomrender;
		gun.am_zoomrendertex = am.zoomrendertex;
		gun.am_ads_tex = am.ads_tex;
		gun.model_x_ads =  (gun.am_sight_x + am.x);
		gun.model_y_ads = - (gun.am_sight_y + am.y);
		//gun.model_z_ads = - (-gun.model_z_ads + gun.am_sight_z + am.z);
		gun.model_z_ads =  am.eye_relief + gun.am_sight_z;*/
		/* if(!itemstack.hasTagCompound())
	        {
			 itemstack.setTagCompound(new NBTTagCompound());
			 itemstack.getTagCompound().setTag("Items", new NBTTagList());
	        }else {
	        	NBTTagList nbttaglist = new NBTTagList();
	        	 ItemStack result = new ItemStack(itemstack.getItem(), 1);
	             result.setTagCompound(new NBTTagCompound());
	             result.getTagCompound().setTag("Items", nbttaglist);
	        }*/
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		float model_x_ads = nbt.getFloat("model_x_ads");
		float model_y_ads = nbt.getFloat("model_y_ads");
		float model_z_ads = nbt.getFloat("model_z_ads");
		
		boolean am_sight = nbt.getBoolean("am_sight");
		nbt.setBoolean("am_sight", true);
		
		nbt.setFloat("model_x_ads", (gun.am_sight_x + am.x));
		nbt.setFloat("model_y_ads", - (gun.am_sight_y + am.y));
		nbt.setFloat("model_z_ads", am.eye_relief + gun.am_sight_z);
		nbt.setString("am_model", am.obj_model_string);
		nbt.setString("am_tex", am.obj_tex);
		
		nbt.setBoolean("am_zoomrender", am.zoomrender);
		nbt.setBoolean("am_zoomrendertex", am.zoomrendertex);
		nbt.setString("am_ads_tex", am.ads_tex);
		
		/*boolean mo = false;
    	for(int ii = 0; ii < 1024; ++ii) {
    		if(mod_GVCLib.am_name[ii] != null && mod_GVCLib.am_name[ii].equals(am.obj_model_string)) {
    			//entity.model  = mod_GVCLib.am_id[ii];
    			mo = true;
    			break;
    		}
    	}
    	if(!mo) 
    	{
    		//try 
    		{
				if(am.obj_model_string != null) {
        			//entity.model = AdvancedModelLoader.loadModel(resource);
            		++mod_GVCLib.am_id;
            		mod_GVCLib.am_model[mod_GVCLib.am_id] = am.obj_model;
            		mod_GVCLib.am_tex[mod_GVCLib.am_id] = new ResourceLocation(am.obj_tex);
            		mod_GVCLib.am_name[mod_GVCLib.am_id] = am.obj_model_string;
            		System.out.println(String.format("GVC_attachment_model-" + mod_GVCLib.am_id));
        		}
			}
	}*/
		
    		/* catch (IOException e) {
				//e.printStackTrace();
				System.out.println(String.format("warning! not exist model!::::" + am.obj_tex));
			}*/
    		/*InputStreamReader packFile = new InputStreamReader(mod_GVCLib.class
		            .getResourceAsStream(am.obj_tex));*/
    		
    		/*ResourceLocation resource = new ResourceLocation(am.obj_tex);
    		try {
				IResource res = Minecraft.getMinecraft().getResourceManager().getResource(resource);
				if(res != null) {
        			//entity.model = AdvancedModelLoader.loadModel(resource);
            		++mod_GVCLib.am_id;
            		mod_GVCLib.am_model[mod_GVCLib.am_id] = am.obj_model;
            		mod_GVCLib.am_tex[mod_GVCLib.am_id] = new ResourceLocation(am.obj_tex);
            		mod_GVCLib.am_name[mod_GVCLib.am_id] = am.obj_tex;
            		System.out.println(String.format("GVC_attachment_model-" + mod_GVCLib.am_id));
        		}
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println(String.format("warning! not exist model!::::" + am.obj_tex));
			}
    		}*/
	}
	
	public static void mat6(ItemStack itemstack, ItemGunBase gun, ItemAttachment am) {
		/*gun.rendering_light_am_light = am.rendering_light;
		gun.am_light = true;
		gun.am_light_kazu = am.light_kazu;
		gun.am_light_model = am.obj_model;
		gun.am_light_tex = am.obj_tex;
		gun.rightposx =  (gun.am_light_x + am.x);
		gun.rightposy =  (gun.am_light_y +am.y);
		gun.rightposz =  (gun.am_light_z +am.z);
		//gun.roterightx = am_light_xr;
		//gun.roterighty = am_light_yr;
		//gun.roterightz = am_light_zr;
		
		 gun.am_colorlevel = am.colorlevel;*/
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		if(am.obj_model == null)return;
		
		boolean am_sight = nbt.getBoolean("am_light");
		nbt.setBoolean("am_light", true);
		
		nbt.setFloat("rightposx", (gun.am_light_x + am.x));
		nbt.setFloat("rightposy", (gun.am_light_y + am.y));
		nbt.setFloat("rightposz", (gun.am_light_z + am.z));
		
		nbt.setInteger("am_light_kazu", am.light_kazu);
		for(int i = 0; i < am.light_kazu; ++i) {
			nbt.setFloat("am_colorlevel" + String.format("%1$3d", i), am.colorlevel[i]);
		}
		nbt.setString("am_light_model", am.obj_model_string);
		nbt.setString("am_light_tex", am.obj_tex);
		
		/*boolean mo = false;
    	for(int ii = 0; ii < 1024; ++ii) {
    		if(mod_GVCLib.am_name[ii] != null && mod_GVCLib.am_name[ii].equals(am.obj_model_string)) {
    			mo = true;
    			break;
    		}
    	}
    	if(!mo) 
    	{
			if (am.obj_model_string != null) {
				// entity.model = AdvancedModelLoader.loadModel(resource);
				++mod_GVCLib.am_id;
				mod_GVCLib.am_model[mod_GVCLib.am_id] = am.obj_model;
				mod_GVCLib.am_tex[mod_GVCLib.am_id] = new ResourceLocation(am.obj_tex);
				mod_GVCLib.am_name[mod_GVCLib.am_id] = am.obj_model_string;
				System.out.println(String.format("GVC_attachment_model-" + mod_GVCLib.am_id));
			}
    	}*/
	}
	
	public static void mat8(ItemStack itemstack, ItemGunBase gun, ItemAttachment am) {
		/*gun.rendering_light_am_muzzle = am.rendering_light;
		gun.am_supu = true;
		gun.am_supu_model = am.obj_model;
		gun.am_supu_tex = am.obj_tex;
		gun.supuposx =  (gun.am_supu_x + am.x);
		gun.supuposy =  (gun.am_supu_y +am.y);
		gun.supuposz =  (gun.am_supu_z +am.z);*/
		
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		if(am.obj_model == null)return;
		
		boolean am_supu = nbt.getBoolean("am_supu");
		nbt.setBoolean("am_supu", true);
		
		nbt.setFloat("supuposx", (gun.am_supu_x + am.x));
		nbt.setFloat("supuposy", (gun.am_supu_y + am.y));
		nbt.setFloat("supuposz", (gun.am_supu_z + am.z));
		
		nbt.setString("am_supu_model", am.obj_model_string);
		nbt.setString("am_supu_tex", am.obj_tex);
		
		/*boolean mo = false;
    	for(int ii = 0; ii < 1024; ++ii) {
    		if(mod_GVCLib.am_name[ii] != null && mod_GVCLib.am_name[ii].equals(am.obj_model_string)) {
    			mo = true;
    			break;
    		}
    	}
    	if(!mo) 
    	{
    		if (am.obj_model_string != null) {
				// entity.model = AdvancedModelLoader.loadModel(resource);
				++mod_GVCLib.am_id;
				mod_GVCLib.am_model[mod_GVCLib.am_id] = am.obj_model;
				mod_GVCLib.am_tex[mod_GVCLib.am_id] = new ResourceLocation(am.obj_tex);
				mod_GVCLib.am_name[mod_GVCLib.am_id] = am.obj_model_string;
				System.out.println(String.format("GVC_attachment_model-" + mod_GVCLib.am_id));
			}
    	}*/
	}
	
	public static void mat9(ItemGunBase gun, ItemAttachment am, ItemStack itemstack, EntityPlayer entityplayer) {
		/*if(gun.render_grip) {
			gun.grip_item = itemstack;
		gun.rendering_light_am_grip = am.rendering_light;
		gun.am_grip = true;
		gun.am_grip_model = am.obj_model;
		gun.am_grip_tex = am.obj_tex;
		gun.gripposx =  (gun.am_grip_x + am.x);
		gun.gripposy =  (gun.am_grip_y +am.y);
		gun.gripposz =  (gun.am_grip_z +am.z);
		}
		if(entityplayer.isSneaking()) {
			gun.bure = gun.bure * am.bure_ads;
			gun.recoil = gun.recoil * am.recoil_ads;
		}else {
			gun.bure = gun.bure * am.bure;
			gun.recoil = gun.recoil * am.recoil;
		}*/
		
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(!itemstack.hasTagCompound())return;
		if(am.obj_model == null)return;
		
		boolean am_supu = nbt.getBoolean("am_grip");
		nbt.setBoolean("am_grip", true);
		
		nbt.setFloat("gripposx", (gun.am_grip_x + am.x));
		nbt.setFloat("gripposy", (gun.am_grip_y + am.y));
		nbt.setFloat("gripposz", (gun.am_grip_z + am.z));
		
		nbt.setBoolean("grip_gripping_point", am.grip_gripping_point);
		nbt.setFloat("grip_gripping_point_x", (am.grip_gripping_point_x));
		nbt.setFloat("grip_gripping_point_y", (am.grip_gripping_point_y));
		nbt.setFloat("grip_gripping_point_z", (am.grip_gripping_point_z));
		
		nbt.setString("am_grip_model", am.obj_model_string);
		nbt.setString("am_grip_tex", am.obj_tex);
		
		nbt.setFloat("bure", (gun.bure * am.bure));
		nbt.setFloat("bure_ads", (gun.bure * am.bure_ads));
		nbt.setDouble("recoil", (gun.recoil * am.recoil));
		nbt.setDouble("recoil_ads", (gun.recoil * am.recoil_ads));
		
		/*boolean mo = false;
    	for(int ii = 0; ii < 1024; ++ii) {
    		if(mod_GVCLib.am_name[ii] != null && mod_GVCLib.am_name[ii].equals(am.obj_model_string)) {
    			mo = true;
    			break;
    		}
    	}
    	if(!mo) 
    	{
    		if (am.obj_model_string != null) {
				// entity.model = AdvancedModelLoader.loadModel(resource);
				++mod_GVCLib.am_id;
				mod_GVCLib.am_model[mod_GVCLib.am_id] = am.obj_model;
				mod_GVCLib.am_tex[mod_GVCLib.am_id] = new ResourceLocation(am.obj_tex);
				mod_GVCLib.am_name[mod_GVCLib.am_id] = am.obj_model_string;
				System.out.println(String.format("GVC_attachment_model-" + mod_GVCLib.am_id));
			}
    	}*/
	}
}
