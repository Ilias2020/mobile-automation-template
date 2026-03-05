package com.company.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Authorization screen.
 */
public class LoginPage extends BasePage {

    private final By authorizationTitle = AppiumBy.accessibilityId("Authorization");
    private final By submitButton = AppiumBy.accessibilityId("Login");
    private final By loginErrorDialog = By.xpath("//android.view.View[@content-desc=\"Ошибка\n" +
            "Невозможно войти с предоставленными учетными данными.\"]");
    private final By allEditTexts = AppiumBy.className("android.widget.EditText");
    private final By passwordField = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    private final By errorOkButton = AppiumBy.accessibilityId("OK");

    public boolean isOpened() {
        try {
            waitVisible(authorizationTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage enterPhone(String phone) {
        WebElement phoneField = findPhoneField();
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement pwd = waitVisible(passwordField);

        type(passwordField, password);

        System.out.println("[LoginPage] After sendKeys text=" + pwd.getAttribute("text"));
        return this;
    }

    public boolean isPasswordEntered() {
        WebElement pwd = findPasswordField();

        String textAttr = pwd.getAttribute("text");
        if (textAttr != null && !textAttr.isBlank()) return true;

        String valueAttr = pwd.getAttribute("value");
        if (valueAttr != null && !valueAttr.isBlank()) return true;

        String elementText = pwd.getText();
        return elementText != null && !elementText.isBlank();
    }

    public void triggerValidation() {
        io.appium.java_client.android.AndroidDriver androidDriver =
                (io.appium.java_client.android.AndroidDriver) driver;
        try {
            if (androidDriver.isKeyboardShown()) {
                androidDriver.hideKeyboard();
            }
        } catch (Exception ignored) {
            // Keyboard may already be hidden.
        }
    }

    public void submit() {
        WebElement submit = waitClickable(submitButton);
        System.out.println("[LoginPage] submit() before click: enabled=" + submit.getAttribute("enabled")
                + ", clickable=" + submit.getAttribute("clickable")
                + ", displayed=" + submit.isDisplayed());
        submit.click();
        System.out.println("[LoginPage] submit() click sent");
    }

    public boolean isSubmitEnabled() {
        WebElement submit = waitVisible(submitButton);
        return Boolean.parseBoolean(submit.getAttribute("enabled"));
    }

    public String getSubmitState() {
        WebElement submit = waitVisible(submitButton);
        return "enabled=" + submit.getAttribute("enabled")
                + ", clickable=" + submit.getAttribute("clickable")
                + ", displayed=" + submit.isDisplayed();
    }

    public boolean isErrorDialogDisplayed() {
        try {
            waitClickable(errorOkButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOkButtonDisplayed() {
        try {
            waitVisible(AppiumBy.accessibilityId("OK"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findPhoneField() {
        return getLoginFields().get(0);
    }

    private WebElement findPasswordField() {
        return waitVisible(passwordField);
    }

    private List<WebElement> getLoginFields() {
        List<WebElement> fields = wait.until(d -> {
            List<WebElement> editTexts = d.findElements(allEditTexts);
            return editTexts.size() >= 2 ? editTexts : null;
        });
        if (fields == null || fields.size() < 2) {
            throw new IllegalStateException("Expected at least 2 EditText fields, found: "
                    + (fields == null ? 0 : fields.size()));
        }
        return fields;
    }
}
