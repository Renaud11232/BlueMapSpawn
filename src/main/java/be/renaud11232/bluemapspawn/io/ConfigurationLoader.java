package be.renaud11232.bluemapspawn.io;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ConfigurationLoader {
    private final InputStream in;

    public ConfigurationLoader(InputStream in) {
        this.in = Objects.requireNonNull(in);
    }

    public FileConfiguration load() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return YamlConfiguration.loadConfiguration(reader);
        }
    }
}
