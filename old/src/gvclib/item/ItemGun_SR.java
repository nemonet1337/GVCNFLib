package gvclib.item;

import gvclib.network.GVCLClientMessageKeyPressed;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;


	public class ItemGun_SR extends ItemGunBase {
		public static String ads;
		
		public ItemGun_SR() {
			super();
			this.maxStackSize = 1;
		}
		
		public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
	    {
			EntityPlayer entityplayer = (EntityPlayer)entity;
			int s;
			int li = getMaxDamage() - itemstack.getItemDamage();
			boolean lflag = cycleBolt(itemstack);
			boolean var5 = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0;
			Item item = itemstack.getItem();
			ItemStack item_m = entityplayer.getHeldItemMainhand();
			ItemStack item_o = entityplayer.getHeldItemOffhand();
			NBTTagCompound nbt = itemstack.getTagCompound();
			
			if(itemstack.hasTagCompound()) {
		//		this.gunbase_recoil(itemstack, world, entity, i, flag, 1);
				if(!entityplayer.isHandActive())
					this.gunbase_cocking(itemstack, world, entity, i, flag);
				this.gunbase_reload(itemstack, world, entity, i, flag);
				this.gunbase_lockon(itemstack, world, entity, i, flag);
			}
			
			
			boolean gethand = false;
			if(itemstack == entityplayer.getHeldItemOffhand()) {
				gethand = true;
			}else if(flag){
				gethand = true;
			}
			if (gethand) {
				entityplayer.motionX = entityplayer.motionX * this.motion;
				//entityplayer.motionY = entityplayer.motionY * 0.1;
				entityplayer.motionZ = entityplayer.motionZ * this.motion;
			}
			
			if (!itemstack.isEmpty() && entityplayer != null) {
				if(itemstack.getItem() instanceof ItemGunBase) {
					ItemGunBase gun = (ItemGunBase) itemstack.getItem();
					this.Attachment(gun, itemstack, world, entityplayer, i, flag);
				}
			}
			/*{
				if(!itemstack.isEmpty())
	            {
					if(flag && entityplayer.getActiveItemStack() == itemstack) {
						nbt.setBoolean("RightClick", true);
					}else {
						nbt.setBoolean("RightClick", false);
					}
	            	 
	            }
			}*/
			this.Fire_SR(itemstack, world, entityplayer, li, flag);
			
			super.onUpdate(itemstack, world, entity, i, flag);
	    }
		
		@Override
		public byte getCycleCountrecoilre(ItemStack pItemstack) {
			return 3;
		}
		
		
		public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityLivingBase par3EntityPlayer, int par4){
			/*if (par3EntityPlayer instanceof EntityPlayer)
	        {
				EntityPlayer entityplayer = (EntityPlayer)par3EntityPlayer;
				int s;
				int li = getMaxDamage() - par1ItemStack.getItemDamage();
				boolean lflag = cycleBolt(par1ItemStack);
				boolean var5 = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, par1ItemStack) > 0;
				Item item = par1ItemStack.getItem();
				NBTTagCompound nbt = par1ItemStack.getTagCompound();
				boolean cocking = nbt.getBoolean("Cocking");;
				
				if (par1ItemStack == entityplayer.getHeldItemMainhand())
		        {
		         if(par1ItemStack.getItemDamage() == this.getMaxDamage())
				 {
				 }
		        	else	 if(cocking)
				 {
		        		//recoilreBolt(par1ItemStack);
						//resetBolt(par1ItemStack);
		        		nbt.setBoolean("Recoiled", false);
						nbt.setBoolean("Cocking", false);
						
							FireBullet(par1ItemStack,par2World,(EntityPlayer) par3EntityPlayer);
							//ItemGunBase.updateCheckinghSlot(entityplayer, par1ItemStack);
							entityplayer.addStat(StatList.getObjectUseStats(this));
							
				  }
				}
	        }
        	*/
			
        }
		
		

}
