package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorisationPageObject extends MainPageObject {
    private static final String
        LOGIN_BTN = "xpath://body/div/div/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BTN = "css:button#wpLoginAttempt";

    public AuthorisationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthBtn() throws InterruptedException {
        this.waitForElementPresent(LOGIN_BTN, "Cannot find auth btn", 5);
        Thread.sleep(1000);
        this.waitForElementAndClick(LOGIN_BTN, "Cannot find and click auth btn", 5);
    }
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    }
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BTN, "Cannot find and click submit auth btn", 5);
    }
}
