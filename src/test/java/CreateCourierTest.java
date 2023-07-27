import pojo.CreateCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class CreateCourierTest {

    CreateCourier newCourier;

    //данные создаваемого курьера
    String loginCourier = "courierLogin_17"; //логин создаваемого курьера
    String password = "12345"; //пароль создаваемого курьера
    String firstName = "Gonzo";

    @Before
    public void setUp() {

        RestAssured.baseURI = Uri.URI;

    }

    @Test
    @DisplayName("createCourierTest")
    @Description("Тест: Создание нового курьера с корректными данными")
    public void createCourierTest() {
       newCourier = new CreateCourier(loginCourier, password, firstName);
       Response response = Steps.createCourier(newCourier);
       response.then()
               .assertThat().statusCode(equalTo(201))
               .assertThat().body("ok", is(true));
         Steps.deleteCourier(loginCourier, password);

    }

    @Test
    @DisplayName("createSameCourierTest")
    @Description("Тест: попытка создать курьера с таким же логином")
    public void createSameCourierTest() {
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(201));
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Response responseThatCourierIsNotCreate = Steps.createCourier(newCourier);
        responseThatCourierIsNotCreate.then().assertThat().statusCode(equalTo(409));
        Steps.deleteCourier(loginCourier, password);
    }

    @Test
    @DisplayName("createCourierWithoutLoginTest")
    @Description("Создание нового курьера без логина")
    public void createCourierWithoutLoginTest() {
       newCourier = new CreateCourier(null, password, firstName);
       Response response = Steps.createCourier(newCourier);
       response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutPasswordTest")
    @Description("Создание нового курьера без пароля")
    public void createCourierWithoutPasswordTest() {
        newCourier = new CreateCourier(loginCourier, null, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutLoginAndPasswordTest")
    @Description("Создание нового курьера без логина и пароля")
    public void createCourierWithoutLoginAndPasswordTest() {
        CreateCourier newCourier = new CreateCourier("", "", firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

}

