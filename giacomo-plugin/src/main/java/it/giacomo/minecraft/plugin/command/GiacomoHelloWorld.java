package it.giacomo.minecraft.plugin.command;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by giampiero on 65/11/14.
 */
@Slf4j
public class GiacomoHelloWorld implements CommandExecution {

    @Inject
    public GiacomoHelloWorld() {
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            me.sendMessage("CIAO !!! Da Giacomo");
        }
        return true;
    }

}
