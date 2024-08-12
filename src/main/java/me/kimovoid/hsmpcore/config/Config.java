package me.kimovoid.hsmpcore.config;

import cpw.mods.fml.client.config.GuiConfigEntries;
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
    public float flySpeed = 1.0f;

    public boolean enableScoreboard = true;
    public boolean scoreboardTotal = true;
    public boolean scoreboardShadow = false;
    public boolean scoreboardHighlightName = false;
    public boolean hideScoreboardInF3 = true;
    public int scoreboardLimit = 15;
    public String scoreboardColor = "red";

    public Config(File path) {
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

        this.enableScoreboard = config.getBoolean("enableScoreboard", "scoreboard", true, "Sidebar rendering");
        this.scoreboardTotal = config.getBoolean("scoreboardTotal", "scoreboard", true, "Sidebar score total");
        this.scoreboardShadow = config.getBoolean("scoreboardShadow", "scoreboard", false, "Sidebar text shadow");
        this.scoreboardHighlightName = config.getBoolean("scoreboardHighlightName", "scoreboard", false, "Highlight own name in the sidebar");
        this.hideScoreboardInF3 = config.getBoolean("hideScoreboardInF3", "scoreboard", true, "Hides sidebar when debug screen is open");

        Property flySpeedConfig = config.get(Configuration.CATEGORY_GENERAL, "flySpeed", 1.0, "Flight speed while holding SPRINT")
                .setMinValue(1.0)
                .setMaxValue(10.0)
                .setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        this.flySpeed = (float) flySpeedConfig.getDouble();

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

        if (config.hasChanged()) {
            config.save();
        }
    }
}