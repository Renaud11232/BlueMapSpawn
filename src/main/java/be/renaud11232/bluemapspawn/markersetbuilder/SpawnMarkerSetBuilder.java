package be.renaud11232.bluemapspawn.markersetbuilder;

import be.renaud11232.bluemapspawn.BlueMapSpawnConfiguration;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnMarkerSetBuilder implements MarkerSetBuilder {
    private final FileConfiguration config;
    private final FileConfiguration defaultConfig;

    public SpawnMarkerSetBuilder(FileConfiguration config, FileConfiguration defaultConfig) {
        this.config = config;
        this.defaultConfig = defaultConfig;
    }

    @Override
    public MarkerSet build() {
        return MarkerSet.builder()
                .label(BlueMapSpawnConfiguration.MarkerSet.LABEL.get(config, defaultConfig))
                .toggleable(BlueMapSpawnConfiguration.MarkerSet.TOGGLEABLE.get(config, defaultConfig))
                .defaultHidden(BlueMapSpawnConfiguration.MarkerSet.DEFAULT_HIDDEN.get(config, defaultConfig))
                .build();
    }

    @Override
    public String getKey() {
        return BlueMapSpawnConfiguration.MarkerSet.KEY.get(config, defaultConfig);
    }


}
