package com.ydanylchenko.saucedemo;

import com.ydanylchenko.configuration.reader.Config;
import com.ydanylchenko.configuration.reader.properties.PropertiesLoader;
import com.ydanylchenko.configuration.reader.properties.Property;
import com.ydanylchenko.configuration.reader.properties.PropertyFile;

/**
 * Class for base tests properties - urls for test, browser name and version
 */
@PropertyFile("configurationFolder/configuration.properties")
public class TestConfigurationReader extends Config {

    private static TestConfigurationReader config;

    public static TestConfigurationReader getConfig() {
        if (config == null) {
            config = new TestConfigurationReader();
        }
        return config;
    }

    public TestConfigurationReader() {
        PropertiesLoader.populate(this);
    }

    @Property("group")
    private String group;

    public String getGroup() {
        return group;
    }

    @Property("group.subgroup")
    private String groupSubgroup;

    public String getGroupSubgroup() {
        return groupSubgroup;
    }

    @Property("group.subgroup.endpoint")
    private String groupSubgroupEndpoint;

    public String getGroupSubgroupEndpoint() {
        return groupSubgroupEndpoint;
    }

    @Property("missing.configuration.property")
    private String notExistingProperty = "notExistingPropertyDefaultValue";

    public String getNotExistingProperty() {
        return notExistingProperty;
    }

    @Property("missing.environment.configuration.property")
    private String missingEnvironmentProperty;

    public String getMissingEnvironmentProperty() {
        return missingEnvironmentProperty;
    }

    @Property("overwritten.environment.configuration.property")
    private String overwrittenEnvironmentProperty = "initialValue";

    public String getOverwrittenEnvironmentProperty() {
        return overwrittenEnvironmentProperty;
    }
}
