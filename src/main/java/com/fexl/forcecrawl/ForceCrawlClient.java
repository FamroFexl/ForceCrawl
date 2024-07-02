package com.fexl.forcecrawl;

import org.lwjgl.glfw.GLFW;

import com.fexl.forcecrawl.networking.Packets;
import com.fexl.forcecrawl.networking.packet.PacketCrawl;
import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.ToggleKeyMapping;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

//import static net.minecraft.client.Options.MOVEMENT_TOGGLE;
//import static net.minecraft.client.Options.MOVEMENT_HOLD;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ForceCrawlClient implements ClientModInitializer {
	public static Boolean clientOn = false;
	public static OptionInstance<Boolean> crawlOption;
	public static KeyMapping crawlKey;
	
	@Override
	public void onInitializeClient() {
		
		crawlOption = OptionInstance.createBoolean(
				"option.forcecrawl.title", 
				OptionInstance.noTooltip(), 
				(optionText, value) -> {return (Boolean)value ? Component.translatable("options.key.toggle") : Component.translatable("options.key.hold");}, 
				false,
				(value) -> {});
		Objects.requireNonNull(ForceCrawlClient.crawlOption);
		
		crawlKey = KeyBindingHelper.registerKeyBinding(new ToggleKeyMapping("option.forcecrawl.title", GLFW.GLFW_KEY_LEFT_ALT, KeyMapping.CATEGORY_MOVEMENT, () -> crawlOption.get()));
		
		
		
		ClientTickEvents.END_WORLD_TICK.register(client -> {
			getCrawlKey();
		});
		
	}
	
	private void getCrawlKey() {
		//If crawl mode is set to "hold"
		if(!crawlOption.get()) {
			if (crawlKey.isDown()) {
				if(!clientOn) {
					//Send packet with crawl=true
					ClientPlayNetworking.send(new PacketCrawl(true));
					clientOn = true;
				}
			}
			else {
				if(clientOn) {
					//Send packet with crawl=false
					ClientPlayNetworking.send(new PacketCrawl(false));
					clientOn = false;
				}
			}
		}
		//If crawl mode is set to "toggle"
		else if(crawlOption.get()) {
			while (ForceCrawlClient.crawlKey.consumeClick()) {
				if(!clientOn) {
					//Send packet with crawl=true
					ClientPlayNetworking.send(new PacketCrawl(true));
					clientOn = true;
				}
				else {
					//Send packet with crawl=false
					ClientPlayNetworking.send(new PacketCrawl(false));
					clientOn = false;
				}
			}
		}
	}

}
