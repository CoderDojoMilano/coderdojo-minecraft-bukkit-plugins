package it.federico.minecraft.plugin.command;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Slf4j
public class FedericoHelloWorld implements CommandExecution {

    @Inject
    public FedericoHelloWorld() {
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            me.sendMessage("CIAO !!! Da Federico");
        }
        return true;
    }

}
