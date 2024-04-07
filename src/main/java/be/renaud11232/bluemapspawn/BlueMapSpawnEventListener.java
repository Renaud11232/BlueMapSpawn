package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.updater.WorldSpawnMarkerUpdater;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;

public class BlueMapSpawnEventListener implements Listener {

    private final WorldSpawnMarkerUpdater worldSpawnMarkerUpdater;

    public BlueMapSpawnEventListener(BlueMapSpawn plugin, BlueMapAPI api) {
        this.worldSpawnMarkerUpdater = new WorldSpawnMarkerUpdater(plugin, api);
    }

    @EventHandler
    public void onSpawnChange(SpawnChangeEvent spawnChangeEvent) {
        worldSpawnMarkerUpdater.update(spawnChangeEvent.getWorld());
    }

}
