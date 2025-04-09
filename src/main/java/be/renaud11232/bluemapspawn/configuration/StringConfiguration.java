package be.renaud11232.bluemapspawn.configuration;

import org.bukkit.configuration.file.FileConfiguration;

public interface StringConfiguration extends Configuration<String> {
    @Override
    default String get(FileConfiguration config, FileConfiguration defaultConfig) {
        return config.getString(getKey(), defaultConfig.getString(getKey(), null));
    }
}
