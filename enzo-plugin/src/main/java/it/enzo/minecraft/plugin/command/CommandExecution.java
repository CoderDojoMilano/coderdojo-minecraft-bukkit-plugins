package it.enzo.minecraft.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by toto on 05/11/14.
 */
public interface CommandExecution {

    boolean go(CommandSender sender, Command command, Iterable<String> args);
}
