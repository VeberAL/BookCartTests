package tests;

import data.DataTest;
import models.ResLoginModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProfilePage;

import static api.ApiAuth.successfulAuthorisation;
import static api.ApiBook.bookAdd;
import static api.ApiBook.bookDelete;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class BookCartTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    DataTest dataTest = new DataTest();

    @DisplayName("Delete book from user profile")
    @Test
    @Tag("demoqa")
//    @WithLogin
    void bookDeleteTest() {
        ResLoginModel loginResponse = successfulAuthorisation(dataTest.login, dataTest.password);
        bookDelete(loginResponse.getUserId(), loginResponse.getToken());
        bookAdd(dataTest.bookId, loginResponse.getUserId(), loginResponse.getToken());
        bookDelete(loginResponse.getUserId(), loginResponse.getToken());

        step("Передача cookies.", () -> {
            open(baseUrl + "/favicon.png");
            getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        });

        step("Проверка удаления книги из корзины профиля пользователя.", () -> {
            open(baseUrl + "/profile");
            profilePage.emptyTableCheck();
        });

    }
}