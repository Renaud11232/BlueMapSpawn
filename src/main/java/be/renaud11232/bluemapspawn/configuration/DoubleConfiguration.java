package be.renaud11232.bluemapspawn.configuration;

import org.bukkit.configuration.file.FileConfiguration;

public interface DoubleConfiguration extends Configuration<Double> {
    @Override
    default Double get(FileConfiguration config, FileConfiguration defaultConfig) {
        return config.getDouble(getKey(), defaultConfig.getDouble(getKey(), 0d));
    }
}
