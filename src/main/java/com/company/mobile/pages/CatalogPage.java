package com.company.mobile.pages;

import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

    private final By catalogTitle = By.xpath("//android.widget.TextView[@text='Каталог']");

    public boolean isOpened() {
        try {
            waitVisible(catalogTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
