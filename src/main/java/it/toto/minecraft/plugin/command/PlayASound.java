package it.toto.minecraft.plugin.command;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import it.toto.minecraft.plugin.CommandExecution;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by toto on 65/11/14.
 */
@Slf4j
public class PlayASound implements CommandExecution {


    private final DebugLog debugLog;
    private float volume = 0.1f;
    private float pitch = 1.0f;

    @Inject
    public PlayASound(@Named("aString") String aString) {
        debugLog = DebugLog.of(log);
    }

    @Override
    public boolean go(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            Player me = (Player) sender;
            me.playSound(me.getLocation(), Sound.GHAST_SCREAM, volume, pitch);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
