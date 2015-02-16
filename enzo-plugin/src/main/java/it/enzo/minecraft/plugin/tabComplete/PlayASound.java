package it.enzo.minecraft.plugin.tabComplete;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by toto on 06/11/14.
 */
public class PlayASound implements OnTabComplete {

    @Override
    public List<String> go(CommandSender sender, Command command, Iterable<String> args) {
        List<String> result = Lists.newArrayList();
        final String containsIgnoreCase = Iterables.getFirst(args, StringUtils.EMPTY);
        Sound[] sounds = Sound.values();
        for (Sound sound : sounds) {
            if (StringUtils.containsIgnoreCase(sound.name(), containsIgnoreCase)) {
                result.add(sound.name());
            }
        }

        return result;
    }
}
