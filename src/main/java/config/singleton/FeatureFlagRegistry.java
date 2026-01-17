package config.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class FeatureFlagRegistry {
    private static final Logger logger = LoggerFactory.getLogger(FeatureFlagRegistry.class);

    private final Map<String, Boolean> flags;

    private FeatureFlagRegistry() {
        logger.info("Creating FeatureFlagRegistry Singleton instance (id={})", System.identityHashCode(this));
        logger.info("Loading feature flags from feature-flags.properties (count=1)");
        FeatureFlagFileSource fileSource = new FeatureFlagFileSource();
        Map<String, Boolean> loaded = fileSource.loadFlags();
        this.flags = Collections.unmodifiableMap(loaded);
    }

    private static class Holder {
        private static final FeatureFlagRegistry INSTANCE = new FeatureFlagRegistry();
    }

    public static FeatureFlagRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public boolean isEnabled(String key) {
        boolean value = flags.getOrDefault(key, false);
        logger.info("FeatureFlagRegistry.isEnabled(key={}, value={}, registryId={})", key, value, System.identityHashCode(this));
        return value;
    }

    public Optional<Boolean> get(String key) {
        Boolean value = flags.get(key);
        logger.info("FeatureFlagRegistry.get(key={}, value={}, registryId={})", key, value, System.identityHashCode(this));
        return Optional.ofNullable(value);
    }
}
