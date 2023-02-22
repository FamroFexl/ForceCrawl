package com.fexl.forcecrawl.config;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
		
	public enum CrawlType {
		TOGGLE("Toggle"),
		HOLD("Hold");

		private Component name;
		
		CrawlType(String name) {
			this.name = new TextComponent(name);
		}

		public Component getDisplayName() {
			return this.name;
		}
	}
	
	//Stores active crawl mode
	public static CrawlType crawlType = CrawlType.valueOf(ConfigOptions.get("crawlType").toUpperCase());
	
	protected ConfigScreen(Screen parent) {
		super(new TranslatableComponent("config.forcecrawl.title"));
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
	public void render(PoseStack poseStack, int i, int j, float f) {
		this.renderBackground(poseStack);
		GuiComponent.drawCenteredString(poseStack, this.font, FormattedCharSequence.forward(this.title.getString(), Style.EMPTY.withBold(true)), this.width / 2, 8, 0xFFFFFF);
		GuiComponent.drawString(poseStack, this.font, FormattedCharSequence.forward(new TranslatableComponent("config.forcecrawl.crawlMode").getString(), Style.EMPTY.withColor(ChatFormatting.GRAY)), this.width / 2 - 180, 50, 0xFFFFFF);
		//Render widgets
		super.render(poseStack, i, j, f);
	}
	
}
