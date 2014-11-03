package it.toto.minecraft.plugin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ShowCase extends JavaPlugin {

    public static Logger log = Logger.getLogger("Minecraft");

    public void onLoad() {
        log.info("[HelloWorld] ");
    }

    public void onEnable() {
        log.info("[HelloWorld] ");
    }

    public void onDisable() {
        log.info("[HelloWorld] ");
    }

    public boolean onCommand(CommandSender sender, Command command,
                             String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("hello")) {
            String msg = "[Server] ";
            getServer().broadcastMessage(msg);
            return true;
        }
        return false;
    }
}

