package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

@Slf4j
public class Initializer {
    private static AppiumDriver driver;
    static Properties config = new Properties();

    static {
        try (InputStream input = Initializer.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                throw new IllegalStateException("Unable to find config.properties");
            }
            config.load(input);
        } catch (Exception ex) {
            log.error("Failed to load configuration", ex);
            throw new ExceptionInInitializerError();
        }
    }
    public static AppiumDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void initDriver() {
        try {
            URI appiumServerURI = new URI(config.getProperty("appium.server.url"));
            URL appiumServerURL = appiumServerURI.toURL();

            driver = new AndroidDriver(appiumServerURL, getAndroidOptions());
            log.info("Driver initialized succesfully");
        } catch (Exception e) {
            log.error("Driver initialiazed failed", e);
            throw new RuntimeException(e);
        }
    }

    private static UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(config.getProperty("device.name"));
        options.setPlatformName(config.getProperty("platform.name"));
        options.setAutomationName(config.getProperty("automation.name"));
        options.setAppPackage(config.getProperty("app.package"));
        options.setAppActivity(config.getProperty("app.activity"));
        options.setNewCommandTimeout(Duration.ofSeconds(
                Long.parseLong(config.getProperty("new.command.timeout"))
        ));
        options.setIgnoreHiddenApiPolicyError(true);
        options.setAutoGrantPermissions(true);

        return options;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
