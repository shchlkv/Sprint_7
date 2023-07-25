import io.restassured.response.Response;
import pojo.CreateCourier;
import pojo.LoginCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class LoginCourierTest {

    CreateCourier newCourier;
    LoginCourier loginData;
String loginCourier = "courierLogin_123"; //логин курьера
    String password = "12345"; //пароль  курьера
    String firstName = "Gonzo";

    @Before
    public void setUp() {
        RestAssured.baseURI= Uri.URI;
        newCourier = new CreateCourier(loginCourier, password, firstName);
       Steps.createCourier(newCourier);

 }

    @Test
    @DisplayName("loginCourierWithCorrectDataTest")
    @Description("Тест: аутентификация курьера с корректными данными")
    public void loginCourierTest() {

        loginData  = new LoginCourier(loginCourier, password);
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(200));

    }


    @Test
    @DisplayName("loginCourierWithoutLoginTest")
    @Description("Тест: аутентификация курьера без логина")
    public void loginCourierWithoutLoginTest() {

        loginData  = new LoginCourier("", password);
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(400));

    }

    @Test
    @DisplayName("loginCourierWithoutPasswordTest")
    @Description("Тест аутентификации курьера без пароля")
    public void loginCourierWithoutPasswordTest() {

        LoginCourier loginData  = new LoginCourier(loginCourier, "");
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("loginCourierNotCorrectDataTest")
    @Description("Аутентификация с некорректными данными")
    public void loginCourierNotCorrectDataTest() {
         loginData  = new LoginCourier("ninja127772***", "1234");

        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(404));


    }


@After
public void deleteCourier() {

        Steps.deleteCourier(loginCourier, password);
}

}
