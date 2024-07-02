package com.fexl.forcecrawl;

import com.fexl.forcecrawl.networking.Packets;
import com.fexl.forcecrawl.networking.packet.PacketCrawl;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ForceCrawl implements ModInitializer {
	public static final String MOD_ID = "forcecrawl";
	
	@Override
	public void onInitialize() {
		PayloadTypeRegistry.playC2S().register(PacketCrawl.type, PacketCrawl.CODEC);
		
		Packets.registerC2SPackets();
	}

}
