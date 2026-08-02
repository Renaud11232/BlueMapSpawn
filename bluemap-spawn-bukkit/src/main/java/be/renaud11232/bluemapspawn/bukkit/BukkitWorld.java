package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.Wrapper;
import be.renaud11232.bluemapspawn.world.SpawnPoint;
import org.bukkit.World;

public class BukkitWorld extends Wrapper<World> implements be.renaud11232.bluemapspawn.world.World {
    public BukkitWorld(World wrapped) {
        super(wrapped);
    }

    @Override
    public SpawnPoint getSpawnPoint() {
        var location = wrapped.getSpawnLocation();
        return new SpawnPoint(
                location.getX(),
                location.getY(),
                location.getZ()
        );
    }
}
