package it.toto.minecraft.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by toto on 05/11/14.
 */
public interface CommandExecution {

    boolean go(CommandSender sender, Command command, String[] args);
}
