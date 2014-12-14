package it.toto.minecraft.plugin;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Created by toto on 14/12/14.
 */
@Singleton
@Slf4j
public class PlayerRepository {

    private Map<String, Player> players = Maps.newConcurrentMap();

    public void quit(Player player) {
        players.remove(player.getDisplayName());
    }

    public void join(Player player) {
        final String displayName = player.getDisplayName();
        players.put(player.getDisplayName(), player);
        log.debug("join {}, {}", displayName, info());
    }

    public String info() {
        String info = StringUtils.EMPTY;
        for (String s : players.keySet()) {
            info = info + s;
        }
        return info;
    }

}
