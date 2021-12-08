package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Orientation")})
    @DisplayName("Change screen orientation")
    @Description("Change app orientation and save previous search result")
    @Step("Start testChangeScreenOrientationOnSearchResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testChangeScreenOrientationOnSearchResult() {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticletitle();
        this.rotaitScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticletitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotaitScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticletitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value = "Save state")})
    @DisplayName("Move app to the background")
    @Description("Move app to the background and wake up, check restore article result")
    @Step("Start testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckSearchArticleInBackground() {
        if(Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
