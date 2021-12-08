package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check test results for search page")
    @Description("We type text in input string and make sure that we see correct search result")
    @Step("Start testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check cancel search for cancel btn")
    @Description("Type text in search input and cancel search for cancel btn")
    @Step("Start testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelBtnToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelBtnToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check that search result is more than zero")
    @Description("Type text in search input and chet that search result has more than zero article")
    @Step("Start testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check that search result without result has a plug")
    @Description("Type text in search input with nothing and check plug")
    @Step("Start testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "flkflkhfj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
        @DisplayName("Check that search result can be dump")
    @Description("Type text in search input and dump the search input with cross")
    @Step("Start testCompareArticleTitle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertListHasMoreThanOneObject();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check that article matches with description")
    @Description("Type text in search input and matches article by title and description straightaway")
    @Step("Start testSearchFromTitleAndDescription")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchFromTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "High-level programming language");
    }
}
