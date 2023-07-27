import io.restassured.response.Response;
import pojo.Order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String[] color;

    Order order;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }


    @Before
    public void setUp() {

        RestAssured.baseURI = Uri.URI;
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

        order = new Order("Вася", "Пупкин", "куда-нибудь", "Петровско-Разумовская", "322226444", 1, "2023-07-25", "коммент", color);
        Response response = Steps.newOrder(order);
        response.then()
                .assertThat().statusCode(equalTo(201)).body("track", notNullValue());


    }


}

