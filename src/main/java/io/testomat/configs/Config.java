package io.testomat.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.testomat.configs.dto.ConfigurationDTO;
import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class Config {

    public ConfigurationDTO get() {
        ObjectMapper objectMapper = new ObjectMapper();

        var path = System.getProperty("user.dir");
        var targetEnvFile = path + "/src/main/resources/envs/" + Envs.get() + ".json";

        try {
            return objectMapper.readValue(
                    new File(targetEnvFile),
                    ConfigurationDTO.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to read configuration", e);
        }
    }




 /*   public ConfigurationDTO get() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    Config.class.getResourceAsStream("envs/" + Envs.BETA + ".json"),
                    ConfigurationDTO.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to read configuration", e);
        }
    }*/

}

