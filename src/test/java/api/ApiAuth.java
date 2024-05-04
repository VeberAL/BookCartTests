package api;

import io.qameta.allure.Step;
import models.LoginBodyModel;

import static io.restassured.RestAssured.given;
import static specs.Spec.loginRequestSpec;
import static specs.Spec.loginResponseSpec;

public class ApiAuth {
    @Step("Авторизация пользователя.")
    public static LoginBodyModel successfulAuthorisation(String login, String password) {

        LoginBodyModel userCredentials = new LoginBodyModel();
        userCredentials.setUserName(login);
        userCredentials.setPassword(password);

        return
                given(loginRequestSpec)
                        .body(userCredentials)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginBodyModel.class);
    }
}