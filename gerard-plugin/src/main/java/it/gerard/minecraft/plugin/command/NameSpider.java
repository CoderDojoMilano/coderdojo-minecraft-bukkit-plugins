package it.gerard.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Player;

/**
 * Created by gerard on 08/12/14.
 */
@Slf4j
public class NameSpider implements CommandExecution {


    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            Spider spider = me.getWorld().spawn(me.getLocation(), Spider.class);
            spider.setCustomName(Iterables.getFirst(args, "Spider"));
            spider.setCustomNameVisible(true);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
