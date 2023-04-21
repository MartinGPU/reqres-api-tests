package com.marat.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @Test
    public void getSingleUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void createUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Donatello\", \"job\": \"peace maker\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",  is("Donatello"))
                .body("job",  is("peace maker"));
 }

    @Test
    public void updateUserData() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Donald\", \"job\": \"teacher\"}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name",  is("Donald"))
                .body("job",  is("teacher"));
    }

    @Test
    public void dropUserData() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Donald\", \"job\": \"teacher\"}")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void userRegistration() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"rachel.howell@reqres.in\", \"password\": \"lineage\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("id",  is(12))
                .body("token",  is("QpwL5tke4Pnpja7X12"));
    }

    @Test
    public void userAuth() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"rachel.howell@reqres.in\", \"password\": \"lineage\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token",  is("QpwL5tke4Pnpja7X12"));
    }
}
