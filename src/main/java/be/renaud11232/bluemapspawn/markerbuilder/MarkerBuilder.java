package be.renaud11232.bluemapspawn.markerbuilder;

import de.bluecolored.bluemap.api.markers.POIMarker;

public interface MarkerBuilder<T> {
    POIMarker build(T object);
}
