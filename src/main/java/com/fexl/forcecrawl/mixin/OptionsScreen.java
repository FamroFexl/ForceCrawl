package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.fexl.forcecrawl.ForceCrawlClient;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;

@Mixin(ControlsScreen.class)
public class OptionsScreen {
	@Inject(method = "options", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private static void options(Options options, CallbackInfoReturnable<OptionInstance<?>[]> ci) {
		ci.setReturnValue(new OptionInstance[] {options.toggleCrouch(), options.toggleSprint(), ForceCrawlClient.crawlOption, options.autoJump(), options.operatorItemsTab()});
	}
}
