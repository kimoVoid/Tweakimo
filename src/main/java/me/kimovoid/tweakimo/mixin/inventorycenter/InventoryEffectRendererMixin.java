package me.kimovoid.tweakimo.mixin.inventorycenter;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InventoryEffectRenderer.class)
public abstract class InventoryEffectRendererMixin extends GuiContainer {

    @Shadow private boolean field_147045_u;

    public InventoryEffectRendererMixin(Container p_i1072_1_) {
        super(p_i1072_1_);
    }

    /**
     * @author kimoVoid
     * @reason Center inventory with effects
     */
    @Overwrite
    public void initGui() {
        super.initGui();

        if (!this.mc.thePlayer.getActivePotionEffects().isEmpty()) {
            if (!Tweakimo.CONFIG.centerInventoryEffects) {
                this.guiLeft = 160 + (this.width - this.xSize - 200) / 2;
            }
            this.field_147045_u = true;
        }
    }
}
