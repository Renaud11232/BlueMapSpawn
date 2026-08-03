package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.bukkit.configuration.BukkitConfiguration;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitBlueMapSpawn extends JavaPlugin {
    private BukkitBlueMapSpawnEventListener eventListener;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        eventListener = new BukkitBlueMapSpawnEventListener();
        getServer().getPluginManager().registerEvents(eventListener, this);
        BlueMapAPI.onEnable(api -> {
            getLogger().info("Enabling " + getName());
            reloadConfig();
            var configuration = BukkitConfiguration.deserialize(getConfig().getValues(true));
            var module = new BukkitBlueMapSpawnModule(api, configuration);
            eventListener.setModule(module);
            module.update();
        });
        BlueMapAPI.onDisable(api -> eventListener.setModule(null));
    }
}
