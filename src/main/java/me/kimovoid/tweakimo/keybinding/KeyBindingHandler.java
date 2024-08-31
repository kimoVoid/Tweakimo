package me.kimovoid.tweakimo.keybinding;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.fakesneak.FakeSneakController;
import me.kimovoid.tweakimo.freecam.FreeCamController;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public class KeyBindingHandler {

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent ev) {
        this.handleKeyBindings();
    }

    public void handleKeyBindings() {
        Minecraft mc = Minecraft.getMinecraft();
        int key = Keyboard.getEventKey();

        if (mc.thePlayer == null
                || mc.currentScreen != null
                || Keyboard.getEventKeyState()) {
            return;
        }

        if (key == Tweakimo.toggleFreeCam.getKeyCode()) {
            FreeCamController.instance.toggle();
            printToggleMessage("free cam", FreeCamController.instance.isActive());
        }

        if (key == Tweakimo.toggleFakeSneak.getKeyCode()) {
            FakeSneakController.instance.active = !FakeSneakController.instance.active;
            printToggleMessage("fake sneaking", FakeSneakController.instance.active);
        }
    }

    private void printToggleMessage(String type, boolean active) {
        Minecraft.getMinecraft().ingameGUI.func_110326_a(String.format("Toggled %s %s%s", type, active ? EnumChatFormatting.GREEN : EnumChatFormatting.RED, active ? "ON" : "OFF"), false);
    }
}