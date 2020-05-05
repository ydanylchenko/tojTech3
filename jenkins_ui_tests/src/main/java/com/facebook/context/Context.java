package com.facebook.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Context {
    private static final Logger LOG = LoggerFactory.getLogger(Context.class);
    private Map<String, String> dataStore = new LinkedHashMap<>();
    private NavigableMap<String, byte[]> screenshots = new TreeMap<>();
    private boolean isMobile;
    private String scenarioName;
    private String pageName;
    private String featureName;
    private String outputDirectory;

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Map<String, String> getDataStore() {
        return dataStore;
    }

    public String getOutputDirectory() {
        if (outputDirectory == null) {
            outputDirectory = String.join(File.separator,
                    System.getProperty("user.dir"),
                    "target",
                    "features",
                    featureName,
                    scenarioName.replaceAll("\\W+", "_"));
            if (new File(outputDirectory).mkdirs()) {
                LOG.debug("{} folder created", outputDirectory);
            } else {
                LOG.debug("{} folder already exists", outputDirectory);
            }
        }
        return outputDirectory;
    }

    public void setIsMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public NavigableMap<String, byte[]> getScreenshots() {
        return screenshots;
    }

    public void storeCapturedScreenshot() {
        storeCapturedScreenshot(false);
    }

    public void storeCapturedScreenshot(boolean last) {
        if (last) {
            Map.Entry<String, byte[]> lastImage = screenshots.lastEntry();
            writeScreenshot(lastImage.getKey(), lastImage.getValue());
        } else {
            screenshots.forEach(this::writeScreenshot);
        }
    }

    private void writeScreenshot(String name, byte[] imageBytes) {
        LOG.info("Saving {} page screenshot", name);
        String format = "png";
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            File file = new File(getOutputDirectory(), name);
            ImageIO.write(bufferedImage, format, file);
            System.out.println("image created" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
