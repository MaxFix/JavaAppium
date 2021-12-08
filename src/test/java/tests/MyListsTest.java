package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "MaxTestAppium",
            password = "q1W@e3R$t5Y^";

    @Test
    @Features(value = {@Feature(value = "MyList"), @Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Navigation")})
    @DisplayName("Save article to MyList first time")
    @Description("Search, choose and add article to MyList for the first time")
    @Step("Start testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticletitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorisationPageObject Auth = new AuthorisationPageObject(driver);
            Auth.clickAuthBtn();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticletitle());

            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value = "MyList"), @Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Navigation")})
    @DisplayName("Save two article and delete one of this")
    @Description("Search, choose and add two article to MyList, when we delete one from MyList and check")
    @Step("Start testSaveTwoArticles")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticles() throws InterruptedException {
        String search_line = "Java";
        String js_title = "JavaScript";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticletitle();
        ArticlePageObject.assertArticleTitleWithoutTimeout();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
            if(Platform.getInstance().isIOS()){
                ArticlePageObject.closeModal();
            }
        }
        if(Platform.getInstance().isMW()){
            AuthorisationPageObject Auth = new AuthorisationPageObject(driver);
            Auth.clickAuthBtn();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticletitle());

            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListNotFirstTime(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.clickForSpecificList(name_of_folder);
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.waitForArticleToApearByTitle(js_title);
        MyListsPageObject.openFolderByName(js_title);
        String actual_title = ArticlePageObject.getArticletitle();

        Assert.assertEquals(js_title, actual_title);
    }
}
