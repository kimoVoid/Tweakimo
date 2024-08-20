package me.kimovoid.tweakimo.mixin.flyspeed;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public class MovementInputFromOptionsMixin {

    @Shadow private GameSettings gameSettings;

    @Inject(method = "updatePlayerMoveState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/settings/KeyBinding;getIsKeyPressed()Z",
                    ordinal = 0,
                    shift = At.Shift.BEFORE
            )
    )
    private void doFlySpeed(CallbackInfo ci) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player == null || !player.capabilities.isFlying || !player.capabilities.isCreativeMode) {
            return;
        }

        if (this.gameSettings.keyBindSprint.getIsKeyPressed()) {
            player.capabilities.setFlySpeed(0.05F * Tweakimo.CONFIG.flySpeed);
            if (player.movementInput.sneak) {
                player.motionY -= 0.15 * Tweakimo.CONFIG.flySpeed;
            }

            if (player.movementInput.jump) {
                player.motionY += 0.15 * Tweakimo.CONFIG.flySpeed;
            }
        } else {
            if (player.capabilities.getFlySpeed() != 0.05F) {
                player.capabilities.setFlySpeed(0.05F);
            }
        }
    }
}
