import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;


public class GetOrderTest {

    @Before
    public void setUp() {

        RestAssured.baseURI = Uri.URI;
    }

    @Test
    @DisplayName("getOrderListTest")
    @Description("Тест: получение списка заказов")
    public void getOrderListTest() {
        given()
                .header("Content-type", "application/json")
                .when()
                .get(Api.ORDER_CREATE)
                .then()
                .statusCode(200)
                .assertThat()
                .body("orders",  not(empty()));

    }
}
