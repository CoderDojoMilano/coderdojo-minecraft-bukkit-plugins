package it.toto.minecraft.plugin;


import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.LoggerFactory;

@Slf4j
public class ShowCase extends JavaPlugin {

    public void onLoad() {
        log.info("[ShowCase] LOAD ");
    }

    public void onEnable() {

        log.info("[ShowCase] ENABLE ");
    }

    public void onDisable() {
        log.info("[ShowCase] DISABLE ");
    }

    public boolean onCommand(
            CommandSender sender,
            Command command,
            String commandLabel,
            String[] args
    ) {
        log.info("sender {}", sender);
        DebugLog.of(log).debug("command {}", command);
        DebugLog.of(log).debug("commandLabel {}", commandLabel);
        DebugLog.of(log).debug("args {}", args);

        if (commandLabel.equalsIgnoreCase("hello")) {
            String msg = "[Server] ";
            getServer().broadcastMessage(msg);
            return true;
        }
        return false;
    }
}

