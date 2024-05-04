package helpers;

import data.DataTest;
import models.ResLoginModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static api.ApiAuth.successfulAuthorisation;
import static io.qameta.allure.Allure.step;

public class LoginExt implements BeforeEachCallback {


    private static ResLoginModel authorizationResponse;

    public void beforeEach(ExtensionContext context) {
        DataTest dataTest = new DataTest();
        step("Авторизация пользователя.", () ->
                authorizationResponse = successfulAuthorisation(dataTest.login, dataTest.password));

    }

    public static ResLoginModel Authorization() {
        return authorizationResponse;

    }
}
