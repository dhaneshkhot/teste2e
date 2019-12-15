package utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class PropertyLoader {
    Parameters params = new Parameters();
    Configuration config;
    FileBasedConfigurationBuilder<FileBasedConfiguration> builder;

    public PropertyLoader(){
        builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(params.properties()
                        .setFileName("int.properties"));

        try {
            config = builder.getConfiguration();
        } catch(ConfigurationException cex) {
            System.out.println("Error no file: " + cex.getMessage());
        }
    }

    public Configuration getConfig() {
        return config;
    }
}
