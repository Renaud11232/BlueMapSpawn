package be.renaud11232.bluemapspawn.updater;

import be.renaud11232.bluemapspawn.BlueMapSpawn;
import be.renaud11232.bluemapspawn.markerbuilder.SpawnMarkerBuilder;
import be.renaud11232.bluemapspawn.markersetbuilder.SpawnMarkerSetBuilder;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.markers.Marker;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class WorldSpawnMarkerUpdater {

    private final BlueMapAPI api;
    private final FileConfiguration config;
    private final SpawnMarkerSetBuilder spawnMarkerSetBuilder;
    private final SpawnMarkerBuilder spawnMarkerBuilder;

    public WorldSpawnMarkerUpdater(BlueMapSpawn plugin, BlueMapAPI api) {
        this.api = api;
        this.config = plugin.getConfig();
        this.spawnMarkerSetBuilder = new SpawnMarkerSetBuilder(plugin.getConfig());
        this.spawnMarkerBuilder = new SpawnMarkerBuilder(plugin.getConfig());
    }

    public void update(World world) {
        Location spawn = world.getSpawnLocation();
        Map<String, Marker> markers = new HashMap<>();
        markers.put(config.getString("marker_set.marker.key", "bluemapspawn-spawn"), spawnMarkerBuilder.build(spawn));
        api.getWorld(world).ifPresent(blueMapWorld -> blueMapWorld.getMaps().forEach(map -> {
            MarkerSet markerSet = map.getMarkerSets().computeIfAbsent(spawnMarkerSetBuilder.getKey(), id -> spawnMarkerSetBuilder.build());
            markerSet.getMarkers().clear();
            markerSet.getMarkers().putAll(markers);
        }));
    }

}
