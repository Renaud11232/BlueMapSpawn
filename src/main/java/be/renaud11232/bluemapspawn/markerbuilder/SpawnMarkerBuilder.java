package be.renaud11232.bluemapspawn.markerbuilder;

import be.renaud11232.bluemapspawn.BlueMapSpawnConfiguration;
import be.renaud11232.bluemapspawn.BlueMapSpawnIcon;
import be.renaud11232.bluemapspawn.BlueMapSpawnStyleClass;
import de.bluecolored.bluemap.api.markers.POIMarker;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnMarkerBuilder implements MarkerBuilder<Location> {
    private final FileConfiguration config;
    private final FileConfiguration defaultConfig;

    public SpawnMarkerBuilder(FileConfiguration config, FileConfiguration defaultConfig) {
        this.config = config;
        this.defaultConfig = defaultConfig;
    }

    @Override
    public POIMarker build(Location location) {
        return POIMarker.builder()
                .label(BlueMapSpawnConfiguration.MarkerSet.Marker.LABEL.get(config, defaultConfig))
                .maxDistance(BlueMapSpawnConfiguration.MarkerSet.Marker.MAX_DISTANCE.get(config, defaultConfig))
                .position(location.getX(), location.getY(), location.getZ())
                .icon(BlueMapSpawnIcon.SPAWN.getPath(), BlueMapSpawnIcon.SPAWN.getAnchor())
                .styleClasses(BlueMapSpawnStyleClass.MARKER)
                .build();
    }
}
