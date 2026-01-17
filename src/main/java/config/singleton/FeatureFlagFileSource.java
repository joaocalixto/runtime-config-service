package config.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FeatureFlagFileSource {
    private static final Logger logger = LoggerFactory.getLogger(FeatureFlagFileSource.class);
    private static final String FLAGS_FILE = "feature-flags.properties";

    public Map<String, Boolean> loadFlags() {
        logger.info("Reading file {}", FLAGS_FILE);
        Properties properties = new Properties();
        Map<String, Boolean> result = new HashMap<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FLAGS_FILE)) {
            if (input == null) {
                logger.warn("Feature flags file not found: {}", FLAGS_FILE);
                return result;
            }
            properties.load(input);
            for (String name : properties.stringPropertyNames()) {
                result.put(name, Boolean.parseBoolean(properties.getProperty(name)));
            }
            logger.info("Parsed {} flags", result.size());
        } catch (IOException e) {
            logger.error("Failed to read feature flags file: {}", FLAGS_FILE, e);
        }

        return result;
    }
}
