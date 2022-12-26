package com.alexandrov;

import com.alexandrov.domain.MenuItem;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends TestBase {

    @EnumSource(value = MenuItem.class)
    @ParameterizedTest()
    void checkResultsMenu(MenuItem menuItem) {
        open(UrlLamoda);
        $$("._root_1o7df_2").find(Condition.text(menuItem.getDesc())).click();
    }

    static Stream<Arguments> testWithMethodSource() {
        return Stream.of(
                Arguments.of(
                        "Alexandrov Artem", "alex@gmail.com", "Samara", "Moscow"
                ),
                Arguments.of(
                        "Ivanov Ivan", "ivanov@gmail.com", "Volgograd", "Omsk"
                ),
                Arguments.of(
                        "Popovskay Ekaterina", "popovskaya@gmail.com", "Novosibirsk", "Ishim"
                )
        );
    }
    @MethodSource("testWithMethodSource")
    @ParameterizedTest(name = "text-box: {0}")
    void textBoxTest(String name, String email, String currentAddress, String permanentAddress) {
        open(UrlDemoQa);
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();
        $("#output").shouldHave(text("Name:" + name))
                .shouldHave(text("Email:" + email))
                .shouldHave(text("Current Address :" + currentAddress))
                .shouldHave(text("Permananet Address :" + permanentAddress));
    }

    @ValueSource(strings = {
            "Ботинки",
            "Куртки",
            "Пижамы"
    })
    @ParameterizedTest(name = "Product - {0}")
    void searchProduct(String value) {
        open(UrlLamoda);
        $x("//div[3]/div/div/div/div/input").setValue(value).pressEnter();
        $("._title_641wy_6").shouldHave(text("Товары по запросу «"+value+"»"));
    }

    @CsvSource(value = {
            "gfwfys, Пацаны",
            "jabc, Офис"
    })
    @ParameterizedTest(name = "Ввод фильма {1} англиской раскладкой {0}")
    void checkResultWithIncorrectInput(String incorrectName, String expectedResult) {
        open(UrlKinopoisk);
        $("[name=kp_query]").setValue(incorrectName);
        $("[data-index='0']").shouldHave(text(expectedResult));
    }
}
