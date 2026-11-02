package be.renaud11232.bluemapspawn.fabric.configuration;

import be.renaud11232.bluemapspawn.configuration.MarkerConfiguration;

public class FabricMarkerConfiguration implements MarkerConfiguration {
    private String id;
    private String label;
    private Integer max_distance;

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
        return max_distance == null ? 1000 : max_distance;
    }
}
