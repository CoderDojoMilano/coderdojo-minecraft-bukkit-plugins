package it.toto.minecraft.plugin;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.FileUtil;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        if (playerRepository.isNotEmpty()) {
            log.info("push {}", playerRepository.info());

            String auth = StringUtils.EMPTY;
            try {
                FileReader fileReader = new FileReader("/home/ubuntu/auth.properties");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                auth = bufferedReader.readLine();
            } catch (FileNotFoundException e) {
                log.error(null, e);
            } catch (IOException e) {
                log.error(null,e);
            }

            if (StringUtils.isNotBlank(auth)) {
                final Response post = ClientBuilder.newClient()
                        .target("http://api.awsjenkins.skillbill.net")
                        .path("alives-servers")
                        .request(MediaType.APPLICATION_JSON)
                        .header("Authorization", auth)
                        .post(Entity.json(null));
                log.debug("ping response {}", post);
            } else {
                log.warn("no authorization config");
            }
        } else {
            log.info("skip push... no players");
        }
    }
}
