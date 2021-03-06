package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static{
        SEARCH_INIT_ELEMENT ="css:button#searchIcon";
        SEARCH_INPUT ="css:form>input[type='search']";
        SEARCH_CANCEL_BTN ="css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_BY_TPL ="xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT ="css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT ="css:p.without-results";
        TITLE_AND_DESCRIPTION_TPL = "//h3/strong[text()='{SUBSTRING_T}']/../../div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING_D}')]";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
