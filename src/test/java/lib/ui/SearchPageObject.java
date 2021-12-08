package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
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
    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    /* TEMPLATES METHODS */
    @Step("Get xpath by title and description")
    public String getTitleXpathByTitleAndDescription(String articleTitle, String articleDescription) {
        return TITLE_AND_DESCRIPTION_TPL.replace("{SUBSTRING_T}", articleTitle).replace("{SUBSTRING_D}", articleDescription);
    }

    @Step("Wait for btn to cancel search result")
    public void waitForCancelBtnToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BTN, "Cannot find search cancel btn", 5);
    }

    @Step("Waiting for search cancel btn to disappear")
    public void waitForCancelBtnToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BTN, "Search cancel btn is still present", 5);
    }

    @Step("Clicking btn to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BTN, "Cannot find and click search cancel btn", 5);
    }

    @Step("Typing '{search_line}' to the search result")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Click by article with any substring text")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticle() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request ",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    @Step("Assert of nothing to result of search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    @Step("Assert what list has more than one object in result")
    public void assertListHasMoreThanOneObject() {
        List<WebElement> listElements = this.waitForElementsPresent(PAGE_LIST_ITEMS,
                "List of elements has less than 1 item", 5);
        Assert.assertTrue("Result of title search > 1", listElements.size() > 1);
    }

    @Step("Check element in search result by title and description")
    public void waitForElementByTitleAndDescription(String Title, String Description) {
        String articleXpathByTitleAndDescription = getTitleXpathByTitleAndDescription(Title, Description);
        this.waitForElementsPresent(articleXpathByTitleAndDescription,
                " \n Cannot find an Article with Title: " + Title + " and description " + Description, 10);
    }
}
