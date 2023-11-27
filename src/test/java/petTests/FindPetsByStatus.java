package petTests;

import base.BaseTestForPet;
import dto.models.PetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.utilsForPet.TestDataHelperForPet.*;
import static utils.utilsForPet.TestObjectBuilderForPet.getAddNewPetModel;

/**
 * Тест-сьют метода GET /pet/findByStatus.
 */
@Epic("Pet-controller")
@Feature("Finds pet by status")
public class FindPetsByStatus extends BaseTestForPet {

    @BeforeEach
    public void initEach() {
        step("Создание тела запроса для добавления питомца с валидным ID", () -> {
            request = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = steps.createNewPetToTheStore(request);
        });
    }

    @Test
    @DisplayName("Find pets by status. Positive case.")
    @Story("Поиск питомцев по статусу. Позитивный сценарий.")
    public void findPetsByStatusPositiveTest() {

        step("Создание тела запроса для поиска питомца по валидному статусу", () -> {
            responseWrapper = steps.getPetByStatus(VALID_STATUS);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            List<PetModel> response = responseWrapper.asList(PetModel[].class);
            boolean finalIsFound = responseWrapper.isPetFound(response, VALID_PET_ID);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match.")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body is empty.")
                                .isNotEmpty();
                        softAssertions
                                .assertThat(finalIsFound)
                                .withFailMessage("Desired pet not found in the response.")
                                .isTrue();
                    }
            );
        });
    }

    @Test
    @DisplayName("Find pets by status. Negative case, if status does not exist.")
    @Story("Поиск питомцев с несуществующим статусом. Негативный сценарий.")
    public void findPetsByStatusIfStatusIsNullNegativeTest() {

        step("Создание тела запроса для поиска питомца по невалидному статусу", () -> {
            responseWrapper = steps.getPetByStatus(getRandomNotValidStatus());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            List<PetModel> response = responseWrapper.asList(PetModel[].class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code does not match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Error body is not empty")
                                .isEmpty();
                    }
            );
        });
    }
}

