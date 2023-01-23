package com.fexl.forcecrawl;

import org.lwjgl.glfw.GLFW;

import com.fexl.forcecrawl.config.ModConfig;
import com.mojang.blaze3d.platform.InputConstants;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class ForceCrawl implements ModInitializer {

	public static KeyMapping crawlKey;
	
	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		
		crawlKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.forcecrawl.crawl",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_CAPS_LOCK,
				"key.forcecrawl.category"
				));
	}

}
