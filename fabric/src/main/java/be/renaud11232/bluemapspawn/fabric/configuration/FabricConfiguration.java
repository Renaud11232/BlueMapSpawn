package be.renaud11232.bluemapspawn.fabric.configuration;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.configuration.MarkerSetConfiguration;

public class FabricConfiguration implements Configuration {
    private FabricMarkerSetConfiguration marker_set;

    @Override
    public MarkerSetConfiguration getMarkerSet() {
        return marker_set == null ? new FabricMarkerSetConfiguration() : marker_set;
    }
}
