package be.renaud11232.bluemapspawn.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;

public class BukkitBlueMapSpawnEventListener implements Listener {
    private final BukkitBlueMapSpawnModule module;

    public BukkitBlueMapSpawnEventListener(BukkitBlueMapSpawnModule module) {
        this.module = module;
    }

    @EventHandler
    public void onSpawnChange(SpawnChangeEvent spawnChangeEvent) {
        module.update(spawnChangeEvent.getWorld());
    }
}
