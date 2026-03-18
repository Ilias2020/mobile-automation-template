package com.company.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CatalogScreenLocators {

    private CatalogScreenLocators(){

    }

    public static final By TITLE =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Каталог\")"
            );

    public static final By PROFILE_TAB =
            AppiumBy.accessibilityId("Профиль\nВкладка 2 из 2");
}
