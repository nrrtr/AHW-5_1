package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {
    private final int rng = (int) (Math.random() * 15);
    LocalDate date = LocalDate.now().plusDays(rng + 3);

    @BeforeEach
    public void openCardForm() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should show not null message with empty city field")
    void shouldShowNotNullMessageWithCityField() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='city'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Should show not null message with empty date field")
    void shouldShowNotNullMessageWithDateField() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[@class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    @DisplayName("Should show not null message with empty name field")
    void shouldShowNotNullMessageWithNameField() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Should show not null message with empty phone field")
    void shouldShowNotNullMessageWithPhoneField() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//*[@data-test-id='agreement']").click();
        $x("//button[@class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='phone'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Should check input_invalid class in checkbox")
    void shouldColorRedWithUncheckedCheckbox() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $(".button [class='button__text']").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    @DisplayName("Should show wrong city message at city field")
    void shouldShowWrongCityMessage() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//input[@placeholder='Город']").setValue("Норильск");
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='city'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    @DisplayName("Should show wrong date message at date field")
    void shouldShowWrongDateMessage() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue("12345678");
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//span[@class='checkbox__box']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    @DisplayName("Should show wrong name message at name field")
    void shouldShowWrongNameMessage() {
        var validUser = DataGenerator.Registration.generateUser("en-US");
        var meetingDate = DataGenerator.generateDate(rng + 3);
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(meetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//span[@class='checkbox__box']").click();
        $(".button [class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldBe(visible).shouldHave(
                exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    @DisplayName("Should pass validation using drop-down list of cities and calendar widget")
    void shouldPassValidationWithPopupCitiesListAndCalendarWidget2() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var day = String.valueOf(date.getDayOfMonth());
        var dateForMatch = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@placeholder='Город']").setValue("Кр");
        $$(".menu-item__control").get(rng).click();
        $x("//*[@id='root']/div/form/fieldset/div[2]/span/span/span/span/span[1]/span/button").click();
        if (date.getMonthValue() > LocalDate.now().getMonthValue()) {
            $x("//*[@class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $$("td.calendar__day").find(exactText(day)).click();
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//span[@class='checkbox__box']").click();
        $(".button [class='button__text']").click();
        $x("//*[@data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id='success-notification']").should(Condition.matchText("Успешно!"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(exactText("Встреча успешно запланирована на " + dateForMatch));
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//input[@placeholder='Город']").setValue(validUser.getCity());
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(firstMeetingDate);
        $x("//input[@name='name']").setValue(validUser.getName());
        $x("//input[@name='phone']").setValue(validUser.getPhone());
        $x("//*[@data-test-id='agreement']").click();
        $(".button [class='button__text']").click();
        $x("/html/body/div[1]/div/div[1]/div[3]").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $x("//*[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@placeholder='Дата встречи']").setValue(secondMeetingDate);
        $(".button [class='button__text']").click();
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
