import POJO.CreateCourier;
import POJO.LoginCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;



public class LoginCourierTest {

    CreateCourier newCourier;
String loginCourier = "courierLogin_123"; //логин курьера
    String password = "12345"; //пароль  курьера
    String firstName = "Gonzo";

    @Before
    public void setUp() {
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
        newCourier = new CreateCourier(loginCourier, password, firstName);
       Steps.createCourier(newCourier);

 }

    @Test
    @DisplayName("loginCourierWithCorrectDataTest")
    @Description("Тест: аутентификация курьера с корректными данными")
    public void loginCourierTest() {

        LoginCourier loginData  = new LoginCourier(loginCourier, password);
       given()
                .header("Content-type", "application/json")
                .body(loginData)
                .when()
                .post(Api.COURIER_LOGIN)
                .then()
                .assertThat()
                .statusCode(equalTo(200));

    }


    @Test
    @DisplayName("loginCourierWithoutLoginTest")
    @Description("Тест аутентификации курьера без логина")
    public void loginCourierWithoutLoginTest() {

        LoginCourier loginData  = new LoginCourier("", password);

        given()
                .header("Content-type", "application/json")
                .body(loginData)
                .when()
                .post(Api.COURIER_LOGIN)
                .then()
                .assertThat()
                .statusCode(equalTo(400));
    }

    @Test
    @DisplayName("loginCourierWithoutPasswordTest")
    @Description("Тест аутентификации курьера без пароля")
    public void loginCourierWithoutPasswordTest() {

        LoginCourier loginData  = new LoginCourier(loginCourier, "");
        given()
                .header("Content-type", "application/json")
                .body(loginData)
                .when()
                .post(Api.COURIER_LOGIN)
                .then()
                .assertThat()
                .statusCode(equalTo(400));
    }

    @Test
    @DisplayName("loginCourierNotCorrectDataTest")
    @Description("Аутентификация с некорректными данными")
    public void loginCourierNotCorrectDataTest() {
        LoginCourier loginData  = new LoginCourier("ninja127772***", "1234");
                  given()
                        .header("Content-type", "application/json")
                        .body(loginData)
                        .when()
                        .post(Api.COURIER_LOGIN)
                        .then()
                        .assertThat()
                        .statusCode(equalTo(404));

    }


@After
public void cancelOrder() {
        Steps.deleteCourier(loginCourier, password);
}

}
