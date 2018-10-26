import core.dto.ArticleDTO;
import core.dto.ResultDTO;
import core.pageObject.MainCnnPage;
import core.pageObject.SearchResultPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.jayway.restassured.RestAssured.given;

public class TestCnn extends AbstractApiTest {

    private WebDriver driver;

    private static final String CONTENT_AND_SIZE_API_PATH = "/content?size=10&q=bitcoin";
    private static final String CONTENT_AND_SIZE_VIDEO_API_PATH = "/content?size=10&q=bitcoin&type=video";

    @Before
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://edition.cnn.com");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void verifySearchResult() {
        final MainCnnPage mainPage = new MainCnnPage(driver);
        mainPage.clickFindButton();
        mainPage.typeSearchedText("bitcoin");
        SearchResultPage searchResultPage = mainPage.clickSearchButtonForResult();
        Map<String, String> mapResult = searchResultPage.getArticles();
        ResultDTO resultDTO = given().get(CONTENT_AND_SIZE_API_PATH).as(ResultDTO.class);
        assertResult(resultDTO, mapResult);
    }

    @Test
    public void verifyVideoSearchResult() {
        final MainCnnPage mainPage = new MainCnnPage(driver);
        mainPage.clickFindButton();
        mainPage.typeSearchedText("bitcoin");

        SearchResultPage searchResultPage = mainPage.clickSearchButtonForResult();
        searchResultPage.clickVideoButton();

        Map<String, String> mapResult = searchResultPage.getArticles();
        ResultDTO resultDTO = given().get(CONTENT_AND_SIZE_VIDEO_API_PATH).as(ResultDTO.class);
        assertResult(resultDTO, mapResult);
    }

    @After
    public void driverTeamDown() {
        driver.close();
    }

    private void assertResult(ResultDTO resultDTO, Map<String, String> mapResult) {
        List<ArticleDTO> articleDTOs = resultDTO.getResult();
        for (ArticleDTO articleDto : articleDTOs) {
            String headline = articleDto.getHeadline();
            String body = articleDto.getBody();
            Assert.assertTrue(mapResult.containsKey(headline.replaceAll(" +", " ").trim()));
            Assert.assertEquals(mapResult.get(headline), body.replaceAll(" +", " ").trim());
        }
    }
}
