package me.kimovoid.tweakimo.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.controllers.FreeCamController;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.world.WorldEvent;

public class FreeCamEvents {

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        FreeCamController.instance.active = false;
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (FreeCamController.instance.isActive()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (FreeCamController.instance.isActive()
                && !Tweakimo.CONFIG.freeCamInteract
                && (event.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR)
                || event.type.equals(RenderGameOverlayEvent.ElementType.HEALTH)
                || event.type.equals(RenderGameOverlayEvent.ElementType.FOOD)
                || event.type.equals(RenderGameOverlayEvent.ElementType.ARMOR)
                || event.type.equals(RenderGameOverlayEvent.ElementType.EXPERIENCE)
                || event.type.equals(RenderGameOverlayEvent.ElementType.HEALTHMOUNT)
                || event.type.equals(RenderGameOverlayEvent.ElementType.JUMPBAR))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (FreeCamController.instance.isActive()) {
            event.setCanceled(true);
        }
    }

    /**
     * This disables interacting while in free cam,
     * however, it only works if the attack/use keybindings
     * are mouse buttons. This should be replaced with mixins,
     * but I'm currently lazy <3
     */
    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        if (FreeCamController.instance.isActive()
                && Minecraft.getMinecraft().currentScreen == null
                && !Tweakimo.CONFIG.freeCamInteract) {
            event.setCanceled(true);
        }
    }
}
