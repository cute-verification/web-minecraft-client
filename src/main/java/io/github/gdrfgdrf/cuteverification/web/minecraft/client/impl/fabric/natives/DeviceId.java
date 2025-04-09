package io.github.gdrfgdrf.cuteverification.web.minecraft.client.impl.fabric.natives;

import io.netty.channel.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author gdrfgdrf
 */
public class DeviceId {
    public static native int send(String platform, String signatureKey, String version, Channel channel);

    static {
        System.loadLibrary("cute-verification-device-id-provider");
    }
}
