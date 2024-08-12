package me.kimovoid.hsmpcore.mixin.config;

import cpw.mods.fml.client.config.GuiConfig;
import me.kimovoid.hsmpcore.HSMPCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuiSlot.class)
public abstract class GuiSlotMixin {

    @Shadow @Final private Minecraft mc;
    @Shadow protected abstract void drawContainerBackground(Tessellator tessellator);

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSlot;drawContainerBackground(Lnet/minecraft/client/renderer/Tessellator;)V", remap = false))
    private void removeBackground(GuiSlot instance, Tessellator tessellator) {
        if (this.mc.currentScreen instanceof GuiConfig
                && HSMPCore.CONFIG.transparentConfigs
                && this.mc.theWorld != null) {
            return;
        }

        drawContainerBackground(tessellator);
    }
}