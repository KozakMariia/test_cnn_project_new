package core.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultPage extends BasePage {


    private static final String RESULT_LIST = "//div[contains(@class, 'cnn-search__result cnn-search__result')]";
    private static final String HEADER_RESULT_LIST = ".//h3[contains(@class, 'cnn-search__result-headline')]/a";
    private static final String BODY_RESULT_LIST = ".//div[contains(@class, 'cnn-search__result-body')]";
    @FindBy(xpath = "//div[contains(@class, 'collectionList')]/ul[contains(@class," +
            " 'facet_list')]/li[contains(@class, 'facet_item')][3]")
    private WebElement videoButton;

    private Map<String, String> articles = new HashMap<>();

    public SearchResultPage(final WebDriver driver) {
        super(driver);
        List<WebElement> listResult = driver.findElements(By.xpath(RESULT_LIST));
        for (WebElement article : listResult) {
            String header = article.findElement(By.xpath(HEADER_RESULT_LIST)).getText();
            String body = article.findElement(By.xpath(BODY_RESULT_LIST)).getText();
            articles.put(header.replaceAll(" +", " ").trim(), body.replaceAll(" +", " ").trim());
        }
    }

    public Map<String, String> getArticles() {
        return articles;
    }

    public void clickVideoButton(){
        articles.clear();
        videoButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> listResult = getWebDriver().findElements(By.xpath(RESULT_LIST));
        for (WebElement article : listResult) {
            String header = article.findElement(By.xpath(HEADER_RESULT_LIST)).getText();
            String body = article.findElement(By.xpath(BODY_RESULT_LIST)).getText();
            articles.put(header.replaceAll(" +", " ").trim(), body.replaceAll(" +", " ").trim());
        }
    }
}