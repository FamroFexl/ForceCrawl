package com.fexl.forcecrawl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.fexl.forcecrawl.ForceCrawlClient;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.controls.ControlsScreen;
import net.minecraft.network.chat.Component;

@Mixin(ControlsScreen.class)
public class OptionsScreen extends OptionsSubScreen {


	public OptionsScreen(Screen screen, Options options, Component component) {
		super(screen, options, component);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;createButton(Lnet/minecraft/client/Options;III)Lnet/minecraft/client/gui/components/AbstractWidget;", ordinal = 2))
	void moveAutoJump(Args args) {
		//Move across
		args.set(1, (int) args.get(1) + 160);
	}
	
	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;createButton(Lnet/minecraft/client/Options;III)Lnet/minecraft/client/gui/components/AbstractWidget;", ordinal = 3))
	void moveOperatorItemsTab(Args args) {
		//Move across
		args.set(1, (int) args.get(1) - 160);
		//Move down
		args.set(2, (int) args.get(2) + 24);
	}
	
	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 2))
	void moveDoneButton(Args args) {
		args.set(1, (int) args.get(1) + 24);
	}
	
	@Inject(method = "init", at = @At("TAIL"))
	void addWidget(CallbackInfo callbackInfo) {
		this.addRenderableWidget(ForceCrawlClient.crawlOption.createButton(this.options, this.width / 2 - 155, this.height / 6 - 12 + 48, 150));
	}
	

}
