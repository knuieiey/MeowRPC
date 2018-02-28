package me.dilysmcat.meowrpc;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class MeowEventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
    public void connectedToServer(ClientConnectedToServerEvent e) {
		
		MeowRPC.instance.discord.updateTimestamp();
		
		if(!e.isLocal) {
			if(Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().startsWith("hypixel.net")) {
			    MeowRPC.instance.setPlayingOnHypixel(true);
			    MeowRPC.instance.discord.updateState(
			            "In Lobby",
                        "Playing Multiplayer on Hypixel"
                );
            } else {
                MeowRPC.instance.discord.updateState(
                        "On: "+Minecraft.getMinecraft().getCurrentServerData().serverIP,
                        "Playing Multiplayer");
            }
		} else {
			MeowRPC.instance.discord.updateState(
					"On: "+Minecraft.getMinecraft().getIntegratedServer().getWorldName(),
					"Playing Singleplayer"
					);
		}
	
    }
	
	@SubscribeEvent
	public void disconnectedFromServer(ClientDisconnectionFromServerEvent e) {
	    if(MeowRPC.instance.isPlayingOnHypixel())
	        MeowRPC.instance.setPlayingOnHypixel(false);
		MeowRPC.instance.discord.updateTimestamp();
		MeowRPC.instance.discord.updateState("In Menu", "");
	}
	
}
