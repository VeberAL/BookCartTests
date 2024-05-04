package helpers;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Spec.authResponseSpec;
import static specs.Spec.userRequestSpec;

public class LoginExt implements BeforeEachCallback {


    private static Response authorizationResponse;

    public void beforeEach(ExtensionContext context) {
        String authData = "{\"userName\":\"VeberAL\",\"password\":\"12832156aA!\"}";

        Response authResponse = step("Авторизация пользователя", () ->
                given(userRequestSpec)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(authResponseSpec)
                        .extract().response());
        authorizationResponse = authResponse;

        String userID = authResponse.path("userId");
        String expires = authResponse.path("expires");
        String token = authResponse.path("token");

        step("Передача cookie", () -> open("/favicon.ico"));
        getWebDriver().manage().addCookie(new Cookie("userID", userID));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }

    public static Response Authorization() {
        return authorizationResponse;

    }
}
