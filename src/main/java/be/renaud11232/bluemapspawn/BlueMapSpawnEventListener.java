package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.updater.WorldMarkerUpdater;
import be.renaud11232.bluemapspawn.updater.WorldSpawnMarkerUpdater;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;

public class BlueMapSpawnEventListener implements Listener {
    private final WorldMarkerUpdater worldMarkerUpdater;

    public BlueMapSpawnEventListener(BlueMapSpawn plugin, BlueMapAPI api) {
        this.worldMarkerUpdater = new WorldSpawnMarkerUpdater(plugin, api);
    }

    @EventHandler
    public void onSpawnChange(SpawnChangeEvent spawnChangeEvent) {
        worldMarkerUpdater.update(spawnChangeEvent.getWorld());
    }
}
