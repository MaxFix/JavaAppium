package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
                ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}}']";
                PAGE_WITH_LISTS = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{SUBSTRING}']";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
