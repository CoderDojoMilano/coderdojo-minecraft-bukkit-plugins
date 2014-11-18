package it.toto.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import it.toto.minecraft.plugin.CommandExecution;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by toto on 05/11/14.
 */
@Slf4j
public class BuildACube implements CommandExecution {


    Location origin = null;

    @Inject
    public BuildACube(@Named("aString") String aString) {
        DebugLog.of(log).debug("aString {}", aString);
    }

    // Create a 3D cube, offset from the saved "origin"
    void makeCube(int offsetX, int offsetY, int offsetZ, int width, int height, Material what) {
        int i, j, k;
        Location loc = new Location(origin.getWorld(), 0,0,0);

        // Base is i X j, k goes up for height
        for (i=0; i< width; i++) {
            for (j=0; j<width; j++) {
                for (k=0; k< height; k++) {
                    loc.setX(origin.getX() + offsetX + i);
                    loc.setZ(origin.getZ() + offsetZ + j);
                    loc.setY(origin.getY() + offsetY + k);
                    origin.getWorld().getBlockAt(loc).setType(what);
                }
            }
        }
    }

    // Called by reader's code
    // Minimum of 5 X 5
    @SuppressWarnings("deprecation")
    void buildMyHouse(int width, int height) {

        // Floor the dimensions at no less than 5 X 5
        if (width < 5) {
            width = 5;
        }
        if (height < 5) {
            height = 5;
        }

        // Center the first house on the player
        origin.setY(origin.getY() - 1);
        origin.setZ(origin.getZ() - (width/2));
        origin.setX(origin.getX() - (width/2));

        // Set the whole area to wood
        makeCube(0,0,0,width, height, Material.WOOD);
        // Set the inside of the cube to air
        makeCube(1,1,1,width-2, height-2, Material.AIR);

        // Move over to make next house if called in a loop.
        origin.setX(origin.getX() + width);
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player) sender;
            // Put your code after this line:
            origin = me.getLocation();

            int width = 6;
            int height = 6;

            if (    Iterables.size(args) > 1 &&
                    NumberUtils.isDigits(Iterables.get(args, 0)) &&
                    NumberUtils.isDigits(Iterables.get(args, 1))
                    ) {
                width = Integer.valueOf(Iterables.get(args, 0));
                height = Integer.valueOf(Iterables.get(args, 1));
            }

            buildMyHouse(width, height);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
