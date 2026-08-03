package be.renaud11232.bluemapspawn.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;

public interface RespawnDataSetCallback {
    Event<RespawnDataSetCallback> EVENT = EventFactory.createArrayBacked(RespawnDataSetCallback.class, (listeners) -> (serverLevel) -> {
        for (RespawnDataSetCallback listener : listeners) {
            InteractionResult result = listener.interact(serverLevel);
            if (result != InteractionResult.PASS) {
                return result;
            }
        }
        return InteractionResult.PASS;
    });

    InteractionResult interact(ServerLevel serverLevel);
}
