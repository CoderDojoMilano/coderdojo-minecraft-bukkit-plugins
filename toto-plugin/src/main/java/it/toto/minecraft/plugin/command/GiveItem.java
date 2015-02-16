package it.toto.minecraft.plugin.command;

import com.google.common.collect.Iterables;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by toto on 14/02/15.
 */
public class GiveItem implements CommandExecution {

    @Override
    public boolean go(CommandSender sender, Command command, Iterable<String> args) {

        Player player = (Player) sender;

        PlayerInventory inventory = player.getInventory();

        Integer times = Integer.valueOf(Iterables.get(args, 1));

        for (int i=times;i!=0;i--) {
            inventory.addItem(new ItemStack(Material.valueOf(Iterables.getFirst(args, "DIRT"))));
        }
        return true;
    }
}
