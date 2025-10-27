package gvclib.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class GVCLPacketHandler {
 
    //このMOD用のSimpleNetworkWrapperを生成。チャンネルの文字列は固有であれば何でも良い。MODIDの利用を推奨。
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("GVCLib");
    public static final SimpleNetworkWrapper INSTANCE2 = NetworkRegistry.INSTANCE.newSimpleChannel("GVCLib2");
 
 
    public static void init() {
        INSTANCE.registerMessage(GVCLMessageKeyPressedHandler.class, GVCLMessageKeyPressed.class, 0, Side.SERVER);
        INSTANCE2.registerMessage(GVCLClientMessageKeyPressedHandler.class, GVCLClientMessageKeyPressed.class, 1, Side.CLIENT);
    }
}