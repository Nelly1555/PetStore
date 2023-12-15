package petTests;

import base.BaseTestForPet;
import dto.models.PetModel;
import dto.response.ExpectedResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.ExpectedResponseObject.getInputWrongIdResponse;
import static utils.ResponseWrapper.ID_FIELD;
import static utils.utilsForPet.TestDataHelperForPet.*;
import static utils.utilsForPet.TestObjectBuilderForPet.getAddNewPetModel;

/**
 * Тест-сьют метода POST /pet.
 */
@Epic("Pet-controller")
@Feature("Add new pet to the store")
public class AddNewPetToTheStore extends BaseTestForPet {

    @Test
    @DisplayName("Add new pet to the store. Positive case.")
    @Story("Добавление нового питомца в магазин. Позитивный сценарий.")
    public void addNewPetToTheStorePositiveTest() {

        request = getAddNewPetModel(VALID_PET_ID);

        responseWrapper = steps.createNewPetToTheStore(request);

        int statusCode = responseWrapper.getStatusCode();
        PetModel response = responseWrapper.as(PetModel.class);

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

        responseWrapper = steps.getPetById(VALID_PET_ID);

        int newStatusCode = responseWrapper.getStatusCode();
        String valuePetId = responseWrapper.getFieldValue(ID_FIELD);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(newStatusCode)
                            .withFailMessage("Status code does not match")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(valuePetId)
                            .withFailMessage("PetId does not match")
                            .isEqualTo(VALID_PET_ID);
                }
        );
    }

   @Test
   @DisplayName("Add new pet to the store. Negative case with not valid ID")
   @Story("Добавление нового питомца с невалидным ID. Негативный сценарий")
   public void addNewPetToTheStoreNegativeTest () {

        request = getAddNewPetModel(getRandomNotValidPetId());

        responseWrapper = steps.createNewPetToTheStore(request);

        int statusCode = responseWrapper.getStatusCode();

        ExpectedResponse errorId = responseWrapper.as(ExpectedResponse.class);
        ExpectedResponse expectedErrorId = getInputWrongIdResponse();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(statusCode)
                            .withFailMessage("Status code does not match")
                            .isEqualTo(STATUS_CODE_ERROR_500);
                    softAssertions
                            .assertThat(errorId)
                            .withFailMessage("Error body does not match")
                            .isEqualTo(expectedErrorId);
                }
         );
    }
}
