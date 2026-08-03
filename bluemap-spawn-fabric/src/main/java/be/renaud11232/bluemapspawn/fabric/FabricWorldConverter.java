package be.renaud11232.bluemapspawn.fabric;

import be.renaud11232.bluemapspawn.WorldConverter;
import be.renaud11232.bluemapspawn.world.World;
import net.minecraft.server.level.ServerLevel;

public class FabricWorldConverter implements WorldConverter<ServerLevel, World> {
    @Override
    public World convert(ServerLevel world) {
        return new FabricWorld(world);
    }
}
