package me.kimovoid.hsmpcore.mixin.fakesneak;

import me.kimovoid.hsmpcore.fakesneak.FakeSneakController;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public class EntityMixin {

    @Redirect(method = "moveEntity",
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/entity/Entity;onGround:Z",
                            ordinal = 0
                    )
            ),
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
}
