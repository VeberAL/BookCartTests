package tests;

import data.DataTest;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static api.ApiBook.bookAdd;
import static api.ApiBook.bookDelete;
import static helpers.LoginExt.authorizationResponse;

public class BookCartTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    public static DataTest dataTest = new DataTest();

    @DisplayName("Тест на проверку удаления книги из корзины профиля пользователя.")
    @Test
    @Tag("demoqa")
    @WithLogin
    void bookDeleteTest() {
        bookDelete(authorizationResponse.getUserId(), authorizationResponse.getToken());
        bookAdd(dataTest.bookId, authorizationResponse.getUserId(), authorizationResponse.getToken());
        profilePage
                .authCheck()
                .profileOpen()
                .bookCheck()
                .ibsnBookDelete()
                .emptyTableCheck();

    }
}