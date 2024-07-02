package com.fexl.forcecrawl.mixin;

import org.apache.commons.lang3.StringEscapeUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fexl.forcecrawl.ForceCrawlClient;
import com.fexl.forcecrawl.networking.packet.PacketCrawl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
@Mixin(Player.class)
public class PlayerPoseClient {
	@Inject(method = "Lnet/minecraft/world/entity/player/Player;updatePlayerPose()V", at = @At(value = "HEAD"), cancellable = true)
	public void updatePlayerPose(CallbackInfo event) {
		Player player = (Player)(Object) this;
		//System.out.println(Minecraft.getInstance().getUser().getName().equals(player.getName().getString()));
		
		//Check if crawling has been activated
		if(ForceCrawlClient.clientOn) {
			System.out.println("Client: \"" + player + "\" Updated Crawling State");
			player.setPose(Pose.SWIMMING);
			event.cancel();
		}
	}
}