package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.DataGenerator.getLogin;
import static ru.netology.DataGenerator.getPassword;

public class TestForm {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSucсsessfulLoginRegisteredUser() {
        var registereduser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registereduser.getLogin());
        $("[data-test-id='password'] input").setValue(registereduser.getPassword());
        $("button.button").click();
        $(withText("Личный кабинет")).shouldBe(appear);

    }

}
