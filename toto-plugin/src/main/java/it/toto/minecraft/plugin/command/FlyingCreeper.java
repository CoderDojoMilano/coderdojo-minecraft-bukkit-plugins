package it.toto.minecraft.plugin.command;

import lombok.extern.slf4j.Slf4j;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by toto on 05/11/14.
 */
@Slf4j
public class FlyingCreeper implements CommandExecution {


    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {
        if (sender instanceof Player) {
            //il giocatore che ha lanciato il comando
            Player player = (Player) sender;
            //la posizione del giocatore
            Location loc = player.getLocation();
            //sopra la testa del giocatore di 5 blocchi
            loc.setY(loc.getY() + 5);
            //spawn di un pipistrello
            Bat bat = player.getWorld().spawn(loc, Bat.class);
            //spawn di un creeper
            Creeper creeper = player.getWorld().spawn(loc, Creeper.class);
            //il creepar è "passeggero" del pipistrello
            bat.setPassenger(creeper);
            //efftto pozione di invisibilità massima (con valore 1)
            PotionEffect potion = new PotionEffect(
                    PotionEffectType.INVISIBILITY,
                    Integer.MAX_VALUE,
                    1);
            //pozione al pipistrello
            bat.addPotionEffect(potion);
            return true;
        } else {
            log.warn("command is avaliable on players");
            return false;
        }

    }

}
