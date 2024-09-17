package me.kimovoid.tweakimo.mixin.reconnectbutton;

import cpw.mods.fml.client.FMLClientHandler;
import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.gui.GuiTexturedButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

@Mixin(GuiIngameMenu.class)
public abstract class GuiIngameMenuMixin extends GuiScreen {

    @Unique @Final private static ResourceLocation RECONNECT_TEXTURE = new ResourceLocation(Tweakimo.MODID, "textures/gui/reconnect_button.png");
    @Unique private GuiTexturedButton reconnectButton;

    @Redirect(
            method = "initGui",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private boolean addDisconnectButton(List instance, Object object) {
        if (Tweakimo.CONFIG.reconnectButton && !this.mc.isIntegratedServerRunning()) {
            GuiButton disconnect = (GuiButton) object;
            disconnect.width = 176;
            instance.add(disconnect);
            return instance.add(this.reconnectButton = new GuiTexturedButton(10, disconnect.xPosition + 180, disconnect.yPosition, 20, 20, 0, 0, 20, RECONNECT_TEXTURE, 32,  64));
        }
        return instance.add(object);
    }

    @Inject(method = "drawScreen", at = @At("TAIL"))
    private void renderTooltip(int x, int y, float delta, CallbackInfo ci) {
        if (this.reconnectButton != null && this.reconnectButton.func_146115_a()) {
            String tooltip = "Reconnect";
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                tooltip += " (unsafe)";
            }
            this.func_146283_a(Collections.singletonList(tooltip), x, y);
        }
    }

    @Inject(method = "actionPerformed", at = @At("TAIL"))
    private void doReconnect(GuiButton btn, CallbackInfo ci) {
        if (btn.id == 10 && Tweakimo.INSTANCE.getLastServer() != null) {
            btn.enabled = false;
            if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
            }
            FMLClientHandler.instance().connectToServer(this, Tweakimo.INSTANCE.getLastServer());
        }
    }
}