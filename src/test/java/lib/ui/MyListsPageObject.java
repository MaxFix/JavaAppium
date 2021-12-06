package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    PAGE_WITH_LISTS,
            REMOVE_FROM_SAVED_BTN;

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BTN.replace("{TITLE}", article_title);
    }

    private static String getResultSearchElement(String substring){
        return PAGE_WITH_LISTS.replace("{SUBSTRING}", substring);
    }

    public MyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name " + name_of_folder, 5);
    }

    public void waitForArticleToApearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleByDissapearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToApearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
        this.swipeElementoLeft(article_xpath,
                "Cannot find saved article");} else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click btn to remove article from saved", 10);
        }

        if(Platform.getInstance().isIOS()){
        this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitForArticleByDissapearByTitle(article_title);
    }

    public void clickForSpecificList(String substring){
        String needed_list_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(needed_list_xpath, "Cannot find needed list " + substring, 5);
    }
}
