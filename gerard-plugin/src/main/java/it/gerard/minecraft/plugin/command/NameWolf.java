package it.gerard.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Player;

/**
 * Created by gerard on 08/12/14.
 */
@Slf4j
public class NameWolf implements CommandExecution {


    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            Wolf wolf = me.getWorld().spawn(me.getLocation(), Wolf.class);
            wolf.setCustomName(Iterables.getFirst(args, "Wolf"));
            wolf.setCustomNameVisible(true);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
