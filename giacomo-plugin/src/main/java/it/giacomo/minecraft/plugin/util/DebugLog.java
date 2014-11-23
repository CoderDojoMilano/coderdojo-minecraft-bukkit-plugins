package it.giacomo.minecraft.plugin.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

/**
 * Created by giampiero on 04/11/14.
 */
@RequiredArgsConstructor (staticName = "of")
public class DebugLog {

    @NonNull
    private Logger log;

    public void debug(String s, Object... arguments) {
        log.info("DEBUG " + s, arguments);
    }
}
