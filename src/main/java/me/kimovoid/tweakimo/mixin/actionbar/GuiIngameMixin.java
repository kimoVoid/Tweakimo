package me.kimovoid.tweakimo.mixin.actionbar;

import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.freecam.FreeCamController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameMixin extends GuiIngame {

    public GuiIngameMixin(Minecraft p_i1036_1_) {
        super(p_i1036_1_);
    }

    @Redirect(
            method = "renderRecordOverlay",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I"
            )
    )
    private int fixActionBarHeight(FontRenderer fr, String str, int x, int y, int color) {
        if (FreeCamController.instance.active && !Tweakimo.CONFIG.freeCamInteract) { // Render free cam text at the bottom
            return fr.drawString(str, x, 32, color);
        }

        return fr.drawString(str, x, y - 20, color);
    }
}