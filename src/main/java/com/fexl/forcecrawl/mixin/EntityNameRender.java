package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.renderer.entity.EntityRenderer;

@Mixin(EntityRenderer.class)
public class EntityNameRender {
	@Inject(method = "render", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	public void playerNameRender(CallbackInfo callbackInfo) {
		
	}
}
