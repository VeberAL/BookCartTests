package helpers;

import data.DataTest;
import io.qameta.allure.Step;
import models.ResLoginModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static api.ApiAuth.successfulAuthorisation;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExt implements BeforeEachCallback {


    public static ResLoginModel authorizationResponse;

    @Step("Авторизация пользователя.")
    public void beforeEach(ExtensionContext context) {
        DataTest dataTest = new DataTest();
        authorizationResponse = successfulAuthorisation(dataTest.login, dataTest.password);

        open("/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("userID", authorizationResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authorizationResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authorizationResponse.getToken()));
    }

    public static ResLoginModel Authorization() {
        return authorizationResponse;

    }
}
