package me.kimovoid.hsmpcore.mixin.freecam;

import me.kimovoid.hsmpcore.freecam.FreeCamController;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin extends Entity {

    public EntityPlayerSPMixin(World world) {
        super(world);
    }

    @Override
    public void setAngles(float yaw, float pitch) {
        if (FreeCamController.instance.isActive()) {
            FreeCamController.instance.onMouseTurn(yaw, -pitch);
        } else {
            super.setAngles(yaw, pitch);
        }
    }
}