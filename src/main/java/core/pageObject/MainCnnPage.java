package core.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainCnnPage extends BasePage{

    @FindBy(xpath = "//div[@id='search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='search-input-field']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit-button']")
    private WebElement searchButtonForResult;

    public MainCnnPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage clickFindButton() {
        searchButton.click();
        return new SearchResultPage(getWebDriver());
    }

    public SearchResultPage clickSearchButtonForResult() {
        searchButtonForResult.click();
        return new SearchResultPage(getWebDriver());
    }

    public void typeSearchedText(final String textToSearch) {
        searchInput.sendKeys(textToSearch);
    }
}
