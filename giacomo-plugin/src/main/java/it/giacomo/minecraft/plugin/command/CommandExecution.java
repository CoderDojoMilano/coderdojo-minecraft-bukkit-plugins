package it.giacomo.minecraft.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by giampiero on 05/11/14.
 */
public interface CommandExecution {

    boolean go(CommandSender sender, Command command, Iterable<String> args);
}
