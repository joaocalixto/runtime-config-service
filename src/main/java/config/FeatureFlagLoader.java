package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FeatureFlagLoader {
    private static final Logger logger = LoggerFactory.getLogger(FeatureFlagLoader.class);
    private static final String FLAGS_FILE = "feature-flags.properties";

    private final Map<String, Boolean> flags = new HashMap<>();

    public FeatureFlagLoader() {
        logger.info("Creating FeatureFlagLoader instance (id={})", System.identityHashCode(this));
        loadFlagsFromFile();
    }

    public boolean isEnabled(String flagName) {
        return flags.getOrDefault(flagName, false);
    }

    private void loadFlagsFromFile() {
        logger.info("Loading feature flags from {} (loader id={})", FLAGS_FILE, System.identityHashCode(this));
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FLAGS_FILE)) {
            if (input == null) {
                logger.warn("Feature flags file not found: {} (loader id={})", FLAGS_FILE, System.identityHashCode(this));
                return;
            }
            properties.load(input);
            for (String name : properties.stringPropertyNames()) {
                flags.put(name, Boolean.parseBoolean(properties.getProperty(name)));
            }
        } catch (IOException e) {
            logger.error("Failed to read feature flags file: {} (loader id={})", FLAGS_FILE, System.identityHashCode(this), e);
        }
    }
}
