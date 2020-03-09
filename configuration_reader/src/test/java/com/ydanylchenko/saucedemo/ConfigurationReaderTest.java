package com.ydanylchenko.saucedemo;

import org.testng.annotations.Test;

import static com.ydanylchenko.saucedemo.TestConfigurationReader.getConfig;
import static org.testng.Assert.assertEquals;

public class ConfigurationReaderTest {
    static {
        System.setProperty("missing.environment.configuration.property", "missingEnvironmentConfigurationProperty");
        System.setProperty("overwritten.environment.configuration.property", "overwrittenEnvironmentPropertyValue");
    }

    @Test
    public void configurationReaderTest() {
        assertEquals(getConfig().getGroup(), "groupValue");
        assertEquals(getConfig().getGroupSubgroup(), "groupSubgroupValue");
        assertEquals(getConfig().getGroupSubgroupEndpoint(), "groupSubgroupEndpointValue");
        assertEquals(getConfig().getNotExistingProperty(), "notExistingPropertyDefaultValue");
        assertEquals(getConfig().getMissingEnvironmentProperty(), "missingEnvironmentConfigurationProperty");
        assertEquals(getConfig().getOverwrittenEnvironmentProperty(), "overwrittenEnvironmentPropertyValue");
    }
}
