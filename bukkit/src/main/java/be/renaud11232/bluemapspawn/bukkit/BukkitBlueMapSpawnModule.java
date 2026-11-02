package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.BlueMapSpawnModule;
import be.renaud11232.bluemapspawn.configuration.Configuration;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Collection;

public class BukkitBlueMapSpawnModule extends BlueMapSpawnModule<World> {
    public BukkitBlueMapSpawnModule(BlueMapAPI api, Configuration configuration) {
        super(api, configuration, new BukkitWorldConverter());
    }

    @Override
    protected Collection<World> getWorlds() {
        return Bukkit.getWorlds();
    }
}
