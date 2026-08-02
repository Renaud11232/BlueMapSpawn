package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.configuration.MarkerSetConfiguration;
import be.renaud11232.bluemapspawn.io.AssetExtractor;
import be.renaud11232.bluemapspawn.world.World;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.markers.MarkerSet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public abstract class BlueMapSpawnModule<SOURCE_WORLD_TYPE> {
    private final BlueMapAPI api;
    private final Configuration configuration;
    private final SpawnMarkerBuilder markerBuilder;
    private final WorldConverter<SOURCE_WORLD_TYPE, World> worldConverter;

    protected BlueMapSpawnModule(BlueMapAPI api, Configuration configuration, WorldConverter<SOURCE_WORLD_TYPE, World> worldConverter) {
        this.api = api;
        this.configuration = configuration;
        this.markerBuilder = new SpawnMarkerBuilder(configuration);
        this.worldConverter = worldConverter;
        extractAssets();
    }

    protected abstract Collection<SOURCE_WORLD_TYPE> getWorlds();

    public void update(SOURCE_WORLD_TYPE world) {
        World convertedWorld = worldConverter.convert(world);
        api.getWorld(world).ifPresent(blueMapWorld -> blueMapWorld.getMaps().forEach(map -> {
            MarkerSet markerSet = map.getMarkerSets().computeIfAbsent(configuration.getMarkerSet().getId(), id -> buildMarkerSet(configuration.getMarkerSet()));
            markerSet.getMarkers().clear();
            markerSet.getMarkers().put(configuration.getMarkerSet().getMarker().getId(), markerBuilder.build(convertedWorld));
        }));
    }

    public void update() {
        getWorlds().forEach(this::update);
    }

    private MarkerSet buildMarkerSet(MarkerSetConfiguration configuration) {
        return MarkerSet.builder()
                .label(configuration.getLabel())
                .toggleable(configuration.isToggleable())
                .defaultHidden(configuration.isHiddenByDefault())
                .build();
    }

    private void extractAssets() {
        Path relativeDestination = Path.of("assets").resolve("bluemap-spawn");
        Path destination = api.getWebApp().getWebRoot().resolve(relativeDestination);
        try (AssetExtractor assetExtractor = new AssetExtractor(getClass(), "assets", destination)) {
            assetExtractor.extract();
            assetExtractor.listDestinationFiles()
                    .stream()
                    .filter(f -> f.toString().toLowerCase().endsWith(".css"))
                    .map(relativeDestination::resolve)
                    .map(Path::toString)
                    .forEach(f -> api.getWebApp().registerStyle(f));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
