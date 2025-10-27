package gvclib;



import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gvclib.block.BlockB_Fire;
import gvclib.block.tile.TileEntityInvasion;
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
import gvclib.event.GVCEventDamege;
import gvclib.event.GVCEventLiving;
import gvclib.event.GVCEventLivingArmor;
import gvclib.event.GVCEventOverlay;
import gvclib.event.GVCEventRenderArmor;
import gvclib.event.GVCEventsLockOn;
import gvclib.event.GVCSoundEvent;
import gvclib.event.client.GVCEventRenderArmor_new;
import gvclib.event.client.GVCEventsClientWorld;
import gvclib.event.gun.GVCEventsGunRender_First;
import gvclib.event.gun.GVCEventsGunRender_Setting;
import gvclib.event.gun.GVCEventsGunRender_Third;
import gvclib.event.gun.GVCEventsGunZoom;
import gvclib.event.gun.GVCGunEvents;
import gvclib.event.gun.GVCReloadEvents;
import gvclib.event.test.GVCEvents_TEST_CN;
import gvclib.event.vehicle.GVCEvent_MAV;
import gvclib.event.vehicle.GVCEventsRiddingVehicle_Update;
import gvclib.event.vehicle.client.GVCEventsLockOn_client;
import gvclib.event.vehicle.client.GVCEventsRiddingVehicle;
import gvclib.event.vehicle.client.GVCEventsRiddingZoom;
import gvclib.gui.GVCGuiHandler;
import gvclib.network.GVCLPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import objmodel.IModelCustom;


@Mod(
		modid	= mod_GVCLib.MOD_ID,
		name	= mod_GVCLib.MOD_ID,
		version	= "1.12.2", 
		acceptedMinecraftVersions="[1.12.2]", 
		useMetadata=false,
		guiFactory = "gvclib.gui.config.GVCGuiFactory"
		)
@EventBusSubscriber
public class mod_GVCLib {
	@SidedProxy(clientSide = "gvclib.ClientProxyGVClib", serverSide = "gvclib.CommonSideProxyGVClib")
	public static CommonSideProxyGVClib proxy;
	public static final String MOD_ID = "gvclib";
	@Mod.Instance(MOD_ID)
	 
    public static mod_GVCLib INSTANCE;
	public static Item flash;
	
	public static Item smoke;
	
	public static Item e_grenade;
	public static Item e_grenadet;
	public static Item e_fire;

	public static Block b_fire;
	
	public static final Logger logger = LogManager.getLogger(MOD_ID);
	public static GVCConfig CONFIG = new GVCConfig();
	public static Configuration config;
	
	public static boolean cfg_optifine;
	public static double cfg_optifiney;
	public static double cfg_optifineys;
	
	public static boolean cfg_debag = true;
	
	public static boolean cfg_debag_weather = false;
	
	public static boolean cfg_debag_gun_mugen = false;
	
	public static boolean cfg_bullet_smoke;
	public static boolean cfg_Instant_death_avoidance;
	public static int cfg_bullet_living;
	
	public static boolean cfg_turret_lockpoint;
	
	public static double cfg_entity_render_range;
	
	public static double cfg_explotion_drop;
	
	public static boolean cfg_explotion_breakdirt;
	
	public static boolean cfg_mobdismount_insave;
	
	public static boolean cfg_vehicle_death_fire;
	
	public static String cfg_key_x;
	public static String cfg_key_r;
	public static String cfg_key_c;
	public static String cfg_key_z;
	public static String cfg_key_g;
	public static String cfg_key_k;
	public static String cfg_key_h;
	public static String cfg_key_f;
	public static String cfg_key_b;
	public static String cfg_key_v;
	
	public static String cfg_key_tab;
	
	//TODO -injection START
		public static boolean cfg_multiCoreLoading;
		//TODO -injection END
	
	
	/**　パーティクルの上限設定*/
	public static boolean cfg_particle_limiter;
	public static int cfg_particle_limiter_kazu;
	
	/**　23/06/03試作
	 *　チャンク境界にモブが乗り物に乗っていると消える問題用。鯖と蔵の位置を変えてる*/
	public static boolean cfg_vehicle_server_client_async;
		
	/***/
	public static boolean cfg_gun_renderrange_limiter = true;
	
	
		
	//test専用
	public static float test_x = 0;
	public static float test_y = 0;
	public static float test_z = 0;
	public static float test_x2 = 0;
	public static float test_y2 = 0;
	public static float test_z2 = 0;
	
	
	protected static File configFile;
	
	public static int moi;
	public static int entityid;
	public static String[] modelt = new String[1024];
	public static IModelCustom[] model = new IModelCustom[1024];
	
	public static int am_id;
	public static String[] am_name = new String[1024];
	public static IModelCustom[] am_model = new IModelCustom[1024];
	public static ResourceLocation[] am_tex = new ResourceLocation[1024];
	
	public static int gunlayer_mob;
	public static String[] gunlayer_mob_name = new String[2048];
	
	
	
	public static boolean dontjumpRedner = false;
	
	
//	public static boolean arm_alex = false;
	public static boolean arm_lmm = false;
	
	//public static GLlist displayList ;
	//public static int gllist =  GLAllocation.generateDisplayLists(1);
	//public static int[] gllist = new int[1024];
	//public static List<String> modelt = new ArrayList<String>();
	//public static List<IModelCustom> model = new ArrayList<IModelCustom>();
	
	@Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        
		GVCLPacketHandler.init();
	}
	
	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {	
		GVCSoundEvent.registerSounds(event);
	}
	
	@SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event){
		//event.getRegistry().register(flash);
		
		
	    smoke	= new Item().setUnlocalizedName("smoke").setRegistryName(MOD_ID, "smoke");
	    
	    e_grenade	= new Item().setUnlocalizedName("e_grenade").setRegistryName(MOD_ID, "e_grenade");
	    e_grenadet	= new Item().setUnlocalizedName("e_grenadet").setRegistryName(MOD_ID, "e_grenadet");
	    e_fire	= new Item().setUnlocalizedName("e_fire").setRegistryName(MOD_ID, "e_fire");
	    event.getRegistry().registerAll(flash	= new Item().setUnlocalizedName("flash").setRegistryName(MOD_ID, "flash")
				);
		event.getRegistry().register(smoke);
		event.getRegistry().register(e_grenade);
		event.getRegistry().register(e_grenadet);
		event.getRegistry().register(e_fire);
		event.getRegistry().register(new ItemBlock(b_fire).setRegistryName(MOD_ID, "b_fire"));
	}
	@SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event){
		GameRegistry.registerTileEntity(TileEntityInvasion.class, "invblock");
		{
			b_fire= new BlockB_Fire().setUnlocalizedName("b_fire")
					.setRegistryName(MOD_ID, "b_fire");
			event.getRegistry().register(b_fire);
			
		}
	}
	
	@SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(MOD_ID);
		ModelLoader.setCustomModelResourceLocation(flash, 0, new ModelResourceLocation("gvclib:flash", "inventory"));
		ModelLoader.setCustomModelResourceLocation(e_grenade, 0, new ModelResourceLocation("gvclib:e_grenade", "inventory"));
		ModelLoader.setCustomModelResourceLocation(e_grenadet, 0, new ModelResourceLocation("gvclib:e_grenadet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(e_fire, 0, new ModelResourceLocation("gvclib:e_fire", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(smoke, 0, new ModelResourceLocation("gvclib:smoke" + "0", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b_fire), 0, 
				new ModelResourceLocation("gvclib:b_fire", "inventory"));
	}
	@EventHandler
	public void preInit(FMLPreInitializationEvent pEvent) {
		configFile = pEvent.getSuggestedConfigurationFile();
		Configuration lconf = new Configuration(configFile);
		lconf.load();
		/*cfg_debag	= lconf.get("debag", "cfg_debag", false).getBoolean(false);
		cfg_optifine	= lconf.get("optifine", "cfg_optifine", false).getBoolean(false);
		cfg_optifiney	= lconf.get("optifine", "cfg_optifiney", 1.60D).getDouble(1.60D);
		cfg_optifineys	= lconf.get("optifine", "cfg_optifineys", 1.54D).getDouble(1.54D);
		cfg_bullet_smoke	= lconf.get("bullet", "cfg_bullet_smoke", false).getBoolean(false);
		cfg_bullet_living	= lconf.get("bullet", "cfg_bullet_living", 80).getInt(80);
		cfg_Instant_death_avoidance	= lconf.get("event", "cfg_Instant_death_avoidance", true).getBoolean(true);
		cfg_explotion_drop	= lconf.get("explotion", "cfg_explotion_drop", 1D).getDouble(1D);
		
		cfg_turret_lockpoint	= lconf.get("event", "cfg_turret_lockpoint", true).getBoolean(true);
		
		cfg_entity_render_range	= lconf.get("entity", "cfg_entity_render_range", 20D).getDouble(20D);
		
		cfg_explotion_breakdirt	= lconf.get("explotion", "cfg_explotion_breakdirt", true).getBoolean(true);*/
		lconf.save();
		
		loadConfig();
		syncConfig();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent pEvent) {
		int D = Short.MAX_VALUE;
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_Shell"), EntityB_Shell.class, "EntityB_Shell", 50, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_Bullet"), EntityB_Bullet.class, "EntityB_Bullet", 51, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_Missile"), EntityB_Missile.class, "EntityB_Missile", 52, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_GrenadeB"), EntityB_GrenadeB.class, "EntityB_GrenadeB", 53, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_BulletAA"), EntityB_BulletAA.class, "EntityB_BulletAA", 54, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_TNT"), EntityT_TNT.class, "EntityT_TNT", 55, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_Flash"), EntityT_Flash.class, "EntityT_Flash", 56, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_Smoke"), EntityT_Smoke.class, "EntityT_Smoke", 57, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_Grenade"), EntityT_Grenade.class, "EntityT_Grenade", 58, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_GrenadeT"), EntityT_GrenadeT.class, "EntityT_GrenadeT", 59, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityT_Morter"), EntityT_Morter.class, "EntityT_Morter", 60, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_BombArrow"), EntityB_BombArrow.class, "EntityB_BombArrow", 62, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_BulletFire"), EntityB_BulletFire.class, "EntityB_BulletFire", 61, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_Cratridge"), EntityB_Cratridge.class, "EntityB_Cratridge", 63, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("Entity_Flare"), Entity_Flare.class, "Entity_Flare", 64, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityB_Grapple"), EntityB_Grapple.class, "EntityB_Grapple", 65, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityP_Particle"), EntityP_Particle.class, "EntityP_Particle", 66, this, 128, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation("EntityP_Explosion"), EntityP_Explosion.class, "EntityP_Explosion", 67, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityGVC_PlayerDummy"), EntityGVC_PlayerDummy.class, "EntityGVC_PlayerDummy", 80, this, 128, 5, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("EntityGVC_Dummy"), EntityGVC_Dummy.class, "EntityGVC_Dummy", 99, this, 128, 5, true);
		
		//EntityRegistry.registerModEntity(EntityB_Gun.class, "EntityB_Gun", 60, this, 128, 5, true);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GVCGuiHandler());
		
		FMLCommonHandler.instance().bus().register(this);
		proxy.getClient();
		proxy.ProxyFile();
		proxy.reisterRenderers();
		proxy.registerTileEntity();
		proxy.getFPS();
		
		proxy.reload();
		proxy.xclick();
		
		proxy.jumped();
		proxy.leftclick();
		proxy.rightclick();
		proxy.keyc();
		proxy.keyx();
		proxy.keyg();
		proxy.keyl();
		proxy.keyz();
		proxy.keyb();
		proxy.keyv();
		
		proxy.left();
		proxy.right();
		proxy.up();
		proxy.down();
		proxy.nine();
		proxy.three();
		proxy.LBRACKET();
		proxy.RBRACKET();
		proxy.setting();
		proxy.getEntityPlayerInstance();
		proxy.getCilentWorld();
		proxy.ExRender(null, null, 0, true);
		proxy.ExRender();
		proxy.dropItem(0, null);
		proxy.numpad_0();
		proxy.numpad_1();
		proxy.numpad_2();
		proxy.numpad_3();
		proxy.numpad_4();
		proxy.numpad_5();
		proxy.numpad_6();
		proxy.numpad_7();
		proxy.numpad_8();
		proxy.numpad_9();
		proxy.tab();
		proxy.getEntityPlayerMP();
		MinecraftForge.EVENT_BUS.register(new GVCReloadEvents());
		MinecraftForge.EVENT_BUS.register(new GVCGunEvents());
		MinecraftForge.EVENT_BUS.register(new GVCEventLivingArmor());
		MinecraftForge.EVENT_BUS.register(new GVCEventsLockOn());
		
		MinecraftForge.EVENT_BUS.register(new GVCEvent_MAV());
		MinecraftForge.EVENT_BUS.register(new GVCEventLiving());
		MinecraftForge.EVENT_BUS.register(new GVCEventsRiddingVehicle_Update());
		
		 if (pEvent.getSide().isClient()) {
			 MinecraftForge.EVENT_BUS.register(new GVCEvents_TEST_CN());
			 
			 MinecraftForge.EVENT_BUS.register(new GVCEventsLockOn_client());
			MinecraftForge.EVENT_BUS.register(new GVCEventsGunZoom());
			MinecraftForge.EVENT_BUS.register(new GVCEventRenderArmor());
	//		MinecraftForge.EVENT_BUS.register(new GVCEventRenderArmor_new());
			MinecraftForge.EVENT_BUS.register(new GVCEventOverlay());
			//MinecraftForge.EVENT_BUS.register(new GVCEventsGunRender());
			MinecraftForge.EVENT_BUS.register(new GVCEventsGunRender_First());
			MinecraftForge.EVENT_BUS.register(new GVCEventsGunRender_Third());
			MinecraftForge.EVENT_BUS.register(new GVCEventsGunRender_Setting());
			MinecraftForge.EVENT_BUS.register(new GVCEventsRiddingZoom());
			
			MinecraftForge.EVENT_BUS.register(new GVCEventsRiddingVehicle());
			
			MinecraftForge.EVENT_BUS.register(new GVCEventsClientWorld());
		 }
		
		
		MinecraftForge.EVENT_BUS.register(new GVCEventDamege());
	}
	
	public static void loadConfig()
	{
		File configFile = new File(Loader.instance().getConfigDir(), "gvclib.cfg");
		if (!configFile.exists())
		{
			try
			{
				configFile.createNewFile();
			} catch (Exception e)
			{
				logger.warn("Could not create a gvclib config file.");
				logger.warn(e.getLocalizedMessage());
			}
		}
		config = new Configuration(configFile);
		config.load();
	}

	public static void syncConfig()
	{
		CONFIG.init(config, INSTANCE);
		config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equalsIgnoreCase(mod_GVCLib.MOD_ID))
		{
			mod_GVCLib.syncConfig();
		}
	}
}

	
	
