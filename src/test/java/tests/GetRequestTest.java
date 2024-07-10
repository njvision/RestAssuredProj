import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//        RestAssured.basePath = "/comments";
    }
    @Test
    public void getResponseBody() {
        Response response = given()
                .param("postId", 1)
                .when().get("/comments");
        System.out.print(response.getBody().asString());
    }

    @Test
    public void checkResponseBodyElement() {
        given()
                .param("postId", 1)
                .when().get("/comments")
                .then()
                .statusCode(200)
                .and()
                .body("[0].email", equalTo("Eliseo@gardner.biz"))
                .contentType(ContentType.JSON);
    }

    @Test
    public void checkResponseBodyGroupOfElements() {
        List<String> emailsExpected = new ArrayList<>(Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz"));
        Response response = given()
                .param("postId", 1)
                .when().get("/comments");
        List<String> emailsActual = response.jsonPath().getList("email");
        emailsExpected.containsAll(emailsActual);
        if (emailsExpected.size() == emailsActual.size()) {
            for (int i = 0; i < emailsExpected.size(); i++) {
                Assert.assertEquals(emailsExpected.get(i), emailsActual.get(i));
            }
        } else {
            throw new AssertionError("Different number of elements 'email' in response");
        }
    }

    @Test(enabled = false)
    public void statusCodeVerification() {
        given()
                .param("postId", 1)
                .when().get("/comments")
                .then().statusCode(200);
    }
}
