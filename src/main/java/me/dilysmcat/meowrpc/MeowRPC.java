package me.dilysmcat.meowrpc;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = MeowRPC.MODID, version = MeowRPC.VERSION)
public class MeowRPC
{
    public static final String MODID = "meowrpc";
    public static final String VERSION = "1.0";
    
    @Instance
    public static MeowRPC instance = new MeowRPC();
    
    public MeowConnection discord;
    private boolean playingOnHypixel = false;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	MeowEventHandler handler = new MeowEventHandler();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	discord = new MeowConnection();
    	discord.run();
    }

    public void setPlayingOnHypixel(boolean b) {
        this.playingOnHypixel = b;
    }
    public boolean isPlayingOnHypixel() {
        return this.playingOnHypixel;
    }
}
