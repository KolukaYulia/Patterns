package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {

    }

    static void sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);

    }

    public static String getLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String getPassword() {
        String password = faker.internet().password();
        return password;
    }

    public static class Registration {

        private Registration() {
        }
        public static RegistrationDto getUser(String status) {
            var user = new RegistrationDto(getLogin(), getPassword(), status);
            return user;
        }

        public static RegistrationDto getRegisteredUser(String status) {
            var registereduser = getUser(status);
            sendRequest(registereduser);
            return registereduser;
        }

    }
    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}
