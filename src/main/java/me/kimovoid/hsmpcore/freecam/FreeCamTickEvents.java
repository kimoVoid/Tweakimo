package me.kimovoid.hsmpcore.freecam;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class FreeCamTickEvents {

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        FreeCamController.instance.onKeyInput();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            FreeCamController.instance.onRenderTickStart();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            FreeCamController.instance.onClientTickStart();
        }
    }
}