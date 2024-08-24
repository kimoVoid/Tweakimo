package me.kimovoid.tweakimo;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.kimovoid.tweakimo.config.Config;
import me.kimovoid.tweakimo.freecam.FreeCamEvents;
import me.kimovoid.tweakimo.freecam.FreeCamTickEvents;
import me.kimovoid.tweakimo.keybinding.KeyBindingHandler;
import me.kimovoid.tweakimo.lavavisibility.LavaVisibilityEvents;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = Tweakimo.MODID, version = Tweakimo.VERSION, guiFactory = "me.kimovoid.tweakimo.config.GuiConfigFactory")
public class Tweakimo {

    public static Logger LOGGER;
    public static Config CONFIG;
    public static Tweakimo INSTANCE;

    public static final KeyBinding toggleFreeCam = new KeyBinding("key.tweakimo.freecam.toggle", Keyboard.KEY_F6, "key.categories.misc");
    public static final KeyBinding toggleFakeSneak = new KeyBinding("key.tweakimo.fakesneak.toggle", Keyboard.KEY_RSHIFT, "key.categories.misc");

    public static final String MODID = "tweakimo";
    public static final String VERSION = "@VERSION@";

    private ServerData lastServer;

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        INSTANCE = this;
        LOGGER = LogManager.getLogger("Tweakimo");
        CONFIG = new Config(event.getSuggestedConfigurationFile());

        ClientRegistry.registerKeyBinding(toggleFreeCam);
        ClientRegistry.registerKeyBinding(toggleFakeSneak);

        FMLCommonHandler.instance().bus().register(this);
        FMLCommonHandler.instance().bus().register(new FreeCamTickEvents());
        FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
        MinecraftForge.EVENT_BUS.register(new FreeCamEvents());
        MinecraftForge.EVENT_BUS.register(new LavaVisibilityEvents());
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (MODID.equals(event.modID)) {
            CONFIG.sync(false);
        }
    }

    public ServerData getLastServer() {
        return this.lastServer;
    }

    public void setLastServer(ServerData server) {
        this.lastServer = server;
    }
}