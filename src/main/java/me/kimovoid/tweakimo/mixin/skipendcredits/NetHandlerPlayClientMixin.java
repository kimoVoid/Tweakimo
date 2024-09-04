package me.kimovoid.tweakimo.mixin.skipendcredits;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C16PacketClientStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Redirect(
            method = "handleChangeGameState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V",
                    ordinal = 0
            )
    )
    private void skipEndCredits(Minecraft mc, GuiScreen gui) {
        if (Tweakimo.CONFIG.skipEndCredits) {
            mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
            return;
        }
        mc.displayGuiScreen(gui);
    }
}
