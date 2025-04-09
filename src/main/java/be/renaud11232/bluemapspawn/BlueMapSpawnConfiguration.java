package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.configuration.SimpleBooleanConfiguration;
import be.renaud11232.bluemapspawn.configuration.SimpleDoubleConfiguration;
import be.renaud11232.bluemapspawn.configuration.SimpleStringConfiguration;

public final class BlueMapSpawnConfiguration {
    public static final class General {
        public static final Configuration<Boolean> OVERWRITE_ASSETS = new SimpleBooleanConfiguration("general.overwrite_assets");
    }
    public static final class MarkerSet {
        public static final Configuration<String> KEY = new SimpleStringConfiguration("marker_set.key");
        public static final Configuration<String> LABEL = new SimpleStringConfiguration("marker_set.label");
        public static final Configuration<Boolean> TOGGLEABLE = new SimpleBooleanConfiguration("marker_set.toggleable");
        public static final Configuration<Boolean> DEFAULT_HIDDEN = new SimpleBooleanConfiguration("marker_set.default_hidden");
        public static final class Marker {
            public static final Configuration<String> KEY = new SimpleStringConfiguration("marker_set.marker.key");
            public static final Configuration<String> LABEL = new SimpleStringConfiguration("marker_set.marker.label");
            public static final Configuration<Double> MAX_DISTANCE = new SimpleDoubleConfiguration("marker_set.marker.max_distance");
        }
    }
}
