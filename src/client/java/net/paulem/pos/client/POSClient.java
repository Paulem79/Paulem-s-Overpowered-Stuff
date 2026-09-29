package net.paulem.pos.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.paulem.pos.POS;
import net.paulem.pos.client.hud.ArmorHUD;

public class POSClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudElementRegistry.attachElementBefore(VanillaHudElements.HOTBAR, POS.id("before_chat"), ArmorHUD::extract);
	}
}