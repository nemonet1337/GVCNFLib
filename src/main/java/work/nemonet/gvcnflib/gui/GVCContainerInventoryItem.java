package gvclib.gui;
 

import gvclib.item.ItemGunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
 
public class GVCContainerInventoryItem extends Container
{
    private GVCInventoryItem inventory;
	//private final IInventory inventory;
 
    private IInventory holderInventory;
    
    public GVCContainerInventoryItem(InventoryPlayer inventoryPlayer, ItemStack itemstack)
    {
    	inventory = new GVCInventoryItem(inventoryPlayer, itemstack);
    	//inventory = inventoryPlayer;
    	//inventory = new GVCInventoryItem(inventoryPlayer.player);
        inventory.openInventory(inventoryPlayer.player);
        if(itemstack.getItem() instanceof  ItemGunBase){
        	ItemGunBase gun = (ItemGunBase) itemstack.getItem();
            {
                //this.addSlotToContainer(new Slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            	if(gun.canuse_sight)this.addSlotToContainer(new SlotGun(inventory, 1, 8 + 5 * 18, 18 + 1 * 18, itemstack));
            	if(gun.canuse_light)this.addSlotToContainer(new SlotGun(inventory, 2, 8 + 2 * 18, 18 + 1 * 18, itemstack));
            	if(gun.canuse_muss)this.addSlotToContainer(new SlotGun(inventory, 3, 8 + 0 * 18, 18 + 4 * 18, itemstack));
            	if(gun.canuse_grip)this.addSlotToContainer(new SlotGun(inventory, 4, 8 + 2 * 18, 18 + 5 * 18, itemstack));
            	
            	this.addSlotToContainer(new SlotGun(inventory, 5, 8 + 7 * 18, 18 + 5 * 18, itemstack));
            }
        }
 
        int i = 2 * 18 + 1;
        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new GVCSlotInventoryItem(inventoryPlayer, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
 
        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new GVCSlotInventoryItem(inventoryPlayer, j, 8 + j * 18, 161 + i));
        }

        //System.out.println(String.format("open"));
    }
 

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.inventory.isUsableByPlayer(playerIn);
    }
 
    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
    	 ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);
 
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
 
            if (p_82846_2_ < this.inventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                	return ItemStack.EMPTY;
                }
            }
            //シフトクリック時に、このアイテムだったら動かさない。
            else if(slot.getStack() != ItemStack.EMPTY && slot.getStack().getItem()instanceof ItemGunBase)
            {
            	return ItemStack.EMPTY;
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false))
            {
            	return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
 
        return itemstack;
    }
 
    /*
        Containerを閉じるときに呼ばれる
     */
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);
        this.inventory.closeInventory(p_75134_1_);
    }
    
    /**
     * Return this chest container's lower chest inventory.
     */
    public IInventory getLowerChestInventory()
    {
        return this.inventory;
    }
}