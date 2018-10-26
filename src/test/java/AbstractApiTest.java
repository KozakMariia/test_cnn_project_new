import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;

public abstract class AbstractApiTest {

    private static final String API_BASE_URL = "https://search.api.cnn.io";

    protected AbstractApiTest() {
        RestAssured.baseURI = API_BASE_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        newConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }
}
