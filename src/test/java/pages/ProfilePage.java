package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private SelenideElement noDataGridElement = $(".rt-noData");

    public void emptyTableCheck() {

        noDataGridElement.shouldHave(Condition.text("No rows found"));
    }
}