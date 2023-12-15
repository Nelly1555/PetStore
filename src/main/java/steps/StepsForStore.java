package steps;

import dto.models.StoreOrderModel;
import io.restassured.specification.RequestSpecification;
import utils.ResponseWrapper;

import static io.restassured.RestAssured.given;
import static utils.utilsForStore.TestDataHelperForStore.VALID_ORDER_ID;

/**
 * Класс с реализацией всех Rest шагов для "Store".
 */
public class StepsForStore {

    /**
     * Экземепляр спецификации RestAssured.
     */
    private final RequestSpecification requestSpecification;

    /**
     * Часть URL для запросов "/store/order".
     */
    private static final String STORE_ORDER_PATH = "store/order";

    /**
     * Часть URL для запросов с параметром "id".
     */
    private final static String FIND_BY_ID_PATH = "store/order/" + VALID_ORDER_ID;

    /**
     * Параметр "id" для запроса.
     */
    private final static String ID_PARAMETER = "id";

    /**
     * Конструктор для создания экземпляра класса.
     *
     * @param requestSpecification - спецификация RestAssured.
     */
    public StepsForStore(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * Метод для создания заказа.
     *
     * @param request - тело запроса.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper placeNewOrderToTheStore (StoreOrderModel request) {

        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_ORDER_PATH)
                .andReturn());
    }

    /**
     * Метод поиска заказа по id.
     *
     * @param id - id.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper getOrderById (String id) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .param(ID_PARAMETER, id)
                        .get(FIND_BY_ID_PATH)
                        .andReturn());
    }

    /**
     * Метод для удаления заказа по id.
     *
     * @param id - id
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper deleteOrderById (String id) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .param(ID_PARAMETER, id)
                        .delete(FIND_BY_ID_PATH)
                        .andReturn());
    }
}
