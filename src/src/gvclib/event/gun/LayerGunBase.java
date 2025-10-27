package gvclib.event.gun;

import org.lwjgl.opengl.GL11;

import gvclib.mod_GVCLib;
import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.item.ItemGunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.AdvancedModelLoader;
import objmodel.IModelCustom;

@SideOnly(Side.CLIENT)
public class LayerGunBase extends LayerHeldItem//implements LayerRenderer<EntityLivingBase>
{
    protected final RenderLivingBase<?> livingEntityRenderer;

    private static final ResourceLocation rightt = new ResourceLocation("gvclib:textures/model/Right.png");
	private static final IModelCustom rightm = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/model/Right.mqo"));
	private static final ResourceLocation lasert = new ResourceLocation("gvclib:textures/model/RightLaser.png");
	private static final IModelCustom laserm = AdvancedModelLoader.loadModel(new ResourceLocation("gvclib:textures/model/RightLaser.mqo"));
    
    public LayerGunBase(RenderLivingBase<?> livingEntityRendererIn)
    {
    	super(livingEntityRendererIn);
        this.livingEntityRenderer = livingEntityRendererIn;
        //GL11.glScalef(5F, 5F, 5F);
    }

    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	//GL11.glScalef(5F, 5F, 5F);
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (this.livingEntityRenderer.getMainModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translate(0.0F, 0.625F, 0.0F);
                GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
            }
            Minecraft minecraft = Minecraft.getMinecraft();
            EntityPlayer player = minecraft.player;
            
            double d0 = entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * (double)partialTicks;
            double d1 = entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * (double)partialTicks;
            double d2 = entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * (double)partialTicks;
            
            if (isInRangeToRender3d(player, d0, d1, d2) && mod_GVCLib.cfg_gun_renderrange_limiter)
            {
            	 this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
                 this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            }
           
            GlStateManager.popMatrix();
            
        }
    }

    
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRender3d(EntityLivingBase entitylivingbaseIn,double x, double y, double z)
    {
        double d0 = entitylivingbaseIn.posX - x;
        double d1 = entitylivingbaseIn.posY - y;
        double d2 = entitylivingbaseIn.posZ - z;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        return isInRangeToRenderDist(entitylivingbaseIn, d3);
    }

    /**
     * Checks if the entity is in range to render.
     */
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(EntityLivingBase entitylivingbaseIn,double distance)
    {
        double d0 = entitylivingbaseIn.getEntityBoundingBox().getAverageEdgeLength();

        if (Double.isNaN(d0))
        {
            d0 = 1.0D;
        }

        d0 = d0 * 64.0D * 1;
        return distance < d0 * d0;
    }
    
    //public static int gllist =  GLAllocation.generateDisplayLists(1);
    
    private void renderHeldItem(EntityLivingBase p_188358_1_, ItemStack itemstack, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide handSide)
    {
        if (!itemstack.isEmpty())
        {
            GlStateManager.pushMatrix();
            if (p_188358_1_.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            if (p_188358_1_ instanceof EntityGVCLivingBase && p_188358_1_ != null) {
             	EntityGVCLivingBase en = (EntityGVCLivingBase) p_188358_1_;
             	/*if (en.getSneak())
                {
                    GlStateManager.translate(0.0F, 0.2F, 0.0F);
                }*/
            }
            // Forge: moved this call down, fixes incorrect offset while sneaking.
            ModelBiped model = (ModelBiped)this.livingEntityRenderer.getMainModel();
            model.bipedLeftArm.isHidden = false;
            model.bipedRightLeg.isHidden = false;
            ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, handSide);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            
            //GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(0.1875F, 0.1875F, 0.1875F);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.translate((float)((flag ? -1 : 1) / 16.0F) * -5.33F, 0.125F * 1.33F, -0.625F * -4.5F);//-5.33,-3.33,-4.5
            //GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
            //Minecraft.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
            if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
				ItemGunBase gun = (ItemGunBase) itemstack.getItem();
				gun.ModelLoad();
				boolean isreload = false;
				if (p_188358_1_ instanceof EntityGVCLivingBase && p_188358_1_ != null) {
	             	EntityGVCLivingBase en = (EntityGVCLivingBase) p_188358_1_;
	             	if (!en.getattacktask())
	                {
	             		if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
	             			//ItemGunBase gun = (ItemGunBase) itemstack.getItem();
	             			if(gun != null && gun.arm_l_posz > -1.0F) {
	             				GlStateManager.rotate(80.0F, 0.0F, 1.0F, 0.0F);
	             				GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
	             			}
	             		}
	                }
	             	if(en.getRemain_L() <= 0){
	             		isreload = true;
	             	}
	            }
				{
					//GL11.glNewList(gllist, GL11.GL_COMPILE);
					Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
					gun.obj_model.renderPart("mat1");
					gun.obj_model.renderPart("mat100");
					gun.obj_model.renderPart("mat2");
					if(!isreload)gun.obj_model.renderPart("mat3");
					Layermat.rendersight( p_188358_1_,  itemstack,  gun);
					Layermat.renderattachment( p_188358_1_,  itemstack,  gun);
					gun.obj_model.renderPart("mat25");
					gun.obj_model.renderPart("mat31");
					gun.obj_model.renderPart("mat32");/**/
					//GL11.glEndList();
				}
				//GL11.glCallList(gllist);
				
			//GVCEventsGunRender.rendermat(p_188358_1_, itemstack, gun);
			/*GL11.glPushMatrix();//right
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if(gun.rightmode && gun.true_mat7) {
				GL11.glColor4f(1F, 1F, 1F, 0.2F);
				GL11.glTranslatef(gun.rightposx, gun.rightposy, gun.rightposz);
				Minecraft.getMinecraft().renderEngine.bindTexture(rightt);
				rightm.renderAll();
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}
			if(gun.rightmode && gun.true_mat6) {
				GL11.glColor4f(1F, 1F, 1F, 0.5F);
				GL11.glTranslatef(gun.rightposx, gun.rightposy, gun.rightposz);
				Minecraft.getMinecraft().renderEngine.bindTexture(lasert);
				laserm.renderAll();
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}
			GL11.glPopMatrix();//right*/
            }
            GlStateManager.popMatrix();
        }
    }

    
    
    public boolean shouldCombineTextures()
    {
        return false;
    }
}