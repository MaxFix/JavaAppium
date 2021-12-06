package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOsArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        TITLE_TWO = "id:JavaScript";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BTN = "id:Save for later";
        CLOSE_ARTICLE_BTN = "id:Back";
        CLOSE_MODAL = "id:places auth close";
        SUBSTRING_JS = "High-level programming language";
    }

    public iOsArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
