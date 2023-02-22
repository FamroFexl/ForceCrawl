package com.fexl.forcecrawl.networking;

import com.fexl.forcecrawl.ForceCrawl;
import com.fexl.forcecrawl.networking.packet.PacketCrawl;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class Packets {
	public static final ResourceLocation CRAWL_ID = new ResourceLocation(ForceCrawl.MOD_ID, "packet.forcecrawl.crawl");
	
	public static void registerC2SPackets() {
		//Register server-side packet receiver
		ServerPlayNetworking.registerGlobalReceiver(CRAWL_ID, PacketCrawl::receive);
	}
}
