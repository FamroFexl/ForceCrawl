package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fexl.forcecrawl.ForceCrawl;
import com.fexl.forcecrawl.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class PlayerPose {
	
	private static boolean crawlTrue = false;
	
	@Inject(method = "Lnet/minecraft/world/entity/player/Player;updatePlayerPose()V", at = @At(value = "HEAD"), cancellable = true)
	public void updatePlayerPose(CallbackInfo event) {
		Player player = (Player)(Object) this; 
		ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		
		if(config.settings.crawlMode == ModConfig.ModeSelect.Hold) {
			if (ForceCrawl.crawlKey.isDown()) {
				player.setPose(Pose.SWIMMING);
				event.cancel();
					
			}
		} else
			if(config.settings.crawlMode == ModConfig.ModeSelect.Toggle) {
				while (ForceCrawl.crawlKey.consumeClick()) {
					if(crawlTrue) {
						crawlTrue = false;
					}
					else if(crawlTrue == false) {
						crawlTrue = true;
					}
				}
						
				if(crawlTrue) {
					player.setPose(Pose.SWIMMING);
					event.cancel();
				}
			}
		}
}
