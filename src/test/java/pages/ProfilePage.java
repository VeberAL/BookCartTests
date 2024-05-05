package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import tests.BookCartTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private SelenideElement noDataGridElement = $(".rt-noData");
    private SelenideElement tBodyElement = $(".rt-tbody");
    private SelenideElement texRightButtonElement = $(".text-right.button.di");
    private SelenideElement modalFooterElement = $(".modal-footer");

    public void emptyTableCheck() {

        noDataGridElement.shouldHave(Condition.text("No rows found"));
    }

    public void bookCheck() {
        IsbnName();
        open(baseUrl + "/profile");
        tBodyElement.shouldHave(text(ProfilePage.bookName)).shouldBe(visible);
        texRightButtonElement.$(byText("Delete All Books")).click();
        modalFooterElement.$(byText("OK")).click();
        Selenide.confirm();
    }

    public static String bookName;

    public static String IsbnName() {

        return switch (BookCartTest.dataTest.bookId) {
            case "9781491904244" -> bookName = "You Don't Know JS";
            case "9781491950296" -> bookName = "Programming JavaScript Applications";
            case "9781593275846" -> bookName = "Eloquent JavaScript, Second Edition";
            case "9781593277574" -> bookName = "Nicholas C. Zakas";
            case "9781449331818" -> bookName = "Learning JavaScript Design Patterns";
            case "9781449337711" -> bookName = "Designing Evolvable Web APIs with ASP.NET";
            case "9781449365035" -> bookName = "Speaking JavaScript";
            case "9781449325862" -> bookName = "Git Pocket Guide";
            default -> null;
        };
    }
}