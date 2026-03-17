package com.company.mobile.tests;

import com.company.mobile.helper.Helper;
import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.company.mobile.utils.Initializer;

@Slf4j
public class BaseTest {

    protected static AppiumDriver driver;

    @BeforeMethod
    @SneakyThrows
    public void setUp() {
        log.info("Driver: Initializing before test");
        driver = Initializer.getDriver();
        Helper.setDriver(driver);
        Thread.sleep(2000);

        log.info("!!!! Starting the Test !!!!");
    }

    @AfterMethod
    public void tearDown() {
        log.info("Driver: Closing after test");
        Initializer.quitDriver();
    }
}
