package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fexl.forcecrawl.networking.packet.PacketCrawl;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.SERVER)
@Mixin(Player.class)
public class PlayerPoseServer {
	
	@Inject(method = "Lnet/minecraft/world/entity/player/Player;updatePlayerPose()V", at = @At(value = "HEAD"), cancellable = true)
	public void updatePlayerPose(CallbackInfo event) {
		Player player = (Player)(Object) this;
		
		//Check if the player who's pose is being activated was shifting previously
		if(PacketCrawl.crawlingPlayers.contains(player)) {
			player.setPose(Pose.SWIMMING);
			event.cancel();
		}
	}
}
