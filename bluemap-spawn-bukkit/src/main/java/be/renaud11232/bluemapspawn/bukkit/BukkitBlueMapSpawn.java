package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.bukkit.configuration.BukkitConfiguration;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitBlueMapSpawn extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        BlueMapAPI.onEnable(api -> {
            getLogger().info("Enabling " + getName());
            reloadConfig();
            var configuration = BukkitConfiguration.deserialize(getConfig().getValues(true));
            var module = new BukkitBlueMapSpawnModule(api, configuration);
            module.update();
            getLogger().info("Registering event listeners...");
            getServer().getPluginManager().registerEvents(new BukkitBlueMapSpawnEventListener(module), this);
        });
    }
}
