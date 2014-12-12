package it.gerard.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Player;

/**
 * Created by gerard on 12/12/14.
 */
@Slf4j
public class NameSheep implements CommandExecution {


    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            Sheep sheep = me.getWorld().spawn(me.getLocation(), Sheep.class);
            sheep.setCustomName(Iterables.getFirst(args, "Sheep"));
            sheep.setCustomNameVisible(true);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
