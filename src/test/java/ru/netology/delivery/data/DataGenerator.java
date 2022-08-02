package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static Random random = new Random();

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift + random.nextInt(shift))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] validCities = {"Великий Новгород", "Екатеринбург", "Йошкар-Ола", "Калининград",
                "Кемерово", "Киров", "Кострома", "Краснодар", "Красноярск", "Курган",
                "Курск", "Санкт-Петербург", "Сыктывкар", "Чебоксары"};
        return validCities[random.nextInt(validCities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return String.valueOf(faker.phoneNumber().cellPhone());
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    public static class UserInfo {
        String city;
        String name;
        String phone;

        public UserInfo(String city, String name, String phone) {
            this.city = city;
            this.name = name;
            this.phone = phone;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }
}
