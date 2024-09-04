package me.kimovoid.tweakimo.config;

import cpw.mods.fml.client.config.GuiConfigEntries;
import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public final Configuration config;

    public boolean entityJitterFix = true;
    public boolean blockBreakingParticles = false;
    public boolean centerInventoryEffects = true;
    public boolean alwaysSprint = false;
    public boolean transparentConfigs = false;
    public boolean lavaVisibility = false;
    public float flySpeed = 1.0f;
    public boolean reconnectButton = false;
    public boolean shortTab = false;
    public boolean skipEndCredits = true;

    public boolean enableScoreboard = true;
    public boolean scoreboardTotal = true;
    public boolean scoreboardShadow = false;
    public boolean scoreboardHighlightName = false;
    public boolean hideScoreboardInF3 = true;
    public int scoreboardLimit = 15;
    public String scoreboardColor = "red";

    public boolean freeCamInteract = false;
    public double freeCamFlyAcceleration = 40;
    public double freeCamFlyMaxSpeed = 20;
    public double freeCamFlySlowdownFactor = 0.01;

    public Config(File path) {
        this.migrateOldConfig(path);
        config = new Configuration(path);
        this.sync(true);
    }

    public void sync(boolean load) {
        if (load && !config.isChild) {
            config.load();
        }

        this.entityJitterFix = config.getBoolean("entityJitterFix", Configuration.CATEGORY_GENERAL, true, "Fixes mob jitter, aka position mismatch");
        this.blockBreakingParticles = config.getBoolean("blockBreakingParticles", Configuration.CATEGORY_GENERAL, false, "Enable/disable block breaking particles");
        this.centerInventoryEffects = config.getBoolean("centerInventoryEffects", Configuration.CATEGORY_GENERAL, true, "Centers inventory with potion effects");
        this.alwaysSprint = config.getBoolean("alwaysSprint", Configuration.CATEGORY_GENERAL, false, "Automatically sprint when moving forward");
        this.transparentConfigs = config.getBoolean("transparentConfigs", Configuration.CATEGORY_GENERAL, true, "Makes all Forge mod config GUIs transparent");
        this.lavaVisibility = config.getBoolean("lavaVisibility", Configuration.CATEGORY_GENERAL, false, "Allows you to see through the lava with Fire Resistance");
        Property flySpeedConfig = config.get(Configuration.CATEGORY_GENERAL, "flySpeed", 1.0, "Flight speed while holding SPRINT")
                .setMinValue(1.0)
                .setMaxValue(10.0)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.flySpeed = (float) flySpeedConfig.getDouble();
        this.reconnectButton = config.getBoolean("reconnectButton", Configuration.CATEGORY_GENERAL, false, "Adds a reconnect button when you're\ndisconnected from a server");
        this.shortTab = config.getBoolean("shortTab", Configuration.CATEGORY_GENERAL, false, "Removes empty player list slots");
        this.skipEndCredits = config.getBoolean("skipEndCredits", Configuration.CATEGORY_GENERAL, true, "Skips credits screen after\ngoing through an end portal");

        this.enableScoreboard = config.getBoolean("enableScoreboard", "scoreboard", true, "Sidebar rendering");
        this.scoreboardTotal = config.getBoolean("scoreboardTotal", "scoreboard", true, "Sidebar score total");
        this.scoreboardShadow = config.getBoolean("scoreboardShadow", "scoreboard", false, "Sidebar text shadow");
        this.scoreboardHighlightName = config.getBoolean("scoreboardHighlightName", "scoreboard", false, "Highlight own name in the sidebar");
        this.hideScoreboardInF3 = config.getBoolean("hideScoreboardInF3", "scoreboard", true, "Hides sidebar when debug screen is open");
        Property scoreLimitConfig = config.get("scoreboard", "scoreboardLimit", 15, "Sidebar lines limit")
                .setMinValue(1)
                .setMaxValue(50)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.scoreboardLimit = scoreLimitConfig.getInt();

        List<String> colorNames = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            colorNames.add(EnumChatFormatting.values()[i] + EnumChatFormatting.values()[i].getFriendlyName());
        }

        Property scoreColorConfig = config.get("scoreboard", "scoreboardColor", EnumChatFormatting.RED + "red", "Sidebar score color")
                .setValidValues(colorNames.toArray(new String[0]));
        this.scoreboardColor = scoreColorConfig.getString().substring(2);

        this.freeCamInteract = config.getBoolean("freeCamInteract", "freecam", false, "Allows you to interact with blocks/entities in free cam");

        Property freeCamFlyAccelConfig = config.get("freecam", "freeCamFlyAcceleration", 40.0, "Free cam flight speed acceleration")
                .setMinValue(0.0)
                .setMaxValue(100.0)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.freeCamFlyAcceleration = freeCamFlyAccelConfig.getDouble();
        Property freeCamFlyMaxSpeedConfig = config.get("freecam", "freeCamFlyMaxSpeed", 20.0, "Free cam flight max speed")
                .setMinValue(0.0)
                .setMaxValue(50.0)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.freeCamFlyMaxSpeed = freeCamFlyMaxSpeedConfig.getDouble();
        Property freeCamFlySlowdownConfig = config.get("freecam", "freeCamFlySlowdownFactor", 0.01, "Free cam flight speed slowdown factor")
                .setMinValue(0.0)
                .setMaxValue(0.1)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.freeCamFlySlowdownFactor = freeCamFlySlowdownConfig.getDouble();

        if (config.hasChanged()) {
            config.save();
        }
    }

    private void migrateOldConfig(File newFile) {
        try {
            File oldConfig = new File(newFile.getParent() + "/hsmpcore.cfg");
            boolean rename = oldConfig.renameTo(newFile);
            if (rename) {
                Tweakimo.LOGGER.info("Migrated old configs from HSMPCore!");
            }
        } catch (Exception ignored) {}
    }
}