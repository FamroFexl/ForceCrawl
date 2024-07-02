package com.fexl.forcecrawl.networking.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.client.server.LanServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import com.fexl.forcecrawl.ForceCrawl;

@Environment(EnvType.CLIENT)
public record PacketCrawl(Boolean bool) implements CustomPacketPayload {
	
	public static ArrayList<ServerPlayer> crawlingPlayers = new ArrayList<ServerPlayer>();
	
	public static void receive(MinecraftServer server, ServerPlayer player, Boolean bool) {
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
