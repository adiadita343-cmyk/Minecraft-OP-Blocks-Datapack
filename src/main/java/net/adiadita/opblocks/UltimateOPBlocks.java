package net.adiadita.opblocks;

import net.adiadita.opblocks.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltimateOPBlocks implements ModInitializer {
    public static final String MOD_ID = "opblocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("UltimateOPBlocks initializing – injecting overpowered loot into block breaking...");
        ModConfig.load();
        LOGGER.info("Configuration loaded. Ready to dominate the server.");
    }
}
