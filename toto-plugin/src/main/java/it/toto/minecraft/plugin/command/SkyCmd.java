package it.toto.minecraft.plugin.command;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Entity;
import java.util.List;

/**
 * Created by toto on 05/11/14.
 */
public class SkyCmd implements CommandExecution {

    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
            if (sender instanceof Player) { //(2)
                Player me = (Player)sender; //(3)
                List<Entity> list = me.getNearbyEntities(50,50,50);
                for (Entity target : list) {
                    Location loc = target.getLocation();
                    double y = loc.getY();
                    double z = loc.getZ();
                    double x = loc.getX();
                    Location melocation = me.getLocation();
                    loc.setY(melocation.getY());
                    loc.setZ(melocation.getZ());
                    loc.setX(melocation.getY()+5);
                    target.teleport(loc);
                }
            }
            return true;
    }

}
