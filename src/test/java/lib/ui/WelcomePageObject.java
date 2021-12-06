package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{
    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
    NEXT_BTN = "id:Next",
    GET_STARTED_BTN = "id:Get started",
    SKIP = "id:Skip";

    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextBtn(){
        this.waitForElementAndClick(NEXT_BTN, "Cannot find and click 'Next' btn", 10);
    }

    public void clickGetStartedBtn(){
        this.waitForElementAndClick(GET_STARTED_BTN, "Cannot find and click 'Get started' btn", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP, "Cannot find and click Skip btn", 5);
    }
}
