package it.toto.minecraft.plugin.listener;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


/**
 * Created by toto on 28/11/14.
 */
@Slf4j
public class BlockPlaceMiniGame implements Listener {

    private final BlockFace[] directions = new BlockFace[]{
        BlockFace.NORTH,
        BlockFace.EAST,
        BlockFace.SOUTH,
        BlockFace.WEST,
    };

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
        log.debug("onBlockPlace {}", ReflectionToStringBuilder.toString(blockPlaceEvent));
        final Block blockPlaced = blockPlaceEvent.getBlockPlaced();
        if (blockPlaced != null) {
            log.info("BlockPlaced {}", blockPlaced);
            final Location blockPlacedLocation = blockPlaced.getLocation();

            log.debug("north {}", blockPlaced.getRelative(BlockFace.NORTH, 1));
            log.debug("east {}", blockPlaced.getRelative(BlockFace.EAST, 1));
            log.debug("south {}", blockPlaced.getRelative(BlockFace.SOUTH, 1));
            log.debug("west {}", blockPlaced.getRelative(BlockFace.WEST, 1));

            final Material blockPlacedType = blockPlaced.getType();
            if (blockPlacedType == Material.SAND) {
                //sabbia. Controllo alla sua destra allora
                //http://jd.bukkit.org/rb/apidocs/src-html/org/bukkit/block/BlockFace.html#line.8
                
                Optional<BlockFace> nearestGravelOptional = findNearest(blockPlaced, Material.GRAVEL);

                if (nearestGravelOptional.isPresent()) {
                    
                }

            }
        }
    }

    private Optional<BlockFace> findNearest(Block block, Material material) {
        for (BlockFace direction : directions) {
            if (block.getRelative(direction,1).getType() == material) {
                return Optional.of(direction);
            }
        }
        return Optional.absent();
    }

}
