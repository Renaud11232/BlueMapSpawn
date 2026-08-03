package be.renaud11232.bluemapspawn.fabric.configuration;

import be.renaud11232.bluemapspawn.configuration.MarkerConfiguration;
import be.renaud11232.bluemapspawn.configuration.MarkerSetConfiguration;

public class FabricMarkerSetConfiguration implements MarkerSetConfiguration {
    private String id;
    private String label;
    private Boolean toggleable;
    private Boolean default_hidden;
    private FabricMarkerConfiguration marker;

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
        return default_hidden != null && default_hidden;
    }

    @Override
    public MarkerConfiguration getMarker() {
        return marker == null ? new FabricMarkerConfiguration() : marker;
    }
}
