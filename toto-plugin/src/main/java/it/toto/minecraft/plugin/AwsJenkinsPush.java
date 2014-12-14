package it.toto.minecraft.plugin;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by toto on 14/12/14.
 */
@Slf4j
public class AwsJenkinsPush extends BukkitRunnable {

    private final PlayerRepository playerRepository;

    @Inject
    public AwsJenkinsPush(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run() {
        log.info("push {}", playerRepository.info());
    }
}
