package com.fexl.forcecrawl.networking;

import com.fexl.forcecrawl.networking.packet.PacketCrawl;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Packets {
	
	
	public static void registerC2SPackets() {
		//Register server-side packet receiver
		ServerPlayNetworking.registerGlobalReceiver(PacketCrawl.type, (payload, context) -> {
			PacketCrawl.receive(context.server(), context.player(), payload.bool());
		});
	}
}
