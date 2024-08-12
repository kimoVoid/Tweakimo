package me.kimovoid.hsmpcore.mixin.villagercrashfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.entity.IMerchant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMerchant.class)
public class GuiMerchantMixin {

    @Shadow
    private IMerchant field_147037_w;

    @Shadow
    private int field_147041_z;

    @Inject(
            method="actionPerformed",
            at=@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/inventory/ContainerMerchant;setCurrentRecipeIndex(I)V"
            ),
            cancellable = true
    )
    protected void removeAction(GuiButton button, CallbackInfo ci) {
        int max = field_147037_w.getRecipes(Minecraft.getMinecraft().thePlayer).size();
        if (field_147041_z == max) {
            --field_147041_z;
            ci.cancel();
        }
        if (field_147041_z < 0) {
            field_147041_z++;
            ci.cancel();
        }
    }
}
