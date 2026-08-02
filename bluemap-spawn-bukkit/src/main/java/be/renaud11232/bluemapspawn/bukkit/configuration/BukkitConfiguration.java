package be.renaud11232.bluemapspawn.bukkit.configuration;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.configuration.MarkerSetConfiguration;

import java.util.Map;
import java.util.stream.Collectors;

public class BukkitConfiguration implements Configuration {
    private final BukkitMarkerSetConfiguration markerSet;

    public BukkitConfiguration(BukkitMarkerSetConfiguration markerSet) {
        this.markerSet = markerSet;
    }

    public static BukkitConfiguration deserialize(Map<String, Object> args) {
        Map<String, Object> markerSetValues = args.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("marker_set."))
                .collect(Collectors.toMap(entry -> entry.getKey().replace("marker_set.", ""), Map.Entry::getValue));
        BukkitMarkerSetConfiguration markerSet = BukkitMarkerSetConfiguration.deserialize(markerSetValues);
        return new BukkitConfiguration(markerSet);
    }

    @Override
    public MarkerSetConfiguration getMarkerSet() {
        return markerSet == null ? new BukkitMarkerSetConfiguration(null, null, null, null, null) : markerSet;
    }
}
