package helpers;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExt implements BeforeEachCallback {


    private static Response authResponse1;

    public void beforeEach(ExtensionContext context) {
        String authData = "{\"userName\":\"VeberAL\",\"password\":\"12832156aA!\"}";

        Response authResponse =
                given()
                        .log().uri()
                        .log().method()
                        .log().body()
                        .contentType(JSON)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().response();
        authResponse1 = authResponse;
    }

    public static Response Authorization() {
        return authResponse1;

    }
}
