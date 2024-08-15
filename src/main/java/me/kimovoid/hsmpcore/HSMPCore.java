package me.kimovoid.hsmpcore;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.kimovoid.hsmpcore.config.Config;
import me.kimovoid.hsmpcore.freecam.FreeCamEvents;
import me.kimovoid.hsmpcore.freecam.FreeCamTickEvents;
import me.kimovoid.hsmpcore.keybinding.KeyBindingHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = HSMPCore.MODID, version = HSMPCore.VERSION, guiFactory = "me.kimovoid.hsmpcore.config.GuiConfigFactory")
public class HSMPCore {

    public static Logger LOGGER;
    public static Config CONFIG;
    public static HSMPCore INSTANCE;

    public static final KeyBinding toggleFreeCam = new KeyBinding("key.hsmpcore.freecam.toggle", Keyboard.KEY_F6, "key.categories.misc");
    public static final KeyBinding toggleFakeSneak = new KeyBinding("key.hsmpcore.fakesneak.toggle", Keyboard.KEY_RSHIFT, "key.categories.misc");

    public static final String MODID = "hsmpcore";
    public static final String VERSION = "@VERSION@";

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        INSTANCE = this;
        LOGGER = LogManager.getLogger("HSMPCore");
        CONFIG = new Config(event.getSuggestedConfigurationFile());
        LOGGER.info("This is so Historical!");
        
        ClientRegistry.registerKeyBinding(toggleFreeCam);
        ClientRegistry.registerKeyBinding(toggleFakeSneak);

        FMLCommonHandler.instance().bus().register(this);
        FMLCommonHandler.instance().bus().register(new FreeCamTickEvents());
        FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
        MinecraftForge.EVENT_BUS.register(new FreeCamEvents());
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (MODID.equals(event.modID)) {
            CONFIG.sync(false);
        }
    }
}
