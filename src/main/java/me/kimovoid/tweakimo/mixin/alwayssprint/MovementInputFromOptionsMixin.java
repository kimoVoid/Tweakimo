package me.kimovoid.tweakimo.mixin.alwayssprint;

import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.controllers.FreeCamController;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public class MovementInputFromOptionsMixin {

    @Inject(method = "updatePlayerMoveState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/settings/KeyBinding;getIsKeyPressed()Z",
                    ordinal = 0
            )
    )
    private void doAlwaysSprint(CallbackInfo ci) {
        if (Tweakimo.CONFIG.alwaysSprint && !FreeCamController.instance.isActive()) {
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
}
