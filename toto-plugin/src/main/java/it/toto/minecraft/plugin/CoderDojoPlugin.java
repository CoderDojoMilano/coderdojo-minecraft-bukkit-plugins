package it.toto.minecraft.plugin;


import com.google.common.base.CaseFormat;
import com.google.common.base.Optional;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import it.toto.minecraft.plugin.command.CommandExecution;
import it.toto.minecraft.plugin.listener.AwsJenkinsClient;
import it.toto.minecraft.plugin.listener.BlockPlaceMiniGame;
import it.toto.minecraft.plugin.tabComplete.OnTabComplete;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CoderDojoPlugin extends JavaPlugin {

    private Injector injector;

    public void onLoad() {

        log.info("[ShowCase] LOAD ");

        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(String.class).annotatedWith(Names.named("aString")).toInstance("AAAAAA");
            }
        });
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(injector.getInstance(BlockPlaceMiniGame.class), this);
        getServer().getPluginManager().registerEvents(injector.getInstance(AwsJenkinsClient.class), this);

        final AwsJenkinsPush awsJenkinsPush = injector.getInstance(AwsJenkinsPush.class);
        awsJenkinsPush.runTaskTimerAsynchronously(this, 20, 1200); //1 secondo, 1 minuto
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
        log.debug("sender {}", sender); //GlowPlayer vs net.glowstone.ConsoleManager$ColoredCommandSender
        log.debug("command {}", command); //org.bukkit.command.PluginCommand

        final Optional<CommandExecution> commandExecutionOpt = instanceFromCommandLabel(commandLabel, CommandExecution.class.getPackage());

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

        final Optional<OnTabComplete> onTabCompleteOptional = instanceFromCommandLabel(alias, OnTabComplete.class.getPackage());

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

        return Optional.fromNullable(commandInstance);
    }
}

