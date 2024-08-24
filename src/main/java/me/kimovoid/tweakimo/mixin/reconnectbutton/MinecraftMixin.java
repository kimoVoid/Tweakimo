package me.kimovoid.tweakimo.mixin.reconnectbutton;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "setServerData",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;currentServerData:Lnet/minecraft/client/multiplayer/ServerData;"
            )
    )
    private void setLastServer(ServerData data, CallbackInfo ci) {
        if (data != null) Tweakimo.INSTANCE.setLastServer(data);
    }
}