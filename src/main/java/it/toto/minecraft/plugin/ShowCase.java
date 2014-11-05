package it.toto.minecraft.plugin;


import com.google.common.base.CaseFormat;
import it.toto.minecraft.plugin.command.BuildACube;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
        final DebugLog debugLog = DebugLog.of(log);

        debugLog.debug("sender {}", sender); //GlowPlayer vs net.glowstone.ConsoleManager$ColoredCommandSender
        debugLog.debug("command {}", command); //org.bukkit.command.PluginCommand
        debugLog.debug("commandLabel {}", commandLabel);
        debugLog.debug("args {}", args);

        final String commandClassName = BuildACube.class.getPackage().getName() + "." + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, commandLabel);

        log.info("execute command with class name {}", commandClassName);

        CommandExecution commandExecution = null;

        try {
            commandExecution = (CommandExecution) Class.forName(commandClassName).newInstance();
        } catch (Exception e) {
            log.error("can't build command execution class ",e);
        }

        if (commandExecution != null) {
            return commandExecution.go(sender, command, args);
        } else {
            return false;
        }
    }
}

