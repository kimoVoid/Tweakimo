package me.kimovoid.tweakimo.mixin.reconnectbutton;

import cpw.mods.fml.client.FMLClientHandler;
import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDisconnected.class)
public class GuiDisconnectedMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("TAIL"))
    private void addReconnectButton(CallbackInfo ci) {
        if (Tweakimo.CONFIG.reconnectButton) {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 110, I18n.format("tweakimo.gui.reconnect")));
        }
    }

    @Inject(method = "actionPerformed", at = @At("TAIL"))
    private void doReconnect(GuiButton btn, CallbackInfo ci) {
        if (btn.id == 1 && Tweakimo.INSTANCE.getLastServer() != null) {
            FMLClientHandler.instance().connectToServer(this, Tweakimo.INSTANCE.getLastServer());
        }
    }
}