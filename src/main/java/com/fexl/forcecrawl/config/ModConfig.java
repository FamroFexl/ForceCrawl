package com.fexl.forcecrawl.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "forcecrawl")
public class ModConfig extends PartitioningSerializer.GlobalData {
	//@ConfigEntry.Category("a")
	@ConfigEntry.Gui.TransitiveObject
	public Settings settings = new Settings();
	
	public enum ModeSelect {
        Hold,
        Toggle
    }
	
	@Config(name = "settings_1")
	public static class Settings implements ConfigData {
		@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
		public ModeSelect crawlMode = ModeSelect.Hold;
	}
}
