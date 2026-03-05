package com.company.mobile.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.mobile.core.BaseTest;

public class SmokeTest extends BaseTest {

    @Test
    @Description("Smoke: проверяем, что TestNG запускается через Gradle и Allure пишет results")
    public void simpleTest() {
        Assert.assertTrue(true);
    }
}