package com.gts.fb.util;

import java.util.Properties;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class EnvSupportProperties extends Properties {
    @Override
    public String getProperty(String key) {
        String property = super.getProperty(key);
        if (property == null || property.isEmpty()) {
            property = System.getenv().get(key);
        }
        return property;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String property = super.getProperty(key);
        if (property == null || property.isEmpty()) {
            property = System.getenv().get(key);
        }
        if (property == null || property.isEmpty()) {
            property = defaultValue;
        }
        return property;
    }
}
