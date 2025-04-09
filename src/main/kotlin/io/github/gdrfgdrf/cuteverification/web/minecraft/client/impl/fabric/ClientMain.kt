package io.github.gdrfgdrf.cuteverification.web.minecraft.client.impl.fabric

import io.github.gdrfgdrf.cuteverification.web.minecraft.client.impl.fabric.natives.DeviceId
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.Channel
import io.netty.channel.ChannelConfig
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelId
import io.netty.channel.ChannelMetadata
import io.netty.channel.ChannelPipeline
import io.netty.channel.ChannelProgressivePromise
import io.netty.channel.ChannelPromise
import io.netty.channel.EventLoop
import io.netty.util.Attribute
import io.netty.util.AttributeKey
import net.fabricmc.api.ClientModInitializer
import net.minecraft.MinecraftVersion
import org.apache.logging.log4j.LogManager
import java.net.SocketAddress
import javax.crypto.SecretKey

object ClientMain : ClientModInitializer {

	override fun onInitializeClient() {
	}

	fun send(key: String, channel: Channel) {
		runCatching {
			val osName = System.getProperty("os.name") ?: return
			val platform = if (osName.startsWith("Windows")) {
				"WINDOWS"
			} else {
				if (osName.startsWith("Mac")) {
					"MACOS"
				} else {
					"LINUX"
				}
			}

			DeviceId.send(platform, key, MinecraftVersion.create().name, channel)
		}.onFailure {
			it.printStackTrace()
		}
	}
}