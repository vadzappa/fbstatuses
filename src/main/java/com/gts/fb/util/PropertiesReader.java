package com.gts.fb.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class PropertiesReader {
    public static Properties readFromResource(Class clazz, String resourcePath) {
        Properties properties = new Properties();
        try {
            properties.load(clazz.getClassLoader().getResourceAsStream(resourcePath));
        } catch (IOException e) {
            Logger logger = Logger.getLogger(clazz.getName());
            logger.log(Level.WARNING, "Unable to read resource", e);
        }

        return properties;
    }
}
