package be.renaud11232.bluemapspawn.bukkit;

import be.renaud11232.bluemapspawn.WorldConverter;
import org.bukkit.World;

public class BukkitWorldConverter implements WorldConverter<World, be.renaud11232.bluemapspawn.world.World> {
    @Override
    public be.renaud11232.bluemapspawn.world.World convert(World world) {
        return new BukkitWorld(world);
    }
}
