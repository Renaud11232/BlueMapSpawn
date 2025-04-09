package be.renaud11232.bluemapspawn.updater;

import be.renaud11232.bluemapspawn.BlueMapSpawn;
import be.renaud11232.bluemapspawn.BlueMapSpawnConfiguration;
import be.renaud11232.bluemapspawn.markerbuilder.MarkerBuilder;
import be.renaud11232.bluemapspawn.markerbuilder.SpawnMarkerBuilder;
import be.renaud11232.bluemapspawn.markersetbuilder.MarkerSetBuilder;
import be.renaud11232.bluemapspawn.markersetbuilder.SpawnMarkerSetBuilder;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.markers.Marker;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class WorldSpawnMarkerUpdater implements WorldMarkerUpdater {
    private final BlueMapSpawn plugin;
    private final BlueMapAPI api;
    private final MarkerSetBuilder spawnMarkerSetBuilder;
    private final MarkerBuilder<Location> spawnMarkerBuilder;

    public WorldSpawnMarkerUpdater(BlueMapSpawn plugin, BlueMapAPI api) {
        this.api = api;
        this.plugin = plugin;
        this.spawnMarkerSetBuilder = new SpawnMarkerSetBuilder(plugin.getConfig(), plugin.getDefaultConfig());
        this.spawnMarkerBuilder = new SpawnMarkerBuilder(plugin.getConfig(), plugin.getDefaultConfig());
    }

    @Override
    public void update(World world) {
        Location spawn = world.getSpawnLocation();
        Map<String, Marker> markers = new HashMap<>();
        markers.put(BlueMapSpawnConfiguration.MarkerSet.Marker.KEY.get(plugin.getConfig(), plugin.getDefaultConfig()), spawnMarkerBuilder.build(spawn));
        api.getWorld(world).ifPresent(blueMapWorld -> blueMapWorld.getMaps().forEach(map -> {
            MarkerSet markerSet = map.getMarkerSets().computeIfAbsent(spawnMarkerSetBuilder.getKey(), id -> spawnMarkerSetBuilder.build());
            markerSet.getMarkers().clear();
            markerSet.getMarkers().putAll(markers);
        }));
    }
}
