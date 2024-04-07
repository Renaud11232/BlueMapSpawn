package be.renaud11232.bluemapspawn.markerbuilder;

import com.flowpowered.math.vector.Vector2i;
import de.bluecolored.bluemap.api.markers.POIMarker;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnMarkerBuilder {

    private final FileConfiguration config;

    public SpawnMarkerBuilder(FileConfiguration config) {
        this.config = config;
    }

    public POIMarker build(Location location) {
        return POIMarker.builder()
                .label(config.getString("marker_set.marker.label", "Spawn"))
                .maxDistance(config.getDouble("marker_set.marker.max_distance", 1000))
                .position(location.getX(), location.getY(), location.getZ())
                .icon("assets/bluemapspawn/img/spawn.png", new Vector2i(12, 12))
                .styleClasses("bluemapspawn-marker")
                .build();
    }

}
