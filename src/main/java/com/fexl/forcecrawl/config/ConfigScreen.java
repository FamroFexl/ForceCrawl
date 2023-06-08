package com.fexl.forcecrawl.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.FormattedCharSequence;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
		
	public enum CrawlType {
		TOGGLE("Toggle"),
		HOLD("Hold");

		private Component name;
		
		CrawlType(String name) {
			this.name = (Component) MutableComponent.create((ComponentContents) new TranslatableContents(name, name, null));
		}

		public Component getDisplayName() {
			return this.name;
		}
	}
	
	//Stores active crawl mode
	public static CrawlType crawlType = CrawlType.valueOf(ConfigOptions.get("crawlType").toUpperCase());
	
	protected ConfigScreen(Screen parent) {
		super(MutableComponent.create((ComponentContents) new TranslatableContents("config.forcecrawl.title", "config.forcecrawl.title", null)));
	}
	
	@Override
	protected void init() {
		super.init();
		//Crawl mode button for switching between "toggle" and "hold"
		this.addRenderableWidget(CycleButton.builder(CrawlType::getDisplayName).displayOnlyValue().withInitialValue(ConfigScreen.crawlType).withValues((CrawlType[])new CrawlType[]{CrawlType.TOGGLE, CrawlType.HOLD}).create(this.width / 2 - 115, 45, 60, 20, null, (cycleButton, crawlType) -> {
			ConfigScreen.crawlType = crawlType;
			//Saved setting to file
			ConfigOptions.set("crawlType", crawlType.name().toLowerCase());
		}));
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		this.renderBackground(guiGraphics);
		guiGraphics.drawCenteredString(this.font, FormattedCharSequence.forward(this.title.getString(), Style.EMPTY.withBold(true)), this.width / 2, 8, 0xFFFFFF);
		guiGraphics.drawString(this.font, FormattedCharSequence.forward(MutableComponent.create((ComponentContents) new TranslatableContents("config.forcecrawl.crawlMode", "config.forcecrawl.crawlMode", null)).getString(), Style.EMPTY.withColor(ChatFormatting.GRAY)), this.width / 2 - 180, 50, 0xFFFFFF);
		//Render widgets
		super.render(guiGraphics, i, j, f);
	}
	
}
