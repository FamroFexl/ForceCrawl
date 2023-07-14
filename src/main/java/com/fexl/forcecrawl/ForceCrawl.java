package com.fexl.forcecrawl;

import com.fexl.forcecrawl.networking.Packets;

import net.fabricmc.api.ModInitializer;

public class ForceCrawl implements ModInitializer {
	public static final String MOD_ID = "forcecrawl";
	
	@Override
	public void onInitialize() {
		Packets.registerC2SPackets();
	}

}
