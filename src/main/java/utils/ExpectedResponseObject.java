package utils;

import dto.response.ExpectedResponse;

/**
 * Вспомогательный класс для формирования ожидаемого результата при успешном удалении объекта из магазина.
 */
public class ExpectedResponseObject {

    /**
     * Код ответа при успешном удалении объекта из магазина.
     */
    private static final int CODE_200 = 200;

    /**
     * Код ошибки "1".
     */
    private static final int ERROR_CODE_1 = 1;

    /**
     * Код ошибки "500".
     */
    private static final int ERROR_CODE_500 = 500;

    /**
     * Тип ответа при успешном удалении объекта из магазина.
     */
    private static final String UNKNOWN_TYPE = "unknown";

    /**
     * Тип ошибки "error".
     */
    private static final String ERROR_TYPE = "error";

    /**
     * Сообщение ошибки при запросе несуществующего питомца.
     */
    public static final String ERROR_PET_NOT_FOUND_MESSAGE = "Pet not found";

    /**
     * Сообщение ошибки при запросе несуществующего заказа.
     */
    public static final String ERROR_ORDER_NOT_FOUND_MESSAGE = "Order not found";

    /**
     * Сообщение ошибки при вводе неправильного ID.
     */
    private static final String ERROR_WRONG_ID_MESSAGE = "something bad happened";

    /**
     * Метод формирования ожидаемого результата при успешном удалении объекта из магазина.
     *
     * @return - тело ошибки.
     */
    public static ExpectedResponse getSuccessfulDeleteResponse(String id) {
        return ExpectedResponse.builder()
                .code(CODE_200)
                .type(UNKNOWN_TYPE)
                .message(id)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата ошибки при удалении несуществующего объекта.
     *
     * @return - тело ошибки.
     */
    public static ExpectedResponse getErrorAfterDeleteResponse(String message) {
        return ExpectedResponse.builder()
                .code(ERROR_CODE_1)
                .type(ERROR_TYPE)
                .message(message)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата ошибки при вводе неправильного ID.
     *
     * @return - тело ошибки.
     */
    public static ExpectedResponse getInputWrongIdResponse() {
        return ExpectedResponse.builder()
                .code(ERROR_CODE_500)
                .type(UNKNOWN_TYPE)
                .message(ERROR_WRONG_ID_MESSAGE)
                .build();
    }
}

