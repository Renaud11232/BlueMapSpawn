package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.updater.WorldMarkerUpdater;
import be.renaud11232.bluemapspawn.updater.WorldSpawnMarkerUpdater;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;

public class BlueMapSpawnUpdateTask implements Runnable {
    private final WorldMarkerUpdater worldMarkerUpdater;

    public BlueMapSpawnUpdateTask(BlueMapSpawn plugin, BlueMapAPI api) {
        this.worldMarkerUpdater = new WorldSpawnMarkerUpdater(plugin, api);
    }

    @Override
    public void run() {
        Bukkit.getServer().getWorlds().forEach(worldMarkerUpdater::update);
    }
}
