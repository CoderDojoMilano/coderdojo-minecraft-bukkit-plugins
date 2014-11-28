package it.toto.minecraft.plugin.command;

import lombok.extern.slf4j.Slf4j;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

/**
 * Created by toto on 05/11/14.
 */
@Slf4j
public class LavaVision implements CommandExecution {

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            BlockIterator sightItr = new BlockIterator (me, 100);
            boolean found = false;
            while (sightItr.hasNext() && !found) {
                Block b = sightItr.next();
                me.playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
                if (b.getType() != Material.AIR) {
                    b.setType(Material.LAVA);
                    me.playSound(b.getLocation(), Sound.EXPLODE, 1.0f, 0.5f);
                    found = true;
                }
            }
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
