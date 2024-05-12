package api;

import io.qameta.allure.Step;
import models.AddBookModel;
import models.IsbnBookModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.Spec.*;

public class ApiBook {

    @Step("Добавление книги в корзину профиля пользователя.")
    public static void bookAdd(String bookId, String userId, String token) {

        IsbnBookModel isbnModel = new IsbnBookModel();
        isbnModel.setIsbn(bookId);
        List<IsbnBookModel> isbn = new ArrayList<>();
        isbn.add(isbnModel);

        AddBookModel bookAddingInfo = new AddBookModel();
        bookAddingInfo.setCollectionOfIsbns(isbn);
        bookAddingInfo.setUserId(userId);

        given(bookAddAndDeleteSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookAddingInfo)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(bookAddResponseSpec);
    }

    @Step("Удаление всех книг из корзины профиля пользователя.")
    public static void bookDelete(String userId, String token) {

        given(bookAddAndDeleteSpec)
                .header("Authorization", "Bearer " + token)
                .param("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(bookDeleteResponseSpec);
    }
}