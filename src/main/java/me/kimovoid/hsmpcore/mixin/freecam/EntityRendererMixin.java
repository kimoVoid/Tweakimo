package me.kimovoid.hsmpcore.mixin.freecam;

import me.kimovoid.hsmpcore.freecam.FreeCamController;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "updateCameraAndRender")
    private void onBeforeUpdateCameraAndRender(float partialTicks, CallbackInfo info) {
        FreeCamController.instance.onBeforeRenderWorld();
    }

    @Inject(at = @At("TAIL"), method = "updateCameraAndRender")
    private void onAfterUpdateCameraAndRender(float partialTicks, CallbackInfo info) {
        FreeCamController.instance.onAfterRenderWorld();
    }
}