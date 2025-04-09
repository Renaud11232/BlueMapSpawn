package be.renaud11232.bluemapspawn.configuration;

import org.bukkit.configuration.file.FileConfiguration;

public interface BooleanConfiguration extends Configuration<Boolean> {
    @Override
    default Boolean get(FileConfiguration config, FileConfiguration defaultConfig) {
        return config.getBoolean(getKey(), defaultConfig.getBoolean(getKey(), false));
    }
}
