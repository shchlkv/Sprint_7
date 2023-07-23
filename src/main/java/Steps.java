import POJO.CreateCourier;
import POJO.DeleteCourier;
import POJO.LoginCourier;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Steps {

    public Steps() {
    }

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
        System.out.println(response.body().asString());//отладка
        System.out.println(courierId);
        if (courierId != null) {
            //и удаляем
            DeleteCourier deleteCourier = new DeleteCourier();
            Response response2 = given()
                    .header("Content-type", "application/json")
                    .body(deleteCourier)
                    .when()
                    .delete(Api.COURIER_DELETE + courierId);
            System.out.println("курьер удален " +response2.body().asString());  //отладка
        }
    }

}


