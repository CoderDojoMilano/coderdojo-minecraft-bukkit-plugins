package it.toto.minecraft.plugin.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by toto on 28/11/14.
 */
@Slf4j
public class BlockPlaceMiniGame implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
        log.debug("onBlockPlace {}", ReflectionToStringBuilder.toString(blockPlaceEvent));
    }

}
