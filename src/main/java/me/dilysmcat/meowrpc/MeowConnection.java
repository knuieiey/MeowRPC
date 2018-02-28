package me.dilysmcat.meowrpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordEventHandlers.OnReady;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class MeowConnection {

	private static final String APP_ID = "410457877598175242";
	
	private DiscordRPC lib;
	private DiscordEventHandlers handlers;
	private DiscordRichPresence presence;
	
	public MeowConnection() {
		
		lib = DiscordRPC.INSTANCE;
		handlers = new DiscordEventHandlers();
		
		handlers.ready = new OnReady() {
			@Override
			public void accept() {
				System.out.println("MeowRPC is ready!");
			}
		};
		
		lib.Discord_Initialize(APP_ID, handlers, true, "");
		
		presence = new DiscordRichPresence();
		presence.startTimestamp = System.currentTimeMillis() / 1000;
		presence.largeImageKey = "minecraft-logo";
		presence.state = "In Menu";
		lib.Discord_UpdatePresence(presence);
		
	}
	
	public void run() {
		new Thread("RPC-Callback-Handler") {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()) {
					lib.Discord_UpdatePresence(presence);
					lib.Discord_RunCallbacks();
					try {
						Thread.sleep(2000);
					} catch(InterruptedException ignored) {}
				}
			}
		}.start();
	}
	
	public void updateTimestamp() {
		presence.startTimestamp = System.currentTimeMillis() / 1000;
	}
	public void updateTimestamp(long timestamp) {
		presence.startTimestamp = timestamp;
	}
	public void updateState(String state, String details) {
		presence.state = state;
		presence.details = details;
	}
	public void updateImageText(String text) {
		presence.largeImageText = text;
	}
	
}
