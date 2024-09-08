package me.kimovoid.tweakimo.mixin.pickfrominventory;

import me.kimovoid.tweakimo.Tweakimo;
import me.kimovoid.tweakimo.controllers.PickBlockController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ForgeHooks.class, remap = false)
public class ForgeHooksMixin {

    @Inject(
            method = "onPickBlock",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private static void pickBlockFromInventory(MovingObjectPosition target, EntityPlayer player, World world, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()
                || target == null
                || !Tweakimo.CONFIG.pickFromInventory
                || player.capabilities.isCreativeMode) {
            return;
        }

        cir.setReturnValue(PickBlockController.instance.pickFromInventory(target, world, player));
    }
}