package me.kimovoid.tweakimo.mixin.shorttab;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiIngameForge.class)
public class GuiIngameMixin {

    @Redirect(method = "renderPlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;currentServerMaxPlayers:I"))
    private int setRows(NetHandlerPlayClient instance) {
        if (Tweakimo.CONFIG.shortTab) {
            return instance.playerInfoList.size();
        }
        return instance.currentServerMaxPlayers;
    }
}