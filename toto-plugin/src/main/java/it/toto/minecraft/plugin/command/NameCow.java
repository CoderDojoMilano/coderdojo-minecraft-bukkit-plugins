package it.toto.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;

/**
 * Created by toto on 65/11/14.
 */
@Slf4j
public class NameCow implements CommandExecution {


    private final DebugLog debugLog;

    @Inject
    public NameCow() {
        debugLog = DebugLog.of(log);
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            Player me = (Player)sender;
            Cow cow = me.getWorld().spawn(me.getLocation(), Cow.class);
            cow.setCustomName(Iterables.getFirst(args, "Cow"));
            cow.setCustomNameVisible(true);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
