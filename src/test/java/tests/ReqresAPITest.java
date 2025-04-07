package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresAPITest {

      static {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testGetUser() {
        //RestAssured.baseURI = "https://reqres.in/api";

        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200) // Verify status code
                .body("data.id", equalTo(2)) // Validate JSON response
                .body("data.first_name", equalTo("Janet")); // Check name
    }



    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"Rashed\", \"job\": \"QA Engineer\", \"ID\": \"12342\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201) // Created
                .body("name", equalTo("Rashed"))
                .body("job", equalTo("QA Engineer"));

    }

    @Test
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"Rion\", \"job\": \"Senior QA\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Rion"))
                .body("job", equalTo("Senior QA"));

    }


    @Test
    public void testDeleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204); // No content
    }


    @Test
    public void testExtractData() {
        Response response =
                given()
                        .when()
                        .get("https://reqres.in/api/users/2")
                        .then()
                        .extract().response();

        // Extract values
        String firstName = response.jsonPath().getString("data.first_name");
        System.out.println("User First Name: " + firstName);
        
    }
}
