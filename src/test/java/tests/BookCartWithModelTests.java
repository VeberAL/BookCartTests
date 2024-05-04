package tests;

import helpers.LoginExt;
import helpers.WithLogin;
import io.restassured.response.Response;
import models.RequestAddBookModel;
import models.RequestDeleteBookModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Collections;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookCartWithModelTests extends TestBase {
    @WithLogin
    @Test
    void addAndDeleteBookToCollectionTest() {
        RequestAddBookModel bookData = new RequestAddBookModel();
        RequestAddBookModel.CollectionOfIsbns collectionOfIsbns = new RequestAddBookModel.CollectionOfIsbns();
        RequestDeleteBookModel deleteBookData = new RequestDeleteBookModel();

        Response authResponse = LoginExt.Authorization();
        String userID = authResponse.path("userId"),
                expires = authResponse.path("expires"),
                token = authResponse.path("token"),
                isbn = "9781449337711";

        collectionOfIsbns.setIsbn(isbn);
        bookData.setUserId(userID);
        bookData.setCollectionOfIsbns(Collections.singletonList(collectionOfIsbns));
        deleteBookData.setUserId(userID);
        deleteBookData.setIsbn(isbn);

        //удаление всех книг
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
        //добавление книги
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
        //удаление книги
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userID));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }
}