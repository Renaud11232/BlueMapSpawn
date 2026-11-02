package be.renaud11232.bluemapspawn.fabric;

import be.renaud11232.bluemapspawn.BlueMapSpawnModule;
import be.renaud11232.bluemapspawn.configuration.Configuration;
import de.bluecolored.bluemap.api.BlueMapAPI;
import net.minecraft.server.level.ServerLevel;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

public class FabricBlueMapSpawnModule extends BlueMapSpawnModule<ServerLevel> {
    protected FabricBlueMapSpawnModule(BlueMapAPI api, Configuration configuration) {
        super(api, configuration, new FabricWorldConverter());
    }

    @Override
    protected Collection<ServerLevel> getWorlds() {
        return FabricBlueMapSpawn.getServer()
                .map(s -> StreamSupport.stream(s.getAllLevels().spliterator(), false).toList())
                .orElseGet(List::of);
    }
}
