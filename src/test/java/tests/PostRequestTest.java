import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//        RestAssured.basePath = "/posts";
    }

    @Test
    public void postResponseBody() {
       given().body("{"
                        + "\"postId\":1,"
                        + "\"name\":\"id labore ex et quam laborum\","
                        + "\"email\":\"Eliseo@gardner.biz\","
                        + "\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\""
                        + "}")
                .when().post("/comments")
                .then().statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", equalTo(501)).and();
    }
}
