package be.renaud11232.bluemapspawn.bukkit.configuration;

import be.renaud11232.bluemapspawn.configuration.MarkerConfiguration;
import be.renaud11232.bluemapspawn.configuration.MarkerSetConfiguration;

import java.util.Map;
import java.util.stream.Collectors;

public class BukkitMarkerSetConfiguration implements MarkerSetConfiguration {
    private final String id;
    private final String label;
    private final Boolean toggleable;
    private final Boolean hiddenByDefault;
    private final BukkitMarkerConfiguration marker;

    public BukkitMarkerSetConfiguration(String id, String label, Boolean toggleable, Boolean hiddenByDefault, BukkitMarkerConfiguration marker) {
        this.id = id;
        this.label = label;
        this.toggleable = toggleable;
        this.hiddenByDefault = hiddenByDefault;
        this.marker = marker;
    }

    public static BukkitMarkerSetConfiguration deserialize(Map<String, Object> args) {
        String id = (String) args.get("id");
        String label = (String) args.get("label");
        Boolean toggleable = (Boolean) args.get("toggleable");
        Boolean hiddenByDefault = (Boolean) args.get("default_hidden");
        Map<String, Object> markerValues = args.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("marker."))
                .collect(Collectors.toMap(entry -> entry.getKey().replace("marker.", ""), Map.Entry::getValue));
        BukkitMarkerConfiguration marker = BukkitMarkerConfiguration.deserialize(markerValues);
        return new BukkitMarkerSetConfiguration(id, label, toggleable, hiddenByDefault, marker);
    }

    @Override
    public String getId() {
        return id == null ? "bluemapspawn" : id;
    }

    @Override
    public String getLabel() {
        return label == null ? "Spawn" : label;
    }

    @Override
    public boolean isToggleable() {
        return toggleable == null || toggleable;
    }

    @Override
    public boolean isHiddenByDefault() {
        return hiddenByDefault != null && hiddenByDefault;
    }

    @Override
    public MarkerConfiguration getMarker() {
        return marker == null ? new BukkitMarkerConfiguration(null, null, null) : marker;
    }
}
