package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.bukkit.configuration.BukkitConfiguration;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitBlueMapSpawn extends JavaPlugin {
    private BukkitBlueMapSpawnModule module;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onSpawnChange(SpawnChangeEvent spawnChangeEvent) {
                if (module != null) {
                    module.update(spawnChangeEvent.getWorld());
                }
            }
        }, this);
        BlueMapAPI.onEnable(api -> {
            getLogger().info("Enabling " + getName());
            reloadConfig();
            var configuration = BukkitConfiguration.deserialize(getConfig().getValues(true));
            module = new BukkitBlueMapSpawnModule(api, configuration);
            module.update();
        });
        BlueMapAPI.onDisable(api -> module = null);
    }
}
