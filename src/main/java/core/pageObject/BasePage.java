package core.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private WebDriver webDriver;
    private Actions actions;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor javascriptExecutor;

    private static final Integer DEFAULT_PAGE_TIME_OUT_IN_SECONDS = 30;
    private static final Integer DEFAULT_ELEMENT_TIME_OUT_IN_SECONDS = 30;

    protected BasePage(final WebDriver driver) {
        this.webDriver = driver;
        actions = new Actions(webDriver);
        webDriverWait = new WebDriverWait(webDriver, DEFAULT_ELEMENT_TIME_OUT_IN_SECONDS);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_PAGE_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
        javascriptExecutor = ((JavascriptExecutor) webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
