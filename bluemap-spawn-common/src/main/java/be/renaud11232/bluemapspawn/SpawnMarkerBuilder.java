package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.world.World;
import de.bluecolored.bluemap.api.markers.POIMarker;

public class SpawnMarkerBuilder {
    private final Configuration configuration;

    public SpawnMarkerBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public POIMarker build(World world) {
        return POIMarker.builder()
                .label(configuration.getMarkerSet().getMarker().getLabel())
                .maxDistance(configuration.getMarkerSet().getMarker().getMaxDistance())
                .position(world.getSpawnPoint().x(), world.getSpawnPoint().y(), world.getSpawnPoint().z())
                .icon(BlueMapSpawnIcon.SPAWN.getSrc(), BlueMapSpawnIcon.SPAWN.getAnchor())
                .styleClasses(BlueMapSpawnStyleClass.MARKER)
                .build();
    }
}
