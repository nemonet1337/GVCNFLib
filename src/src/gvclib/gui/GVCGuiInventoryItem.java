package gvclib.gui;
 
import org.lwjgl.opengl.GL11;

import gvclib.event.gun.Layermat;
import gvclib.item.ItemGunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
 
public class GVCGuiInventoryItem extends GuiContainer
{
    //private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
    private static final ResourceLocation texture = new ResourceLocation("gvclib:textures/gui/ar.png");
    private static EntityPlayer player;
    /** The mouse x-position recorded during the last rendered frame. */
    private float mousePosx;
    /** The mouse y-position recorded during the last renderered frame. */
    private float mousePosY;
 
    public GVCGuiInventoryItem(InventoryPlayer inventoryPlayer, ItemStack itemstack)
    {
        super(new GVCContainerInventoryItem(inventoryPlayer, itemstack));
        this.ySize = 222;
        player = inventoryPlayer.player;
    }

    /*public void initGui() {
    	super.initGui();
    	int i = width  - xSize >> 1;
		int j = height - ySize >> 1;
        this.buttonList.add(new GuiButton(0, i + 175, j + 0, 50, 20 , "Parameters"));
		//this.buttonList.add(new GuiButton(0, i + 175, j + 0, 50, 20 , "battlemachine:bm.parameters"));
        //this.buttonList.add(new GuiButton(1, i + 210, j + 10, 30, 20 , "Button 1"));
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
    	if (button.id == 0) {
    		//GVCLPacketHandler.INSTANCE2.sendToServer(new GVCLMessageKeyPressed(1110));
    		//mod_GVCLib.proxy.dropItem(0, player);
    		/*if(player != null && !player.world.isRemote) {
    			player.entityDropItem(new ItemStack(Items.APPLE), 1);
        	}//
    	}
    }*/
    
	/*
        ChestとかInventoryとか文字を描画する
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int p_146979_2_)
    {
    	
        //描画する文字, X, Y, 色
        this.fontRenderer.drawString("Attachment", 8, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }
 
    /*
        背景の描画
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        GL11.glPushMatrix();
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if(this.player != null){
			ItemStack itemstack = player.getHeldItemMainhand();
			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				if(!gun.render_sight) {
					if(gun.canuse_sight && gun.designated_kazu > 0) {
						this.drawTexturedModalRect(k + 98, l + 36, 192, 0, 16, 16);
					}else if(gun.canuse_sight) {
						this.drawTexturedModalRect(k + 98, l + 36, 208, 0, 16, 16);
					}else {
						this.drawTexturedModalRect(k + 98, l + 36, 176, 0, 16, 16);
					}
				}
				if(!gun.render_light) {
					if(gun.canuse_light && gun.designated_kazu > 0) {
						this.drawTexturedModalRect(k + 44, l + 36, 192, 0, 16, 16);
					}else if(gun.canuse_light) {
						this.drawTexturedModalRect(k + 44, l + 36, 208, 0, 16, 16);
					}else {
						this.drawTexturedModalRect(k + 44, l + 36, 176, 0, 16, 16);
					}
				}
				if(!gun.render_muss) {
					if(gun.canuse_muss && gun.designated_kazu > 0) {
						this.drawTexturedModalRect(k + 8, l + 90, 192, 0, 16, 16);
					}else if(gun.canuse_muss) {
						this.drawTexturedModalRect(k + 8, l + 90, 208, 0, 16, 16);
					}else {
						this.drawTexturedModalRect(k + 8, l + 90, 176, 0, 16, 16);
					}
				}
				if(!gun.render_grip) {
					if(gun.canuse_grip && gun.designated_kazu > 0) {
						this.drawTexturedModalRect(k + 44, l + 108, 192, 0, 16, 16);
					}else if(gun.canuse_grip) {
						this.drawTexturedModalRect(k + 44, l + 108, 208, 0, 16, 16);
					}else {
						this.drawTexturedModalRect(k + 44, l + 108, 176, 0, 16, 16);
					}
				}
					
				//if(!gun.rightmode)this.drawTexturedModalRect(k + 98, l + 36, 176, 0, 15, 15);
			}
		}
		GL11.glPopMatrix();
        
        GL11.glPushMatrix();
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if(this.player != null){
			ItemStack itemstack = player.getHeldItemMainhand();
			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				//GL11.glScalef(-1F, 1F, 1F);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GlStateManager.translate(scaledresolution.getScaledWidth()/2 + 20, scaledresolution.getScaledHeight()/2 - 20, 5);
				GL11.glScalef(15F, 15F, 15F);
				//GlStateManager.translate(15, 7, 5);
				GlStateManager.rotate(-180, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
				//GlStateManager.rotate(this.mousePosx, 0.0F, 1.0F, 0.0F);
				//System.out.println(String.format("getScaledWidth-" + scaledresolution.getScaledWidth()/2));//213
				//System.out.println(String.format("getScaledHeight-" + scaledresolution.getScaledHeight()/2));//120
			gun.obj_model.renderPart("mat1");
			gun.obj_model.renderPart("mat100");
			gun.obj_model.renderPart("mat2");
			gun.obj_model.renderPart("mat3");
			Layermat.rendersight( player,  itemstack,  gun);
			//this.rendersight(player, itemstack, gun);
			Layermat.renderattachment( player,  itemstack,  gun);
			gun.obj_model.renderPart("mat25");
			gun.obj_model.renderPart("mat31");
			gun.obj_model.renderPart("mat32");/**/
		}
		}
		GL11.glPopMatrix();
    }
    
    /*public void rendersight(EntityPlayer player, ItemStack stack, ItemGunBase gun) {
    	InventoryPlayer playerInv = player.inventory;
		GVCInventoryItem inventory = new GVCInventoryItem(playerInv, stack);
		inventory.openInventory();
		for (int i1 = 0; i1 < inventory.getSizeInventory(); ++i1) {
			ItemStack itemstacki = inventory.getStackInSlot(i1);

			if (i1 == 1) {
				if (!itemstacki.isEmpty() && itemstacki.getItem() instanceof ItemAttachment) {
					ItemAttachment am = (ItemAttachment) itemstacki.getItem();
					if(am.objtrue && am.id == 101){
						gun.am_sight = true;
						gun.scopezoom = am.zoom;
						gun.am_model = am.obj_model;
						gun.am_tex = am.obj_tex;
						gun.am_zoomrender = am.zoomrender;
						gun.am_ads_tex = am.ads_tex;
						gun.model_x_ads =  (gun.am_sight_x + am.x);
						gun.model_y_ads = - (gun.am_sight_y + am.y);
						gun.model_z_ads = - (-gun.model_z_ads + gun.am_sight_z + am.z);
						GL11.glPushMatrix();//glstart11
						GL11.glTranslatef(gun.am_sight_x, gun.am_sight_y, gun.am_sight_z);
						Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.am_tex));
						gun.am_model.renderPart("sight");
						GL11.glTranslatef( - gun.am_sight_x,  - gun.am_sight_y,  - gun.am_sight_z);
						Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
						GL11.glPopMatrix();//glend11
						gun.obj_model.renderPart("mat41");
					}
				}
			}
		}
    }*/
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.mousePosx = (float)mouseX;
        this.mousePosY = (float)mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
        //this.renderHoveredToolTip(mouseX, mouseY);
        this.renderHoveredToolTip(mouseX, mouseY);
        //player.inventoryContainer.renderTooltip(this.guiLeft, this.guiTop, mouseX, mouseY);
    }
}