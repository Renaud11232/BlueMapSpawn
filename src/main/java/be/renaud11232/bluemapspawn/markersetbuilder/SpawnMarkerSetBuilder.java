package be.renaud11232.bluemapspawn.markersetbuilder;

import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnMarkerSetBuilder {

    private final FileConfiguration config;

    public SpawnMarkerSetBuilder(FileConfiguration config) {
        this.config = config;
    }

    public MarkerSet build() {
        return MarkerSet.builder()
                .label(config.getString("marker_set.label", "Spawn"))
                .toggleable(config.getBoolean("marker_set.toggleable", true))
                .defaultHidden(config.getBoolean("marker_set.default_hidden", false))
                .build();
    }

    public String getKey() {
        return config.getString("marker_set.key", "bluemapspawn");
    }


}
