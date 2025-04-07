package pages;

import config.BaseTest;
import io.restassured.response.Response;
import models.User;

public class UserApi extends BaseTest {

    public Response getUserById(int id) {
        return getRequestSpec()
                .when()
                .get("/api/users/" + id);
    }

    public Response createUser(User user) {
        return getRequestSpec()
                .body(user)
                .when()
                .post("/api/users");
    }
}
