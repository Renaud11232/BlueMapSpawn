package be.renaud11232.bluemapspawn;

public interface WorldConverter<SOURCE_WORLD_TYPE, TARGET_WORLD_TYPE> {
    TARGET_WORLD_TYPE convert(SOURCE_WORLD_TYPE world);
}
