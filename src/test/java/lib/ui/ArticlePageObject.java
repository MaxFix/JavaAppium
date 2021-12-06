package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
    TITLE,
    TITLE_TWO,
    FOOTER_ELEMENT,
    OPTIONS_BTN ,
    OPTIONS_ADD_TO_MY_LIST_BTN,
    OPTIONS_REMOVE_FROM_MY_LIST_BTN,
            ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BTN,
    CLOSE_ARTICLE_BTN,
            CLOSE_MODAL,
    SUBSTRING_JS;


    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticletitle(){
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(OPTIONS_BTN,
                "Cannot find button to open article options", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BTN,
                "Cannot find option to add article to reading list", 5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay ", 5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder", 5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder, "Cannot put text into article folder input", 5);

        this.waitForElementAndClick(MY_LIST_OK_BTN,
                "Cannot press OK button", 5);
    }

    public void addArticleToMyListNotFirstTime(String name_of_folder){
        this.waitForElementAndClick(OPTIONS_BTN,
                "Cannot find button to open article options", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BTN,
                "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle(){
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(CLOSE_ARTICLE_BTN,
                    "Cannot close article, cannot find X link", 5);
        } else {
            System.out.println("Method closeArticle() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void assertArticleTitleWithoutTimeout(){
        this.assertElementPresentWithoutTimeout(TITLE, "Cannot find article title text");
    }

    public void addArticleToMySaved(){
        if(Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BTN, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BTN)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BTN,
                    "Cannot click btn to remove an article from saved", 2);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BTN,
                    "Cannot find btn to add an article to saved list after removing it from this list before");
        }
    }

    public void closeModal(){
        this.waitForElementAndClick(CLOSE_MODAL, "Cannot find X to close modal window", 5);
    }
}
