import POJO.Order;
import POJO.OrderCancel;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String[] color;
    String trackId;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }


    @Before
    public void setUp() {

        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }


    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                 {new String[]{"GRAY"}},
                   {new String[]{"BLACK", "GRAY"}},
                {new String[]{""}}
        };
    }

    @Test
    @DisplayName("makeOrderTest")
    @Description("Тест: формирование заказа") // описание теста
    public void makeOrderTest() {


        Order order = new Order(color);


        String response = given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(Api.ORDER_CREATE)
                .then()
                .assertThat()
                .statusCode(equalTo(201))
                .body("track", notNullValue())
                .extract()
                .response()
                .getBody()
                .asString();

        JsonPath jsonPath = new JsonPath(response);
        String trackId = jsonPath.getString("track");
        System.out.println("Track: " + trackId);
    }


}

