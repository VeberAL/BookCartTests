package api;

import models.LoginBodyModel;
import models.ResLoginModel;

import static io.restassured.RestAssured.given;
import static specs.Spec.loginRequestSpec;
import static specs.Spec.loginResponseSpec;

public class ApiAuth {

    public static ResLoginModel successfulAuthorisation(String login, String password) {

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
                        .extract().as(ResLoginModel.class);
    }
}