package com.fexl.forcecrawl;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class ForceCrawl implements ModInitializer {

	public static KeyMapping crawlKey;
	
	@Override
	public void onInitialize() {
		crawlKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.forcecrawl.forcecrawl",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"key.forcecrawl.category"
				));
	}

}
