package me.kimovoid.tweakimo.mixin.fakesneak;

import me.kimovoid.tweakimo.controllers.FakeSneakController;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {

    @Redirect(
            method = "moveEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isSneaking()Z",
                    ordinal = 0
            )
    )
    private boolean fakeSneaking(Entity entity) {
        if (FakeSneakController.instance.active && entity instanceof EntityPlayerSP) {
            return true;
        }

        return entity.isSneaking();
    }

    /* Thank you Pine for helping me out with this <3 */
    @ModifyVariable(
            method = "moveEntity",
            name = "flag",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getCollidingBoundingBoxes(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;",
                    ordinal = 3,
                    shift = At.Shift.BEFORE
            )
    )
    private boolean setFlag(boolean flag) {
        Entity entity = (Entity) (Object) this;
        return entity.onGround && entity.isSneaking() && entity instanceof EntityPlayer;
    }
}