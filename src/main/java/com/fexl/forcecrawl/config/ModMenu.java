package com.fexl.forcecrawl.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;

@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return ModMenu::genConfig;
	}
	
	private static Screen genConfig(Screen parent) {
		return new ConfigScreen(parent);
	}
}
