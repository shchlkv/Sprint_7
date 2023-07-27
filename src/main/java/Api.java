//справочно: соответствие статусов
//          - 200 OK: HttpStatus.SC_OK
//        - 201 Created: HttpStatus.SC_CREATED
//        - 204 No Content: HttpStatus.SC_NO_CONTENT
//        - 400 Bad Request: HttpStatus.SC_BAD_REQUEST
//        - 401 Unauthorized: HttpStatus.SC_UNAUTHORIZED
//        - 403 Forbidden: HttpStatus.SC_FORBIDDEN
//        - 404 Not Found: HttpStatus.SC_NOT_FOUND
//        - 500 Internal Server Error: HttpStatus.SC_INTERNAL_SERVER_ERROR

public class Api {
    public static final String COURIER_CREATE = "/api/v1/courier";
    public static final String COURIER_DELETE = "/api/v1/courier/";
    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String ORDER_CREATE = "/api/v1/orders";
    public static final String ORDER_CANCEL = "/api/v1/orders/cancel";



}
