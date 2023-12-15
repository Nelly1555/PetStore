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
import static utils.ResponseWrapper.ID_FIELD;
import static utils.utilsForPet.TestDataHelperForPet.STATUS_CODE_OK;
import static utils.utilsForPet.TestDataHelperForPet.VALID_PET_ID;
import static utils.utilsForPet.TestObjectBuilderForPet.getAddNewPetModel;

/**
 * Тест-сьют метода GET /pet/findById.
 */
@Epic("Pet-controller")
@Feature("Find pet by Id")
public class FindPetById extends BaseTestForPet {

    @BeforeEach
    public void initEach() {

        request = getAddNewPetModel(VALID_PET_ID);
        responseWrapper = steps.createNewPetToTheStore(request);
    }

    @Test
    @DisplayName("Find pet by ID. Positive case.")
    @Story("Поиск питомца по ID. Позитивный сценарий.")
    public void findPetsByIdPositiveTest() {

        responseWrapper = steps.getPetById(VALID_PET_ID);

        int statusCode = responseWrapper.getStatusCode();
        PetModel response = responseWrapper.as(PetModel.class);
        String valuePetId = responseWrapper.getFieldValue(ID_FIELD);

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
                            .assertThat(valuePetId)
                            .withFailMessage("ID does not match")
                            .isEqualTo(VALID_PET_ID);
                    }
        );
    }
}
