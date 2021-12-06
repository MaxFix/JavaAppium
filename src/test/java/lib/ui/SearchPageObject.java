package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BTN,
            SEARCH_RESULT_BY_SUBSTRING_BY_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            PAGE_LIST_ITEMS,
            TITLE_AND_DESCRIPTION_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_BY_TPL.replace("{SUBSTRING}", substring);
    }

    /* TEMPLATES METHODS */
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    /* TEMPLATES METHODS */
    public String getTitleXpathByTitleAndDescription(String articleTitle, String articleDescription) {
        return TITLE_AND_DESCRIPTION_TPL.replace("{SUBSTRING_T}", articleTitle).replace("{SUBSTRING_D}", articleDescription);
    }


    public void waitForCancelBtnToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BTN, "Cannot find search cancel btn", 5);
    }

    public void waitForCancelBtnToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BTN, "Search cancel btn is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BTN, "Cannot find and click search cancel btn", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticle() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request ",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertListHasMoreThanOneObject() {
        List<WebElement> listElements = this.waitForElementsPresent(PAGE_LIST_ITEMS,
                "List of elements has less than 1 item", 5);
        Assert.assertTrue("Result of title search > 1", listElements.size() > 1);
    }

    public void waitForElementByTitleAndDescription(String Title, String Description) {
        String articleXpathByTitleAndDescription = getTitleXpathByTitleAndDescription(Title, Description);
        this.waitForElementsPresent(articleXpathByTitleAndDescription,
                " \n Cannot find an Article with Title: " + Title + " and description " + Description, 10);
    }
}
