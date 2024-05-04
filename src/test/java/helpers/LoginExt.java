package helpers;

import data.DataTest;
import models.LoginBodyModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static api.ApiAuth.successfulAuthorisation;
import static io.qameta.allure.Allure.step;

public class LoginExt implements BeforeEachCallback {


    private static LoginBodyModel authorizationResponse;

    public void beforeEach(ExtensionContext context) {
        DataTest dataTest = new DataTest();
        step("Авторизация пользователя.", () ->
                authorizationResponse = successfulAuthorisation(dataTest.login, dataTest.password));

    }

    public static LoginBodyModel Authorization() {
        return authorizationResponse;

    }
}
