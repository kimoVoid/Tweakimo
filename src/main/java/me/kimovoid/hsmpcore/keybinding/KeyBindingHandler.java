package me.kimovoid.hsmpcore.keybinding;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import me.kimovoid.hsmpcore.HSMPCore;
import me.kimovoid.hsmpcore.fakesneak.FakeSneakController;
import me.kimovoid.hsmpcore.freecam.FreeCamController;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class KeyBindingHandler {

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        this.handleKeyBindings();
    }

    public void handleKeyBindings() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) {
            return;
        }
        if (mc.currentScreen != null) {
            return;
        }

        if (HSMPCore.toggleFreeCam.getIsKeyPressed()) {
            FreeCamController.instance.toggle();
            printToggleMessage("Free cam", FreeCamController.instance.isActive());
        }

        if (HSMPCore.toggleFakeSneak.getIsKeyPressed()) {
            FakeSneakController.instance.active = !FakeSneakController.instance.active;
            printToggleMessage("Fake sneaking", FakeSneakController.instance.active);
        }
    }

    private void printToggleMessage(String type, boolean active) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(
                String.format("%s%s has been %s", EnumChatFormatting.GRAY, type, active ? "enabled" : "disabled")
        ));
    }
}
