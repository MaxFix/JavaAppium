package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOsSearchPageObject extends SearchPageObject {
    static{
        SEARCH_INIT_ELEMENT ="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT ="xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BTN ="xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_BY_TPL ="xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT ="xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT ="xpath://XCUIElementTypeStaticText[@name='No results found']";
        TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_T}']/../XCUIElementTypeStaticText[@name='{SUBSTRING_D}']";
    }
    public iOsSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
