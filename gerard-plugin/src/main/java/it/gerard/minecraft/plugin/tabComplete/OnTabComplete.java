package it.gerard.minecraft.plugin.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by giacomo on 06/11/14.
 */
public interface OnTabComplete {

    List<String> go(CommandSender sender, Command command, Iterable<String> args);
}
