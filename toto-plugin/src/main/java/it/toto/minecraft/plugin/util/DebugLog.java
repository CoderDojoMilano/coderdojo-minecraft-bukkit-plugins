package it.toto.minecraft.plugin.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.slf4j.Logger;

/**
 * Created by toto on 04/11/14.
 */
@RequiredArgsConstructor (staticName = "of")
public class DebugLog {

    @NonNull
    private Logger log;

    public void debug(String s, Object... arguments) {
        log.info("DEBUG " + s, arguments);
    }
}
