import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.generateRequest.GenerateCreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTest {

    //curl -i -H "Accept:application/json" -H "Content-Type:application/json"
    // -H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v1/users"
    // -d '{"name":"Tenali Ramakrishna", "gender":"male", "email":"tenali.ramakrishna@15ce.com", "status":"active"}'

    // https://gorest.co.in/public/v1/users
    // https://gorest.co.in
    // /public/v1/users

    @Test
    public void createNewUser() {
        RestAssured.baseURI = "https://gorest.co.in";
        String requestBody = null;
        ObjectMapper om = new ObjectMapper();
        try {
            requestBody = om.writeValueAsString(GenerateCreateUserRequest.generateRequest("TestNamesasweew",
                    "test@testew1.com", "active", "Male"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(requestBody);

        // given
        // when
        // then

        Response response = given()
                .contentType("application/json")
                .headers("Authorization", "Bearer bcd3cf3168de8ea08aaffc558dcb4cf9569ae0960457dac92d7b128ec1f3fc23")
                .body(requestBody)
                .when()
                .post("/public/v1/users")
                .then().assertThat().statusCode(201).extract().response();

        System.out.println(response.asString());

        JsonPath jsonPath = new JsonPath(response.asString());
        int id = jsonPath.getInt("data.id");
        Assert.assertNotNull(id, "Verify id is not null");

        // ?key=value -> query param
        // /34 -> path param

        response = given()
                .headers("Authorization", "Bearer bcd3cf3168de8ea08aaffc558dcb4cf9569ae0960457dac92d7b128ec1f3fc23")
                //.queryParam("key", "value")
                .when()
                .get("/public/v1/users/" + id)
                .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        jsonPath = new JsonPath(response.asString());

        Assert.assertEquals(jsonPath.getString("data.name"), "TestNamesasweew", "verify username");

        // post -  usually will create a new resource, request body to be given fully.
        // patch - usually used to update single/multiple value in db and resource name (id number),
        //          no need to give full request body.
        // put - to update single or multiple values in db, but u need to give full request body

    }

    @Test
    public void sample() {
        // https://reqres.in/api/users?page=2

        RestAssured.baseURI = "https://reqres.in";

        Map<String, Integer> headers = new HashMap<>();
        headers.put("page", 2);

        Response response = given()
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

}
