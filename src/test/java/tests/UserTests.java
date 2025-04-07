package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UserApi;
import utils.DataProviderUtils;



public class UserTests {




    UserApi userApi = new UserApi();

    @Test
    public void testGetUserById() {
        Response response = userApi.getUserById(2);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
    }

    @Test(dataProvider = "userData", dataProviderClass = utils.DataProviderUtils.class)
    public void testCreateUser(String name, String job) {
        User user = new User(name, job);
        Response response = userApi.createUser(user);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);
    }
}
