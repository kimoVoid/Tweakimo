package me.kimovoid.hsmpcore;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.kimovoid.hsmpcore.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HSMPCore.MODID, version = HSMPCore.VERSION, guiFactory = "me.kimovoid.hsmpcore.config.GuiConfigFactory")
public class HSMPCore {

    public static Logger LOGGER;
    public static Config CONFIG;
    public static HSMPCore INSTANCE;

    public static final String MODID = "hsmpcore";
    public static final String VERSION = "@VERSION@";

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        INSTANCE = this;
        LOGGER = LogManager.getLogger("HSMPCore");
        CONFIG = new Config(event.getSuggestedConfigurationFile());
        LOGGER.info("This is so Historical!");
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (MODID.equals(event.modID)) {
            CONFIG.sync(false);
        }
    }
}
