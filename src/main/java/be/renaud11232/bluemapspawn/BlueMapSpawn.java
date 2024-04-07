package be.renaud11232.bluemapspawn;

import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public final class BlueMapSpawn extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        BlueMapAPI.onEnable(api -> {
            getLogger().info("Reloading configuration file...");
            reloadConfig();
            getLogger().info("Extracting assets to web root...");
            extractAssets(api);
            getLogger().info("Update spawn location...");
            Bukkit.getScheduler().runTask(this, new BlueMapSpawnUpdateTask(this, api));
            getLogger().info("Registering event listeners...");
            getServer().getPluginManager().registerEvents(new BlueMapSpawnEventListener(this, api), this);
        });
    }

    private void extractAssets(BlueMapAPI api) {
        boolean overwrite = getConfig().getBoolean("general.overwrite_assets", true);
        try {
            Path jarPath = Path.of(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            try (FileSystem jar = FileSystems.newFileSystem(jarPath)) {
                Path sourcePath = jar.getPath("assets");
                try (Stream<Path> sourceFiles = Files.walk(sourcePath)) {
                    Path relativeDestinationPath = Path.of("assets").resolve("bluemapspawn");
                    sourceFiles.forEach(sourceFile -> {
                        try {
                            Path relativeDestinationFile = relativeDestinationPath.resolve(sourcePath.relativize(sourceFile).toString());
                            Path destinationFile = api.getWebApp().getWebRoot().resolve(relativeDestinationFile);
                            try {
                                Files.copy(sourceFile, destinationFile, overwrite ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[]{});
                            } catch (DirectoryNotEmptyException ignored) {
                            }
                            if (relativeDestinationFile.toString().endsWith(".css")) {
                                api.getWebApp().registerStyle(relativeDestinationFile.toString());
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
