package com.fexl.forcecrawl.networking.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;

import com.fexl.forcecrawl.ForceCrawl;

public record PacketCrawl(Boolean bool) implements CustomPacketPayload {
	
	public static ArrayList<ServerPlayer> crawlingPlayers = new ArrayList<ServerPlayer>();
	
	public static void receive(ServerPlayer player, Boolean bool) {
		if(bool) {
			crawlingPlayers.add(player);
		}
		else {
			crawlingPlayers.remove(player);
		}
	}
	
	public static final ResourceLocation CRAWL_ID = ResourceLocation.fromNamespaceAndPath(ForceCrawl.MOD_ID, "packet.forcecrawl.crawl");
	public static final CustomPacketPayload.Type<PacketCrawl> type = new CustomPacketPayload.Type<PacketCrawl>(CRAWL_ID);
	
	public static final StreamCodec<RegistryFriendlyByteBuf, PacketCrawl> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketCrawl::bool, PacketCrawl::new);
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return type;
	}
}
