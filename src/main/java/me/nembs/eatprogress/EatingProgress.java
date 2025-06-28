package me.nembs.eatprogress;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EatingProgress implements ModInitializer {
	public static final String MOD_ID = "eatingprogress";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Eating Progress Mod initialized correctly");
	}
}