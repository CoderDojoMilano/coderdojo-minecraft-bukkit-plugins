package it.toto.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;

public class HelloToto implements CommandExecution {

    private static Integer conto = 0;

    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        Player me = (Player)sender;

        conto = conto + 1;

        if (StringUtils.containsIgnoreCase(me.getDisplayName(), "toto")) {
            Location miaLocation = me.getLocation();

            Location oggettoLocation = new Location(me.getWorld(), 0,0,0);

            oggettoLocation.setX(miaLocation.getX() + 5);
            oggettoLocation.setZ(miaLocation.getZ() + 5);
            oggettoLocation.setY(miaLocation.getY() + 2);
            me.getWorld().getBlockAt(oggettoLocation).setType(Material.WOOD);

        }

        if (conto == 10) {
            me.sendMessage("BRAVO ECCO LA TUA MUCCA !!!");

            Cow cow = me.getWorld().spawn(me.getLocation(), Cow.class);
            cow.setCustomName(me.getDisplayName());
            cow.setCustomNameVisible(true);

        } else if (conto == 20) {
            conto = 1;

            me.sendMessage("TRASFORMAZIONE !!!!");

            Location miaLocation = me.getLocation();

            Location oggettoLocation = new Location(me.getWorld(), 0,0,0);

            oggettoLocation.setX(miaLocation.getX() + 5);
            oggettoLocation.setZ(miaLocation.getZ() + 5);
            oggettoLocation.setY(miaLocation.getY() + 2);
            me.getWorld().getBlockAt(oggettoLocation).setType(Material.WOOD);

        } else {
            me.sendMessage("CIAO MONDO !!!!!! Sei Arrivato a " + conto);
        }
        return true;
    }

}
