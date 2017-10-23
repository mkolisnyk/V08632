package com.sample.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;

public class Configuration {
    private Configuration() {
    }

    private static Properties properties;
    public static void load() throws IOException {
        Configuration config = new Configuration();
        properties = new Properties();
        Enumeration<URL> enumerator = config.getClass().getClassLoader().getResources("/");
        while(enumerator.hasMoreElements()) {
            System.out.println(enumerator.nextElement());
        }
        InputStream is = new FileInputStream(new File("config.properties"));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        is,
                        StandardCharsets.UTF_8));
        try {
            properties.load(reader);
        } finally {
            is.close();
            reader.close();
        }
    }
    public static String get(String option) throws IOException {
        if (properties == null) {
            load();
        }
        String value = properties.getProperty(option);
        if (value == null) {
            return "";
        }
        return value;
    }
}
