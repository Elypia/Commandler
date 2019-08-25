package com.elypia.commandler.configuration;

import com.elypia.commandler.Commandler;
import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.combined.CombinedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.*;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.inject.*;
import java.util.*;

/**
 * An immutable view of the {@link Configuration} for {@link Commandler}.
 * This should contain all configuration for the Commandedler instance regardless
 * of sources.
 */
@Singleton
public class CommandlerConfiguration {

    /**
     * An internal view of the {@link Configuration} loaded to define this
     * {@link Commandler} applications runtime behaviour.
     */
    private final ImmutableHierarchicalConfiguration configuration;

    /**
     * A properties view of the {@link #configuration} in order to conveininetly
     * access it's key/value pairs with other APIs.
     */
    private final Properties properties;

    /**
     * Creates and loads the {@link Configuration} using the default profile.
     *
     * @throws ConfigurationException If the configuration fails to initialize.
     */
    @Inject
    public CommandlerConfiguration() throws ConfigurationException {
        this("default");
    }

    /**
     * @param profile The profile to load.
     * @throws ConfigurationException If the configuration fails to initialize.
     */
    public CommandlerConfiguration(final String profile) throws ConfigurationException {
        Objects.requireNonNull(profile);

        Parameters params = new Parameters();
        params.registerDefaultsHandler(FileBasedBuilderParameters.class, new FileDefaultsHandler());

        CombinedConfigurationBuilder builder = new CombinedConfigurationBuilder()
            .configure(params.fileBased().setFileName("config.xml"));
        CombinedConfiguration config = builder.getConfiguration();

        this.configuration = ConfigurationUtils.unmodifiableConfiguration(config);
        this.properties = ConfigurationConverter.getProperties(configuration);
    }

    public <T> List<T> getList(Class<T> t, final String key) {
        return configuration.getList(t, key);
    }

    public String getString(final String key) {
        return configuration.getString(key);
    }

    public int getInt(final String key) {
        return configuration.getInt(key);
    }

    public double getDouble(final String key) {
        return configuration.getDouble(key);
    }

    public float getFloat(final String key) {
        return configuration.getFloat(key);
    }

    /**
     * You should avoid this method where possible
     * as this exposed the underlying configuration library. Should
     * {@link Commandler} ever change how it manages configuration or what
     * library we use is more likely to break.
     *
     * @return The underlying {@link ImmutableHierarchicalConfiguration configuration}
     * that is being used internally.
     */
    public ImmutableHierarchicalConfiguration getConfiguration() {
        return configuration;
    }

    public Properties getProperties() {
        return properties;
    }
}
