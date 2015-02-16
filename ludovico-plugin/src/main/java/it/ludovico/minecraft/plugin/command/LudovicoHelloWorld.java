package it.ludovico.minecraft.plugin.command;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by giampiero on 65/11/14.
 */
@Slf4j
public class LudovicoHelloWorld implements CommandExecution {

    @Inject
    public LudovicoHelloWorld() {
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
            Player me = (Player)sender;
            me.sendMessage("CIAO !!! Da Ludovico");
        return true;
    }

}
