package me.kimovoid.hsmpcore.config;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import me.kimovoid.hsmpcore.HSMPCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiConfigFactory implements IModGuiFactory {

    @Override
    public void initialize(final Minecraft instance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return HSMPCoreGuiConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(final RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class HSMPCoreGuiConfig extends GuiConfig {
        public HSMPCoreGuiConfig(final GuiScreen parent) {
            super(parent, getConfigElements(HSMPCore.CONFIG.config), HSMPCore.MODID, false, false, "HSMPCore");
        }

        private static List<IConfigElement> getConfigElements(final Configuration configuration) {
            List<IConfigElement> elements = new ArrayList<>();

            elements.addAll((new ConfigElement(configuration.getCategory("general"))).getChildElements());

            ConfigCategory scoreboard = configuration.getCategory("scoreboard").setLanguageKey("hsmpcore.config.scoreboard");
            scoreboard.setComment("Configure in-game sidebar");
            elements.add(new ConfigElement(scoreboard));

            ConfigCategory freecam = configuration.getCategory("freecam").setLanguageKey("hsmpcore.config.freecam");
            freecam.setComment("Configure free cam mode");
            elements.add(new ConfigElement(freecam));

            return elements;
        }
    }
}
