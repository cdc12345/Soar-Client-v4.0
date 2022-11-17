package me.eldodebug.soar.mixin.mixins.chunk;

import java.util.Queue;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import me.eldodebug.soar.utils.interfaces.IMixinVisGraph;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.EnumFacing;

@Mixin(VisGraph.class)
public class MixinVisGraph implements IMixinVisGraph{
	
    @Unique
    private boolean limitScan;

    @Inject(method = "func_178604_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/VisGraph;func_178610_a(ILjava/util/Set;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void patcher$checkLimitScan(int enumfacing, CallbackInfoReturnable<Set<EnumFacing>> cir, Set<EnumFacing> set, Queue<Integer> queue, int i) {
        if (this.limitScan && set.size() > 1) {
            cir.setReturnValue(set);
        }
    }

    @Override
    public void setLimitScan(boolean limitScan) {
        this.limitScan = limitScan;
    }
}
