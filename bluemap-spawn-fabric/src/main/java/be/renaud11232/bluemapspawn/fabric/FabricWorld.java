package be.renaud11232.bluemapspawn.fabric;

import be.renaud11232.bluemapspawn.Wrapper;
import be.renaud11232.bluemapspawn.world.SpawnPoint;
import be.renaud11232.bluemapspawn.world.World;
import net.minecraft.server.level.ServerLevel;

public class FabricWorld extends Wrapper<ServerLevel> implements World {
    public FabricWorld(ServerLevel wrapped) {
        super(wrapped);
    }

    @Override
    public SpawnPoint getSpawnPoint() {
        var location = wrapped.getLevelData().getRespawnData().pos();
        return new SpawnPoint(
                location.getX(),
                location.getY(),
                location.getZ()
        );
    }
}
