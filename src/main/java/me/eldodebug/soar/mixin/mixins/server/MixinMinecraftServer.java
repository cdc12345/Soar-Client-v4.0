package me.eldodebug.soar.mixin.mixins.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @ModifyVariable( method = "addFaviconToStatusResponse", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ServerStatusResponse;setFavicon(Ljava/lang/String;)V", shift = At.Shift.AFTER), ordinal = 1)
    private ByteBuf patcher$releaseByteBuf(ByteBuf buf1) {
        buf1.release();
        return buf1;
    }
}
