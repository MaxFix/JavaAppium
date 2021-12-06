package tests;

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

            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticletitle());

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
    public void testSaveTwoArticles(){
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
            ArticlePageObject.closeModal();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Programming language");

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
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.clickMyLists();

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.waitForArticleToApearByTitle(js_title);
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        String actual_title = ArticlePageObject.getArticletitle();

        Assert.assertEquals(js_title, actual_title);
    }
}
