package be.renaud11232.bluemapspawn.fabric;

import be.renaud11232.bluemapspawn.configuration.Configuration;
import be.renaud11232.bluemapspawn.fabric.configuration.FabricConfiguration;
import be.renaud11232.bluemapspawn.fabric.event.RespawnDataSetCallback;
import be.renaud11232.bluemapspawn.fabric.mod.FabricModDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bluecolored.bluemap.api.BlueMapAPI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FabricBlueMapSpawn implements ModInitializer {
    private static MinecraftServer SERVER;

    private final Gson gson;
    private final FabricModDefinition modDefinition;
    private final Logger logger;
    private Configuration configuration;
    private FabricBlueMapSpawnModule module;

    public FabricBlueMapSpawn() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new InputStreamReader(getJarResource("/fabric.mod.json"))) {
            modDefinition = gson.fromJson(reader, FabricModDefinition.class);
            logger = LoggerFactory.getLogger(modDefinition.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<MinecraftServer> getServer() {
        return Optional.ofNullable(SERVER);
    }

    @Override
    public void onInitialize() {
        saveDefaultConfig();
        ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER = server);
        RespawnDataSetCallback.EVENT.register(level -> {
            if (module != null) {
                module.update(level);
            }
            return InteractionResult.SUCCESS;
        });
        BlueMapAPI.onEnable(api -> {
            logger.info("Enabling {}", modDefinition.getName());
            reloadConfig();
            module = new FabricBlueMapSpawnModule(api, configuration);
            module.update();
        });
        BlueMapAPI.onDisable(_ -> module = null);
    }

    private InputStream getJarResource(String name) {
        try (var jar = FileSystems.newFileSystem(Path.of(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()))) {
            var source = jar.getPath(name);
            byte[] data = Files.readAllBytes(source);
            return new ByteArrayInputStream(data);
        } catch (IOException | URISyntaxException e) {
            return null;
        }
    }

    private void saveDefaultConfig() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            Path configDirectory = configPath.getParent();
            try {
                if (!Files.exists(configDirectory)) {
                    Files.createDirectories(configDirectory);
                }
                Files.copy(getJarResource("/config.json"), configPath);
            } catch (IOException e) {
                logger.error("Unable to save default configuration file.", e);
            }
        }
    }

    private Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir().resolve(modDefinition.getId());
    }

    private Path getConfigPath() {
        return getConfigDirectory().resolve("config.json");
    }

    private void reloadConfig() {
        try (Reader reader = Files.newBufferedReader(getConfigPath())) {
            configuration = gson.fromJson(reader, FabricConfiguration.class);
        } catch (IOException e) {
            logger.error("Failed to reload config", e);
        }
    }
}
