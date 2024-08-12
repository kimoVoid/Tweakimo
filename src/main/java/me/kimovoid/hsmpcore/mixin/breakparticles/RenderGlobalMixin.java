package me.kimovoid.hsmpcore.mixin.breakparticles;

import me.kimovoid.hsmpcore.HSMPCore;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {

    @Redirect(method = "playAuxSFX", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;addBlockDestroyEffects(IIILnet/minecraft/block/Block;I)V"))
    private void doBreakParticles(EffectRenderer instance, int d1, int d2, int k1, Block j1, int i1) {
        if (HSMPCore.CONFIG.blockBreakingParticles) {
            instance.addBlockDestroyEffects(d1, d2, k1, j1, i1);
        }
    }
}
