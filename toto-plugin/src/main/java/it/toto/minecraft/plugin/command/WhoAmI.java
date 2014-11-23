package it.toto.minecraft.plugin.command;

import com.google.inject.Inject;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Created by toto on 65/11/14.
 */
@Slf4j
public class WhoAmI implements CommandExecution {


    private final DebugLog debugLog;
    private float volume = 0.1f;
    private float pitch = 1.0f;

    @Inject
    public WhoAmI() {
        debugLog = DebugLog.of(log);
    }

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        sender.getServer().broadcastMessage("Modificato !!");
        if (sender instanceof Player) {
            Player me = (Player)sender;
            me.sendMessage(String.format("Hello . Your list name is %s", me.getPlayerListName()));
            me.sendMessage(String.format("Your location is %s", me.getLocation()));

            me.getLocation();

            float exp = me.getExp();
            int food = me.getFoodLevel();
            boolean grounded = ((Entity)me).isOnGround();
            String groundMsg = "";
            if (!grounded) {
                groundMsg = "not "; //(1)
            }
            me.sendMessage("Your experience points are " + exp +
                            ", food is " + food +
                            "\nwater falls from the sky "
                            + "and you are " + groundMsg + "on the ground."
            );
        }
        return true;
    }

}
