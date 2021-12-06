package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_TWO = "id:JavaScript";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BTN = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-star-base20";
        //#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-star-base20 - не нажата
        //#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-unStar-progressive - нажата

        OPTIONS_REMOVE_FROM_MY_LIST_BTN = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
        SUBSTRING_JS = "High-level programming language";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
