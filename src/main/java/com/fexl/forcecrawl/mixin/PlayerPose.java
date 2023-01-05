package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fexl.forcecrawl.ForceCrawl;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class PlayerPose {
	
	@Inject(method = "Lnet/minecraft/world/entity/player/Player;updatePlayerPose()V", at = @At(value = "HEAD"), cancellable = true)
	public void updatePlayerPose(CallbackInfo event) {
		Player player = (Player)(Object) this;
		
		if (ForceCrawl.crawlKey.isDown()) {
			player.setPose(Pose.SWIMMING);
			event.cancel();
		}
	}
}
