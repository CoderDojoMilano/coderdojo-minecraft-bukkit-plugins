package it.toto.minecraft.plugin;


import com.google.common.base.CaseFormat;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import it.toto.minecraft.plugin.command.BuildACube;
import it.toto.minecraft.plugin.tabComplete.PlayASound;
import it.toto.minecraft.plugin.util.DebugLog;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ShowCase extends JavaPlugin {

    private Injector injector;
    private DebugLog debugLog;

    public void onLoad() {

        log.info("[ShowCase] LOAD ");

        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(String.class).annotatedWith(Names.named("aString")).toInstance("AAAAAA");
            }
        });

        debugLog = DebugLog.of(log);
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
        debugLog.debug("sender {}", sender); //GlowPlayer vs net.glowstone.ConsoleManager$ColoredCommandSender
        debugLog.debug("command {}", command); //org.bukkit.command.PluginCommand
        debugLog.debug("commandLabel {}", commandLabel);
        debugLog.debug("args {}", args);

        final Optional<CommandExecution> commandExecutionOpt = instanceFromCommandLabel(commandLabel, BuildACube.class.getPackage());

        if (commandExecutionOpt.isPresent()) {
            return commandExecutionOpt.get().go(sender, command, Arrays.asList(args));
        } else {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String alias,
            String[] args) {

        final Optional<OnTabComplete> onTabCompleteOptional = instanceFromCommandLabel(alias, PlayASound.class.getPackage());

        if (onTabCompleteOptional.isPresent()) {
            return onTabCompleteOptional.get().go(sender, command, Arrays.asList(args));
        } else {
            return super.onTabComplete(sender ,command, alias, args);
        }

    }

    private <T> Optional<T> instanceFromCommandLabel(String commandLabel, Package p) {
        String className = p.getName() + "." + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, commandLabel);
        T commandInstance = null;

        try {
            commandInstance = (T) injector.getInstance(Class.<T>forName(className));
        } catch (Exception e) {
            log.error("can't build command execution class ",e);
        }

        return Optional.ofNullable(commandInstance);
    }
}

