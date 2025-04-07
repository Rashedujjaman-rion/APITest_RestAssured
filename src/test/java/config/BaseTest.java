package config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class BaseTest {
    static {
        RestAssured.baseURI = "https://reqres.in";  // Base URL for all API calls

    }

    public RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}
