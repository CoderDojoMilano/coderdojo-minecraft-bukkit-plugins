package it.gerard.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

/**
 * Created by gerard on 08/12/14.
 */
@Slf4j
public class NameHorse implements CommandExecution {


    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            Horse horse = me.getWorld().spawn(me.getLocation(), Horse.class);
            horse.setCustomName(Iterables.getFirst(args, "Horse"));
            horse.setCustomNameVisible(true);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
