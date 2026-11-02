package be.renaud11232.bluemapspawn.bukkit.configuration;

import be.renaud11232.bluemapspawn.configuration.MarkerConfiguration;

import java.util.Map;

public class BukkitMarkerConfiguration implements MarkerConfiguration {
    private final String id;
    private final String label;
    private final Integer maxDistance;

    public BukkitMarkerConfiguration(String id, String label, Integer maxDistance) {
        this.id = id;
        this.label = label;
        this.maxDistance = maxDistance;
    }

    public static BukkitMarkerConfiguration deserialize(Map<String, Object> args) {
        String id = (String) args.get("id");
        String label = (String) args.get("label");
        Integer maxDistance = (Integer) args.get("max_distance");
        return new BukkitMarkerConfiguration(id, label, maxDistance);
    }

    @Override
    public String getId() {
        return id == null ? "bluemapspawn-spawn" : id;
    }

    @Override
    public String getLabel() {
        return label == null ? "Spawn" : label;
    }

    @Override
    public int getMaxDistance() {
        return maxDistance == null ? 1000 : maxDistance;
    }
}
