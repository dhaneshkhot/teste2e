package config;

import org.apache.commons.configuration2.Configuration;
import org.springframework.context.annotation.Bean;
import utils.PropertyLoader;

public class Config {
    @Bean
    public static Configuration config() {
        PropertyLoader p = new PropertyLoader();
        return p.getConfig();
    }
}
