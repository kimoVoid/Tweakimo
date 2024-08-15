package me.kimovoid.hsmpcore.keybinding;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import me.kimovoid.hsmpcore.HSMPCore;
import me.kimovoid.hsmpcore.fakesneak.FakeSneakController;
import me.kimovoid.hsmpcore.freecam.FreeCamController;
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

        if (key == HSMPCore.toggleFreeCam.getKeyCode()) {
            FreeCamController.instance.toggle();
            printToggleMessage("Free cam", FreeCamController.instance.isActive());
        }

        if (key == HSMPCore.toggleFakeSneak.getKeyCode()) {
            FakeSneakController.instance.active = !FakeSneakController.instance.active;
            printToggleMessage("Fake sneaking", FakeSneakController.instance.active);
        }
    }

    private void printToggleMessage(String type, boolean active) {
        Minecraft.getMinecraft().ingameGUI.func_110326_a(String.format("%s has been %s%s", type, active ? EnumChatFormatting.GREEN : EnumChatFormatting.RED, active ? "enabled" : "disabled"), false);
    }
}
