package be.renaud11232.bluemapspawn;

import be.renaud11232.bluemapspawn.io.AssetExtractor;
import be.renaud11232.bluemapspawn.io.ConfigurationLoader;
import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Path;

@SuppressWarnings("unused")
public final class BlueMapSpawn extends JavaPlugin {
    private FileConfiguration defaultConfig;

    @Override
    public void onEnable() {
        loadDefaultConfig();
        saveDefaultConfig();
        BlueMapAPI.onEnable(api -> {
            getLogger().info("Reloading configuration file...");
            reloadConfig();
            getLogger().info("Preparing files in the web root...");
            prepareFiles(api);
            getLogger().info("Scheduling update task...");
            Bukkit.getScheduler().runTask(this, new BlueMapSpawnUpdateTask(this, api));
            getLogger().info("Registering event listeners...");
            getServer().getPluginManager().registerEvents(new BlueMapSpawnEventListener(this, api), this);
        });
        BlueMapAPI.onDisable(api -> {
            getLogger().info("Cancelling all tasks...");
            Bukkit.getScheduler().cancelTasks(this);
        });
    }

    private void loadDefaultConfig() {
        try {
            defaultConfig = new ConfigurationLoader(getResource("config.yml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileConfiguration getDefaultConfig() {
        return defaultConfig;
    }

    private void prepareFiles(BlueMapAPI api) {
        boolean overwrite = BlueMapSpawnConfiguration.General.OVERWRITE_ASSETS.get(getConfig(), defaultConfig);
        Path relativeDestination = Path.of("assets").resolve("bluemapspawn");
        Path destination = api.getWebApp().getWebRoot().relativize(relativeDestination);
        try (AssetExtractor assetExtractor = new AssetExtractor("assets", destination)) {
            assetExtractor.extract(overwrite);
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
