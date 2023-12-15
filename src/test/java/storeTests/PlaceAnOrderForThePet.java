package storeTests;

import base.BaseTestForStore;
import dto.models.StoreOrderModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.ResponseWrapper.ID_FIELD;
import static utils.utilsForStore.TestDataHelperForStore.STATUS_CODE_OK;
import static utils.utilsForStore.TestDataHelperForStore.VALID_ORDER_ID;
import static utils.utilsForStore.TestObjectBuilderForStore.getPlaceAnOrderInTheStore;

/**
 * Тест-сьют метода POST /store/order.
 */
@Epic("Store-controller")
@Feature("Place an order for a pet")
public class PlaceAnOrderForThePet extends BaseTestForStore {

    @Test
    @DisplayName("Place an order for a pet to the store. Positive case.")
    @Story(" позитивный сценарий.")
    public void PlaceAnOrderForThePetPositiveTest () {

        step("Создание запроса для добавления заказа с валидным ID", () -> {
            request = getPlaceAnOrderInTheStore(VALID_ORDER_ID);
        });

        step("Выполнение запроса POST /store/order", () -> {
            responseWrapper = steps.placeNewOrderToTheStore(request);

        });

        step("Проверка результата", () -> {

            int statusCode = responseWrapper.getStatusCode();
            StoreOrderModel response = responseWrapper.as(StoreOrderModel.class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body does not match")
                                .isEqualTo(request);
                    }
            );
        });

        step("Создание тела запроса для поиска созданного заказа по ID", () -> {
            responseWrapper = steps.getOrderById(VALID_ORDER_ID);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            String valueOrderId = responseWrapper.getFieldValue(ID_FIELD);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(valueOrderId)
                                .withFailMessage("Order ID does not match")
                                .isEqualTo(VALID_ORDER_ID);
                    }
            );
        });
    }
}
