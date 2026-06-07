package net.adiadita.opblocks.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.adiadita.opblocks.UltimateOPBlocks;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("ultimateopblocks.json");

    public float godGearDropChance = 0.5f;
    public int diamondBlockStackCount = 64;
    public int cosmicDropCooldownMinutes = 60;
    public boolean cosmicDropEnabled = true;
    public String cosmicDropBroadcastMessage = "§6§l[Cosmic Drop] §e%s§r §dhas broken a block and unleashed a cosmic drop! §6+64 Netherite Ingots & +64 Smithing Tables!";

    private static ModConfig instance;

    public static ModConfig getInstance() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    public static ModConfig load() {
        ModConfig config = new ModConfig();
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                config = GSON.fromJson(reader, ModConfig.class);
                UltimateOPBlocks.LOGGER.info("Config loaded successfully.");
            } catch (IOException e) {
                UltimateOPBlocks.LOGGER.error("Failed to load config, using defaults.", e);
            }
        } else {
            save(config);
            UltimateOPBlocks.LOGGER.info("Default config created.");
        }
        instance = config;
        return config;
    }

    private static void save(ModConfig config) {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            UltimateOPBlocks.LOGGER.error("Failed to save config.", e);
        }
    }
}