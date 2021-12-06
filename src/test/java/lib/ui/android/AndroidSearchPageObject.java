package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    static{
            SEARCH_INIT_ELEMENT ="xpath://*[contains(@text, 'Search Wikipedia')]";
            SEARCH_INPUT ="xpath://*[contains(@text, 'Search…')]";
            SEARCH_CANCEL_BTN ="id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_BY_SUBSTRING_BY_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
            SEARCH_RESULT_ELEMENT ="xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
            SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[@text='No results found']";
            PAGE_LIST_ITEMS ="id:org.wikipedia:id/page_list_item_title";
            TITLE_AND_DESCRIPTION_TPL =
                    "xpath://android.widget.LinearLayout/android.widget.TextView[@text='{SUBSTRING_T}']/../android.widget.TextView[@text='{SUBSTRING_D}']";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
