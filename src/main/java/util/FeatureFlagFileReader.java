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

    public Map<String, Boolean> readFromClasspath(String fileName) {
        logger.info("Reading feature flags file: {}", fileName);
        Properties properties = new Properties();
        Map<String, Boolean> result = new HashMap<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.warn("Feature flags file not found: {}", fileName);
                return result;
            }
            properties.load(input);
            for (String name : properties.stringPropertyNames()) {
                result.put(name, Boolean.parseBoolean(properties.getProperty(name)));
            }
            logger.info("Parsed {} flags", result.size());
        } catch (IOException e) {
            logger.error("Failed to read feature flags file: {}", fileName, e);
        }

        return result;
    }
}
