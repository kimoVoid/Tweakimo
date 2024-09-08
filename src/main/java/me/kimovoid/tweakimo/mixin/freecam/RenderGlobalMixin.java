package me.kimovoid.tweakimo.mixin.freecam;

import me.kimovoid.tweakimo.controllers.FreeCamController;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin {

    @Inject(at = @At("HEAD"), method = "renderEntities")
    private void onBeforeRenderEntities(EntityLivingBase p_147589_1_, ICamera p_147589_2_, float p_147589_3_, CallbackInfo ci) {
        FreeCamController.instance.onBeforeRenderEntities();
    }

    @Inject(at = @At("TAIL"), method = "renderEntities")
    private void onAfterRenderEntities(EntityLivingBase p_147589_1_, ICamera p_147589_2_, float p_147589_3_, CallbackInfo ci) {
        FreeCamController.instance.onAfterRenderEntities();
    }
}