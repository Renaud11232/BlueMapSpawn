package be.renaud11232.bluemapspawn.configuration;

public interface MarkerSetConfiguration {
    String getId();

    String getLabel();

    boolean isToggleable();

    boolean isHiddenByDefault();

    MarkerConfiguration getMarker();
}
