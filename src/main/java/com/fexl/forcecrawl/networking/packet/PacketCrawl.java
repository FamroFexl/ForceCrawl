package com.fexl.forcecrawl.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class PacketCrawl {
	
	public static ArrayList<Player> crawlingPlayers = new ArrayList<Player>(0);
	
	public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf packet, PacketSender responseSender) {
		if(packet.readBoolean() == true) {
			crawlingPlayers.add(player);
		}
		else {
			crawlingPlayers.remove(player);
		}
	}
}
