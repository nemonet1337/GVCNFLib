package work.nemonet.gvcnflib;


import java.io.File;

import org.lwjgl.input.Keyboard;

import java.awt.event.KeyEvent;

import gvclib.block.tile.TileEntityB_Fire;
import gvclib.entity.EntityB_BombArrow;
import gvclib.entity.EntityB_Bullet;
import gvclib.entity.EntityB_BulletAA;
import gvclib.entity.EntityB_BulletFire;
import gvclib.entity.EntityB_Cratridge;
import gvclib.entity.EntityB_Grapple;
import gvclib.entity.EntityB_GrenadeB;
import gvclib.entity.EntityB_Missile;
import gvclib.entity.EntityB_Shell;
import gvclib.entity.EntityP_Explosion;
import gvclib.entity.EntityP_Particle;
import gvclib.entity.EntityT_Flash;
import gvclib.entity.EntityT_Grenade;
import gvclib.entity.EntityT_GrenadeT;
import gvclib.entity.EntityT_Morter;
import gvclib.entity.EntityT_Smoke;
import gvclib.entity.EntityT_TNT;
import gvclib.entity.Entity_Flare;
import gvclib.entity.living.EntityGVC_Dummy;
import gvclib.entity.living.EntityGVC_PlayerDummy;
import gvclib.event.gun.LayerGunBase;
import gvclib.item.ItemGunBase;
import gvclib.render.ModelArmorNewObj;
import gvclib.render.RenderB_Base;
import gvclib.render.RenderB_BombArrow;
import gvclib.render.RenderB_BulletFire;
import gvclib.render.RenderB_Cratridge;
import gvclib.render.RenderB_Grapple;
import gvclib.render.RenderGVC_Dummy;
import gvclib.render.RenderGVC_PlayerDummy;
import gvclib.render.RenderP_Explosion;
import gvclib.render.RenderP_Particle;
import gvclib.render.RenderT_Flash;
import gvclib.render.RenderT_Grenade;
import gvclib.render.RenderT_GrenadeT;
import gvclib.render.RenderT_Morter;
import gvclib.render.RenderT_Smoke;
import gvclib.render.RenderT_TNT;
import gvclib.render.Render_Flare;
import gvclib.render.tile.TileRenderB_Fire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
public class ClientProxyGVClib extends CommonSideProxyGVClib {
    
	public static ModelBiped ArmorNewObj = new ModelArmorNewObj();
	
	@Override
    public boolean getClient() {
        return true;
    }
	
	@Override
	public File ProxyFile(){
		return Minecraft.getMinecraft().mcDataDir;
	}
	@Override
	public void dropItem(int i, Entity entity) {}
	
	@Override
    public EntityPlayer getEntityPlayerInstance() {
        return Minecraft.getMinecraft().player;
    }
	
	
	@Override
    public EntityPlayerMP getEntityPlayerMP() {
		if(Minecraft.getMinecraft().player != null) {
			//return Minecraft.getMinecraft().getIntegratedServer().getPlayerList().getPlayerByUUID(Minecraft.getMinecraft().player.getUniqueID());
			return (EntityPlayerMP) FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
		}else {
			return null;
		}
    }
	
    @Override
	public World getCilentWorld(){
		return FMLClientHandler.instance().getClient().world;
		}
    
    @Override
    public void registerClientInfo() {
        //ClientRegistry.registerKeyBinding(Speedreload);
    }
    
    @Override
	public int getFPS(){
		return Minecraft.getMinecraft().getDebugFPS();
		}
    
    @Override
	public void reisterRenderers(){
    	Minecraft mc = Minecraft.getMinecraft();
    	//RenderManager rendermanager = new RenderManager(mc.renderEngine, mc.getRenderItem());
    	RenderManager rendermanager = mc.getRenderManager();
    	EntityRenderer entityrenderer = mc.entityRenderer;
    	RenderItem renderitem = mc.getRenderItem();
    	
    	//List<String> particles = new ArrayList<String>();
		//particles.add(ParticleEx.EXPLOSION_TEXTURE);
    	
    	//RenderingRegistry.registerEntityRenderingHandler(EntityB_Gun.class, new RenderB_Gun(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_Shell.class, new RenderB_Base(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_Bullet.class, new RenderB_Base(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_Missile.class, new RenderB_Base(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_GrenadeB.class, new RenderB_Base(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_BulletAA.class, new RenderB_Base(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_TNT.class, new RenderT_TNT(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_Flash.class, new RenderT_Flash(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_Smoke.class, new RenderT_Smoke(rendermanager, renderitem));
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_Grenade.class, new RenderT_Grenade(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_GrenadeT.class, new RenderT_GrenadeT(rendermanager, renderitem));
    	RenderingRegistry.registerEntityRenderingHandler(EntityT_Morter.class, new RenderT_Morter(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_BombArrow.class, new RenderB_BombArrow(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_BulletFire.class, new RenderB_BulletFire(rendermanager, renderitem));
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_Cratridge.class, new RenderB_Cratridge(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(Entity_Flare.class, new Render_Flare(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityB_Grapple.class, new RenderB_Grapple(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityP_Particle.class, new RenderP_Particle(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityP_Explosion.class, new RenderP_Explosion(rendermanager));
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityGVC_PlayerDummy.class, new RenderGVC_PlayerDummy(rendermanager));
    	RenderingRegistry.registerEntityRenderingHandler(EntityGVC_Dummy.class, new RenderGVC_Dummy(rendermanager));
    	
    	/*for (Render render : Iterables.concat(rendermanager.getSkinMap().values(), rendermanager.entityRenderMap.values())) 
    	//for (Render render : Iterables.concat(rendermanager.entityRenderMap.values())) 
    	{
    	      if ((render instanceof RenderLivingBase))
    	      {
    	        RenderLivingBase renderbase = (RenderLivingBase)render;
    	        if ((renderbase.getMainModel() instanceof ModelBiped)) {
    	        	renderbase.addLayer(new LayerGunBase(renderbase));
    	        }
    	      }
    	}*/
    	//for(int e = 0; e < 2000; ++e)
    	/*{
    		Render<Entity> renderer =  (Render<Entity>) Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(EntityZombie.class);
    		
    		if (renderer instanceof RenderLivingBase) {
    			RenderLivingBase renderbase = (RenderLivingBase)renderer;
    	        if ((renderbase.getMainModel() instanceof ModelBiped)) {
    //	        	renderbase.addLayer(new LayerGunBase(renderbase));
    	        }
    		}
    	}/**/
    	for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			playerRender.addLayer(new LayerGunBase(playerRender));
		}
    	
        /*for (Render render : Minecraft.getMinecraft().getRenderManager().entityRenderMap.values()) {
          if ((render instanceof RenderLivingBase))
          {
            RenderLivingBase rle = (RenderLivingBase)render;
          //  if ((rle.getMainModel() instanceof ModelBiped))
            {
              rle.addLayer(new LayerGunBase(rle));
            }
          }
        }/**/
    }
    @Override
    public void registerTileEntity() {		
    	ClientRegistry.registerTileEntity(TileEntityB_Fire.class, "TileEntityB_Fire", new TileRenderB_Fire());
	}
    @Override
	public void reisterModel(){
    	for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			playerRender.addLayer(new LayerGunBase(playerRender));
		}
        for (Render render : Minecraft.getMinecraft().getRenderManager().entityRenderMap.values()) {
          if ((render instanceof RenderLivingBase))
          {
            RenderLivingBase rle = (RenderLivingBase)render;
            if ((rle.getMainModel() instanceof ModelBiped))
            {
              rle.addLayer(new LayerGunBase(rle));
            }
          }
        }/**/
    }
    
    @Override
    public int mcbow(){
		return Minecraft.getMinecraft().gameSettings.thirdPersonView;
	}
    
    @Override
    public boolean reload(){
		//return Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex("R"));
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_r));
		//return false;
	}
    @Override
    public boolean xclick(){
		//return Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_X);
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_x));
		//return false;
	}
    
    @Override
    public boolean keyc(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_C);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_c));
	}
    @Override
    public boolean keyx(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_X);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_x));
	}
    @Override
    public boolean keyg(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_G);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_g));
	}
    @Override
    public boolean keyl(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_K);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_k));
	}
    @Override
    public boolean keyz(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_Z);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_z));
	}
    @Override
    public boolean keyh(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_H);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_h));
	}
    @Override
    public boolean keyf(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_F);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_f));
	}
    @Override
    public boolean keyb(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_F);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_b));
	}
    @Override
    public boolean keyv(){
		//return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_F);
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_v));
	}
    @Override
    public boolean leftclick(){
    	return Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown();
		//return false;
	}
    @Override
    public boolean rightclick(){
    	return Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown();
		//return false;
	}
    @Override
    public boolean jumped(){
		//return Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
		return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		//return false;
	}
    
    @Override
    public void ExRender(World wl, Entity en, float i, boolean ex){
    	if(wl != null && en != null){
    		if (i >= 2.0F)
            {
    			if (i >= 4.0F)
                {
    				int a = 0;
    				a = (int) (6 + i);
    				for (int ii = 0; ii < a; ++ii){
    					int xx = wl.rand.nextInt(a);
    					int zz = wl.rand.nextInt(a);
    					int yy = wl.rand.nextInt(a);
    					wl.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, en.posX - (a/2) + xx, en.posY - (a/2) + yy, en.posZ - (a/2) + zz, 1.0D, 0.0D, 0.0D, new int[0]);
    				}
    				wl.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, en.posX, en.posY, en.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
                }else{
                	wl.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, en.posX, en.posY, en.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            else
            {
            	wl.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, en.posX, en.posY, en.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
            }
    	}
	}
    @Override
    public void ExRender(){
	}
    
    
    @Override
    public boolean left(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		//return false;
	}
    @Override
    public boolean right(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		//return false;
	}
    @Override
    public boolean up(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_UP);
		//return false;
	}
    @Override
    public boolean down(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		//return false;
	}
    @Override//PageUp
    public boolean nine(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_PRIOR);
	}
    @Override//PageDown
    public boolean three(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NEXT);
	}
    @Override//PageDown
    public boolean LBRACKET(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_LBRACKET);
	}
    @Override//PageDown
    public boolean RBRACKET(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_RBRACKET);
	}
    @Override//@
    public boolean setting(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_GRAVE);
	}
    
    /*@Override//@
    public boolean setting(){
    	return KeyEvent.KEY_RELEASED == Keyboard.KEY_GRAVE;
	}*/
    
    
    @Override
    public boolean numpad_1(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1);
	}
    @Override
    public boolean numpad_2(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2);
	}
    @Override
    public boolean numpad_3(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3);
	}
    @Override
    public boolean numpad_4(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4);
	}
    @Override
    public boolean numpad_5(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5);
	}
    @Override
    public boolean numpad_6(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6);
	}
    @Override
    public boolean numpad_7(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7);
	}
    @Override
    public boolean numpad_8(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8);
	}
    @Override
    public boolean numpad_9(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9);
	}
    @Override
    public boolean numpad_0(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0);
	}
    
    @Override
    public boolean tab(){
    	return Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.getKeyIndex(mod_GVCLib.cfg_key_tab));
	}
}