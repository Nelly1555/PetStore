package petTests;

import base.BaseTestForPet;
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
import static utils.utilsForPet.TestDataHelperForPet.*;
import static utils.utilsForPet.TestObjectBuilderForPet.getAddNewPetModel;

/**
 * Тест-сьют метода DELETE /pet.
 */
@Epic("Pet-controller")
@Feature("Delete a pet from the store by ID")
public class DeletePetFromTheStore extends BaseTestForPet {

    @BeforeEach
    public void initEach() {
        step("Создание запроса для добавления питомца с валидным ID", () -> {
            request = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = steps.createNewPetToTheStore(request);
        });
    }

    @Test
    @DisplayName("Delete a pet from the store. Positive case.")
    @Story("Удаление созданного питомца из магазина. Позитивный сценарий.")
    public void deletePetFromTheStorePositiveTest() {

        step("Создание тзапроса для удаления питомца по ID", () -> {
            responseWrapper = steps.deletePetById(VALID_PET_ID);
        });

        step("Выполнение запроса DELETE /pet", () -> {
            int statusCode = responseWrapper.getStatusCode();

            ExpectedResponse successfulDelete = responseWrapper.as(ExpectedResponse.class);
            ExpectedResponse successfulDeleteResponse = getSuccessfulDeleteResponse(VALID_PET_ID);

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
            responseWrapper = steps.getPetById(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ExpectedResponse errorDeleteResponse = responseWrapper.as(ExpectedResponse.class);
            ExpectedResponse expectedErrorDeleteResponse = getErrorAfterDeleteResponse(ERROR_PET_NOT_FOUND_MESSAGE);


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
