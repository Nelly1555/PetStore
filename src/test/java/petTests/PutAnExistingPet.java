package petTests;

import base.BaseTestForPet;
import dto.models.PetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.ResponseWrapper.*;
import static utils.utilsForPet.TestDataHelperForPet.*;
import static utils.utilsForPet.TestObjectBuilderForPet.getAddNewPetModel;
import static utils.utilsForPet.TestObjectBuilderForPet.getPutUpdatedPetModel;

/**
 * Тест метода PUT /pet.
 */
@Epic("Pet-controller")
@Feature("Update an existing pet.")
public class PutAnExistingPet extends BaseTestForPet {

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
    @DisplayName("Update an existing pet. Positive case.")
    @Story("Обновление параметров созданного питомца. Позитивный сценарий.")
    public void PutAnExistingPetPositiveTest() {

        step("Обновление тела запроса с валидным ID", () -> {
            request = getPutUpdatedPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса PUT /pet", () -> {
            responseWrapper = steps.putAnExistingPet(request);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            PetModel response = responseWrapper.as(PetModel.class);

            int updatedCategoryId = Integer.parseInt(responseWrapper.getFieldValue(CATEGORY_ID_FIELD));
            String updatedCategoryName = responseWrapper.getFieldValue(CATEGORY_NAME_FIELD);
            String updatedName = responseWrapper.getFieldValue(NAME_FIELD);
            String updatedPhotoUrl = responseWrapper.getFieldValue(PHOTO_URL_FIELD);
            String updatedStatus = responseWrapper.getFieldValue(STATUS_FIELD);

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
                        softAssertions
                                .assertThat(updatedCategoryId)
                                .withFailMessage("The value of the \"category.id\" field does not match.")
                                .isEqualTo(UPDATED_VALID_CATEGORY_ID);
                        softAssertions
                                .assertThat(updatedCategoryName)
                                .withFailMessage("The value of the \"category.name\" field does not match.")
                                .isEqualTo(UPDATED_CATEGORY_NAME);
                        softAssertions
                                .assertThat(updatedName)
                                .withFailMessage("The value of the \"name\" field does not match.")
                                .isEqualTo(UPDATED_NAME);
                        softAssertions
                                .assertThat(updatedPhotoUrl)
                                .withFailMessage("The value of the \"photoUrl\" field does not match.")
                                .isEqualTo(UPDATED_PHOTO_URL);
                        softAssertions
                                .assertThat(updatedStatus)
                                .withFailMessage("The value of the \"status\" field does not match.")
                                .isEqualTo(UPDATED_VALID_STATUS);
                    }
            );
        });
    }
}
