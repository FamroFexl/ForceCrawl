package com.fexl.forcecrawl;

import org.lwjgl.glfw.GLFW;

import com.fexl.forcecrawl.config.ConfigOptions;
import com.fexl.forcecrawl.config.ConfigScreen;
import com.fexl.forcecrawl.config.ConfigScreen.CrawlType;
import com.fexl.forcecrawl.networking.Packets;
import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.FriendlyByteBuf;

@Environment(EnvType.CLIENT)
public class ForceCrawlClient implements ClientModInitializer {
	public static KeyMapping crawlKey;
	
	public static Boolean clientOn = false;
	
	@Override
	public void onInitializeClient() {
		//Initialize config file
		if(!ConfigOptions.configFile.exists()) { ConfigOptions.fileInit(); }
		
		crawlKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.forcecrawl.crawl",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_CAPS_LOCK,
				"key.forcecrawl.category"
				));
		
		ClientTickEvents.END_WORLD_TICK.register(client -> {
			getCrawlKey();
		});
		
	}
	
	private void getCrawlKey() {
		//If crawl mode is set to "hold"
		if(ConfigScreen.crawlType == CrawlType.HOLD) {
			if (crawlKey.isDown()) {
				if(!clientOn) {
					//Send packet with crawl=true
					FriendlyByteBuf packet = PacketByteBufs.create(); packet.writeBoolean(true); ClientPlayNetworking.send(Packets.CRAWL_ID, packet);
					clientOn = true;
				}
			}
			else {
				if(clientOn) {
					//Send packet with crawl=false
					FriendlyByteBuf packet = PacketByteBufs.create(); packet.writeBoolean(false); ClientPlayNetworking.send(Packets.CRAWL_ID, packet);
					clientOn = false;
				}
			}
		}
		//If crawl mode is set to "toggle"
		else if(ConfigScreen.crawlType == CrawlType.TOGGLE) {
			while (ForceCrawlClient.crawlKey.consumeClick()) {
				if(clientOn == false) {
					//Send packet with crawl=true
					FriendlyByteBuf packet = PacketByteBufs.create(); packet.writeBoolean(true); ClientPlayNetworking.send(Packets.CRAWL_ID, packet);
					clientOn = true;
				}
				else {
					//Send packet with crawl=false
					FriendlyByteBuf packet = PacketByteBufs.create(); packet.writeBoolean(false); ClientPlayNetworking.send(Packets.CRAWL_ID, packet);
					clientOn = false;
				}
			}
		}
	}

}
