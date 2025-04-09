package io.github.gdrfgdrf.cuteverification.web.minecraft.client.impl.fabric.mixin;

import io.github.gdrfgdrf.cuteverification.web.minecraft.client.impl.fabric.ClientMain;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author gdrfgdrf
 */
@Mixin(ClientLoginNetworkHandler.class)
public class ClientLoginNetworkHandlerMixin {
    @Shadow
    @Final
    private ClientConnection connection;

    @Inject(at = @At("RETURN"), method = "Lnet/minecraft/client/network/ClientLoginNetworkHandler;onLoginSuccess(Lnet/minecraft/network/packet/s2c/login/LoginSuccessS2CPacket;)V")
    public void onLoginSuccess_mixin(LoginSuccessS2CPacket packet, CallbackInfo callbackInfo) {
        new Thread(() -> {
            try {
                Thread.sleep(200);
                ClientMain.INSTANCE.send("test_key_1234567", connection.channel);
            } catch (InterruptedException e) {
            }
        }).start();
    }

}
