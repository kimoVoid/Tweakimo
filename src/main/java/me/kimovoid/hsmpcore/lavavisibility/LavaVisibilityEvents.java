package me.kimovoid.hsmpcore.lavavisibility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.kimovoid.hsmpcore.HSMPCore;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.EntityViewRenderEvent;

public class LavaVisibilityEvents {

    @SubscribeEvent
    public void onFogRender(EntityViewRenderEvent.FogDensity event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if ((HSMPCore.CONFIG.lavaVisibility && canSeeThroughLava(player)) || player.capabilities.isCreativeMode) {
            event.density = player.capabilities.isCreativeMode ? 0.0f : 0.03f;
            event.setCanceled(true);
        }
    }

    public boolean canSeeThroughLava(EntityPlayer player) {
        return player.isInsideOfMaterial(Material.lava) && player.isPotionActive(Potion.fireResistance);
    }
}
