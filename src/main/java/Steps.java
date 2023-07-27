import pojo.CreateCourier;
import pojo.DeleteCourier;
import pojo.LoginCourier;
import io.restassured.response.Response;
import pojo.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class Steps {

     //  Создать курьера
    public static Response createCourier(CreateCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(Api.COURIER_CREATE);
        return response;
    }

 //удалить курьера
    public static void deleteCourier(String loginCourier, String password) {
        String courierId;

        //логинимся под созданным курьером чтобы вычилить id
        LoginCourier loginData = new LoginCourier(loginCourier, password);
        Response response =
                given()
                     .header("Content-type", "application/json")
                     .body(loginData)
                     .when()
                     .post(Api.COURIER_LOGIN);
        response.then().assertThat().statusCode(equalTo(200));
        // и вычисляем id
        courierId = response.jsonPath().getString("id");

        if (courierId != null) {
            //и удаляем
            DeleteCourier deleteCourier = new DeleteCourier();
            Response responseDelCourier = given()
                    .header("Content-type", "application/json")
                    .body(deleteCourier)
                    .when()
                    .delete(Api.COURIER_DELETE + courierId);
            System.out.println("курьер удален " +responseDelCourier.body().asString());  //отладка
        }
    }

        //вынос ручки

    public static Response newOrder(Order order) {
        Response response = given()
               .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(Api.ORDER_CREATE);
        return response;

    }

    public static Response loginCourier(LoginCourier loginData) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginData)
                .when()
                .post(Api.COURIER_LOGIN);
        return response;

    }

    }




