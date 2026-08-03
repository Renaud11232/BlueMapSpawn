package be.renaud11232.bluemapspawn.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;

public class BukkitBlueMapSpawnEventListener implements Listener {
    private BukkitBlueMapSpawnModule module;

    public BukkitBlueMapSpawnEventListener() {
        this.module = null;
    }

    public void setModule(BukkitBlueMapSpawnModule module) {
        this.module = module;
    }

    @EventHandler
    public void onSpawnChange(SpawnChangeEvent spawnChangeEvent) {
        if (module != null) {
            module.update(spawnChangeEvent.getWorld());
        }
    }
}
