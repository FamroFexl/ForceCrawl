package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fexl.forcecrawl.ForceCrawlClient;

import net.minecraft.client.Options;

@Mixin(Options.class)
public class CrawlToggle {
	
	@Inject(method = "processOptions", at = @At("HEAD"))
	void crawlToggle(Options.FieldAccess fieldAccess, CallbackInfo ci) {
		fieldAccess.process("toggleCrawl", ForceCrawlClient.crawlOption);
	}
}
