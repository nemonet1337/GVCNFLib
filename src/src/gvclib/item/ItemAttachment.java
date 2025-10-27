package gvclib.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import objmodel.IModelCustom;

public class ItemAttachment extends Item
{
	public int id = 0;
	public String obj_model_string = "gvclib:textures/model/ar.png";
	public IModelCustom obj_model = null;//AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/model/ar.obj"));
    public String obj_tex = "gvclib:textures/model/ar.png";
    public boolean objtrue = false;
    public boolean zoomrender = true;
    public boolean zoomrendertex = false;
    public String ads_tex = "gvclib:textures/misc/scope.png";
    public float zoom = 1.0F;
	public float x = 0;
	public float y = 0;
	public float z = 0;
	
	public float eye_relief = - 2.0F;
	
	public float xr = 0;
	public float yr = 0;
	public float zr = 0;
	public float[] colorlevel = new float[1024];
	public int light_kazu = 1;
	
	public double recoil = 0.8F;
	public double recoil_ads = 0.6F;
	public float bure = 0.8F;
	public float bure_ads = 0.4F;
	
	public int p_id = 0;
	public int p_level = 0;
	public int p_time = 0;
	
	public float exlevel = 0F;
	public float power = 1F;
	public int bulletid = 0;
	/**使用用途不*/
	public boolean notpelletbase = false;
	
	/**true時pellet数が適応される
	 * false時1発のみ*/
	public boolean pellet_priority = true;
	public int pellet = 1;
	
	//19/1/14
	public boolean rendering_light = true;
	
	//19/2/16
	public boolean grip_gripping_point = false;
	public float grip_gripping_point_x = 0;
	public float grip_gripping_point_y = 0;
	public float grip_gripping_point_z = 0;
	
	public String bullet_name;
	
	public String designated_attachment_name = null;
	
	
	public String information1 = null;
	public String information2 = null;
	public String information3 = null;
	
	public TextFormatting information1_color = TextFormatting.WHITE;
	public TextFormatting information2_color = TextFormatting.WHITE;
	public TextFormatting information3_color = TextFormatting.WHITE;
	
    public ItemAttachment()
    {
        this.maxStackSize = 1;
    }
    
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	  {
    	tooltip.add(TextFormatting.BLUE + "Enter the GUI with X key while holding the gun ");
    	String am = "";
    	if(information1 != null) {
			TextComponentTranslation information = new TextComponentTranslation(information1, new Object[0]);
			tooltip.add(information1_color + information.getFormattedText());
		}
		if(information2 != null) {
			TextComponentTranslation information = new TextComponentTranslation(information2, new Object[0]);
			tooltip.add(information2_color + information.getFormattedText());
		}
		if(information3 != null) {
			TextComponentTranslation information = new TextComponentTranslation(information3, new Object[0]);
			tooltip.add(information3_color + information.getFormattedText());
		}
		{
			if(designated_attachment_name != null) {
				tooltip.add(TextFormatting.YELLOW + "AttachmentGroup" + " : " + designated_attachment_name);
			}
		}
    	if(id == 101 || id == 4 || id == 5) {
    		am = "Sight";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    		tooltip.add(TextFormatting.GREEN + "Zoom " + " : x" + I18n.translateToLocal(String.valueOf(this.zoom)));
    	}
    	if(id == 102 || id == 6 || id == 7) {
    		am = "Light";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	if(id == 8) {
    		am = "Muzzle";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	if(id == 9) {
    		am = "UnderBurrel";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    		tooltip.add(TextFormatting.GREEN + "Recoil " + " : " + I18n.translateToLocal(String.valueOf(this.recoil)));
    		tooltip.add(TextFormatting.GREEN + "Recoil_ADS " + " : " + I18n.translateToLocal(String.valueOf(this.recoil_ads)));
    		tooltip.add(TextFormatting.GREEN + "BlletSpread " + " : " + I18n.translateToLocal(String.valueOf(this.bure)));
    		tooltip.add(TextFormatting.GREEN + "BlletSpread_ADS " + " : " + I18n.translateToLocal(String.valueOf(this.bure_ads)));
    	}
    	if(id == 50) {
    		am = "Bullet";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    		tooltip.add(TextFormatting.YELLOW + "Bullet " + " : " + I18n.translateToLocal(bullet_name));
    		tooltip.add(TextFormatting.GREEN + "Power " + " : x" + I18n.translateToLocal(String.format("%1$.2f ms", this.power)));
    		tooltip.add(TextFormatting.GREEN + "Pellet " + " : " + I18n.translateToLocal(String.valueOf(this.pellet)));
    		if(exlevel >= 1) {
    			tooltip.add(TextFormatting.GREEN + "ExprotionLevel " + " : " + I18n.translateToLocal(String.valueOf(this.exlevel)));
    		}
    		if(bulletid == 1) {
    			tooltip.add(TextFormatting.YELLOW + "AP");
    		}
    		if(p_id != 0) {
    			tooltip.add(TextFormatting.GREEN + "PotionID " + " :"  + I18n.translateToLocal(String.valueOf(this.p_id)));
        		tooltip.add(TextFormatting.GREEN + "PotionLevel " + " : " + I18n.translateToLocal(String.valueOf(this.p_level)));
        		tooltip.add(TextFormatting.GREEN + "PotionTime " + " : " + I18n.translateToLocal(String.valueOf(this.p_time)));
    		}
    		
    	}
    	if(id == 51) {
    		am = "APBullet";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	if(id == 52) {
    		am = "PotionBullet";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	if(id == 53) {
    		am = "PotionBullet";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	if(id == 54) {
    		am = "ExprotionBullet";
    		tooltip.add(TextFormatting.YELLOW + "Attachment " + " : " + I18n.translateToLocal(am));
    	}
    	//tooltip.add(TextFormatting.WHITE + "Attachment " + ":" + I18n.translateToLocal(am));
		//String powor = String.valueOf(this.powor);
		//tooltip.add(TextFormatting.WHITE + "FireDamege " + "+" + I18n.translateToLocal(powor));
	  }
}