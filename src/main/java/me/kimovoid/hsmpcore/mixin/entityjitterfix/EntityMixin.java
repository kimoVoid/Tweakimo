package me.kimovoid.hsmpcore.mixin.entityjitterfix;

import me.kimovoid.hsmpcore.HSMPCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {

    @Redirect(method = "moveEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posX:D", opcode = 181))
    private void fixX(Entity entity, double value) {
        if (!HSMPCore.CONFIG.entityJitterFix
                || !entity.worldObj.isRemote
                || entity instanceof EntityPlayer
                || !(entity instanceof EntityLivingBase)) {
            entity.posX = value;
        }
    }

    @Redirect(method = "moveEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posY:D", opcode = 181))
    private void fixY(Entity entity, double value) {
        if (!HSMPCore.CONFIG.entityJitterFix
                || !entity.worldObj.isRemote
                || entity instanceof EntityPlayer
                || !(entity instanceof EntityLivingBase)) {
            entity.posY = value;
        }
    }

    @Redirect(method = "moveEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posZ:D", opcode = 181))
    private void fixZ(Entity entity, double value) {
        if (!HSMPCore.CONFIG.entityJitterFix
                || !entity.worldObj.isRemote
                || entity instanceof EntityPlayer
                || !(entity instanceof EntityLivingBase)) {
            entity.posZ = value;
        }
    }
}
