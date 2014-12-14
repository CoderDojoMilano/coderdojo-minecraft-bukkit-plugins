package it.toto.minecraft.plugin.listener;

import com.google.inject.Inject;
import it.toto.minecraft.plugin.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by toto on 14/12/14.
 */
@Slf4j
public class AwsJenkinsClient implements Listener {

    private PlayerRepository playerRepository;

    @Inject
    public void AwsJenkinsClient(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent playerJoinEvent) {
        log.info("PlayerJoinEvent {}", playerJoinEvent.getPlayer());
        playerRepository.join(playerJoinEvent.getPlayer());
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent playerQuitEvent) {
        log.info("PlayerQuitEvent {}", playerQuitEvent.getPlayer());
        playerRepository.quit(playerQuitEvent.getPlayer());
    }
}
