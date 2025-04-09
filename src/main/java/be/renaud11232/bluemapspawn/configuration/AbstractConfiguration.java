package be.renaud11232.bluemapspawn.configuration;

public abstract class AbstractConfiguration<T> implements Configuration<T> {
    private final String key;

    public AbstractConfiguration(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
