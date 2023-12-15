package storeTests;

import base.BaseTestForStore;
import dto.response.ExpectedResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.ExpectedResponseObject.*;
import static utils.utilsForPet.TestDataHelperForPet.STATUS_CODE_ERROR_404;
import static utils.utilsForPet.TestDataHelperForPet.STATUS_CODE_OK;
import static utils.utilsForStore.TestDataHelperForStore.VALID_ORDER_ID;
import static utils.utilsForStore.TestObjectBuilderForStore.getPlaceAnOrderInTheStore;


/**
 * Тест-сьют метода DELETE /store/order.
 */
@Epic("Store-controller")
@Feature("Delete purchase order from the store by ID")
public class DeleteOrderFromTheStore extends BaseTestForStore {

    @BeforeEach
    public void initEach() {
        step("Создание запроса для добавления заказа с валидным ID", () -> {
            request = getPlaceAnOrderInTheStore(VALID_ORDER_ID);
        });

        step("Выполнение запроса POST /store/order", () -> {
            responseWrapper = steps.placeNewOrderToTheStore(request);
        });
    }

    @Test
    @DisplayName("Delete purchase order from the store by ID. Positive case.")
    @Story("Удаление заказа из магазина по ID. позитивный сценарий.")
    public void deleteOrderFromStorePositiveTest() {

        step("Создание запроса для удаления заказа по ID", () -> {
            responseWrapper = steps.deleteOrderById(VALID_ORDER_ID);
        });

        step("Выполнение запроса DELETE /store/order", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ExpectedResponse successfulDelete = responseWrapper.as(ExpectedResponse.class);
            ExpectedResponse successfulDeleteResponse = getSuccessfulDeleteResponse(VALID_ORDER_ID);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(successfulDelete)
                                .withFailMessage("Response body does not match")
                                .isEqualTo(successfulDeleteResponse);
                    }
            );
        });

        step("Создание запроса для поиска удаленного питомца по ID", () -> {
            responseWrapper = steps.getOrderById(VALID_ORDER_ID);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ExpectedResponse errorDeleteResponse = responseWrapper.as(ExpectedResponse.class);
            ExpectedResponse expectedErrorDeleteResponse = getErrorAfterDeleteResponse(ERROR_ORDER_NOT_FOUND_MESSAGE);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match")
                                .isEqualTo(STATUS_CODE_ERROR_404);
                        softAssertions
                                .assertThat(errorDeleteResponse)
                                .withFailMessage("Response body does not match")
                                .isEqualTo(expectedErrorDeleteResponse);
                    }
            );
        });
    }
}
