package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FeatureFlagFileReader {
    private static final Logger logger = LoggerFactory.getLogger(FeatureFlagFileReader.class);
    private static FeatureFlagFileReader INSTANCE;
    private static Map<String, Boolean> flagsResult;

    private final String FILE_NAME = "feature-flags.properties";

    private FeatureFlagFileReader() {
        flagsResult = readFromClasspath();
    }

    private Map<String, Boolean> readFromClasspath() {
        logger.info("Reading feature flags file: {}", this.FILE_NAME);
        Properties properties = new Properties();
        Map<String, Boolean> result = new HashMap<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(this.FILE_NAME)) {
            if (input == null) {
                logger.warn("Feature flags file not found: {}", this.FILE_NAME);
                return result;
            }
            properties.load(input);
            for (String name : properties.stringPropertyNames()) {
                result.put(name, Boolean.parseBoolean(properties.getProperty(name)));
            }
            logger.info("Parsed {} flags", result.size());
        } catch (IOException e) {
            logger.error("Failed to read feature flags file: {}", this.FILE_NAME, e);
        }

        return result;
    }

    public Boolean getFlagsResult(String flagName) {
        return flagsResult.get(flagName);
    }

    public static FeatureFlagFileReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FeatureFlagFileReader();
        }
        return INSTANCE;
    }
}
