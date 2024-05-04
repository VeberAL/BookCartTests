package tests;

import helpers.LoginExt;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.RequestAddBookModel;
import models.RequestDeleteBookModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Spec.*;

public class BookCartTest extends TestBase {
    @WithLogin
    @Test
    void addAndDeleteBookToCollectionTest() {
        RequestAddBookModel bookData = new RequestAddBookModel();
        RequestAddBookModel.CollectionOfIsbns collectionOfIsbns = new RequestAddBookModel.CollectionOfIsbns();
        RequestDeleteBookModel deleteBookData = new RequestDeleteBookModel();

        Response authResponse = LoginExt.Authorization();
        String userID = authResponse.path("userId"),
                token = authResponse.path("token"),
                isbn = "9781449337711";

        deleteBookData.setUserId(userID);
        deleteBookData.setIsbn(isbn);
        bookData.setUserId(userID);
        collectionOfIsbns.setIsbn(isbn);
        bookData.setCollectionOfIsbns(Collections.singletonList(collectionOfIsbns));

        step("Удаление всех книг из корзины", () ->
                given(userRequestSpec)
                        .header("Authorization", "Bearer " + token)
                        .queryParams("UserId", userID)
                        .when()
                        .delete("/BookStore/v1/Books")
                        .then()
                        .spec(deleteBookResponseSpec));

        step("Добавление книги в корзину", () ->
                given(userRequestSpec)
                        .header("Authorization", "Bearer " + token)
                        .body(bookData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(addBookResponseSpec));

        step("Удаление книги из корзины", () ->
                given(userRequestSpec)
                        .header("Authorization", "Bearer " + token)
                        .body(deleteBookData)
                        .when()
                        .delete("/BookStore/v1/Book")
                        .then()
                        .spec(deleteBookResponseSpec));
        step("Открытие страницы профиля", () ->
                open("/profile"));
    }
}