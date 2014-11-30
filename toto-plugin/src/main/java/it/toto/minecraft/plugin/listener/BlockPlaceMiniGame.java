package it.toto.minecraft.plugin.listener;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;


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

    private final Material[] sequence = new Material[]{
        Material.SAND,
        Material.DIRT,
        Material.WOOD
    };

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
        //log.debug("onBlockPlace {}", ReflectionToStringBuilder.toString(blockPlaceEvent));
        final Block blockPlaced = blockPlaceEvent.getBlockPlaced();
        if (blockPlaced != null) {
            log.info("BlockPlaced {}", blockPlaced);

            final Material blockPlacedType = blockPlaced.getType();
            if (blockPlacedType == sequence[0]) {
                Optional<BlockFace> nearestGravelOptional = findNearest(blockPlaced, sequence[1]);

                if (nearestGravelOptional.isPresent()) {

                    final BlockFace blockFace = nearestGravelOptional.get();

                    int index = 0;

                    Block block = blockPlaced;

                    List<Block> blocks = Lists.newArrayList();

                    boolean found = true;

                    while (index < sequence.length && found) {
                        log.info("relative of {} index {} blockface {}", block, index, blockFace);
                        if (block.getType() != sequence[index]) {
                            found = false;
                        } else {
                            blocks.add(block);
                        }
                        index ++;
                        block = block.getRelative(blockFace, 1);
                    }

                    if (found) {
                        log.info("sequence ok !!!!");

                        final Player player = blockPlaceEvent.getPlayer();

                        for (Block b : blocks) {
                            player.playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
                            b.setType(Material.AIR);
                        }

                        player.getServer().broadcastMessage(player.getDisplayName() + " a completato la sequenza !");

                    } else {
                        log.info("wong sequence of {} index {} blockface {}", block, index, blockFace);
                    }

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
