package be.renaud11232.bluemapspawn.fabric.mixin;

import be.renaud11232.bluemapspawn.fabric.event.RespawnDataSetCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(
            at = @At("RETURN"),
            method = "setRespawnData"
    )
    private void onRespawnDataSet(LevelData.RespawnData respawnData, CallbackInfo ci) {
        RespawnDataSetCallback.EVENT.invoker().interact((ServerLevel) (Object) this);
    }
}
