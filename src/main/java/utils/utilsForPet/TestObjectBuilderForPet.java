package utils.utilsForPet;

import dto.models.PetModel;
import java.util.List;

import static utils.utilsForPet.TestDataHelperForPet.*;

/**
 * Вспомогательный класс для создания тестовых объектов питомцев.
 */
public class TestObjectBuilderForPet {

    /**
     * Метод для формирования тела запроса создания питомца.
     *
     * @param VALID_PET_ID- идентификатор питомца.
     * @return - тело запроса.
     */

    public static PetModel getAddNewPetModel (String VALID_PET_ID) {
        return PetModel.builder()
                .id(VALID_PET_ID)
                .category(PetModel.CategoryAndTagsItem.builder()
                        .id (VALID_CATEGORY_ID)
                        .name(getRandomCategoryName())
                        .build())
                .name(getRandomPetName())
                .photoUrls(List.of(getRandomUrl()))
                .tags(List.of(
                        PetModel.CategoryAndTagsItem.builder()
                                .id(VALID_TAG_ID)
                                .name(VALID_TAG_NAME)
                                .build()))
                .status(VALID_STATUS)
                .build();
    }

    /**
     * Метод для формирования тела запроса изменения питомца.
     *
     * @param VALID_PET_ID- идентификатор питомца.
     * @return - тело запроса.
     */

    public static PetModel getPutUpdatedPetModel (String VALID_PET_ID) {
        return PetModel.builder()
                .id(VALID_PET_ID)
                .category(PetModel.CategoryAndTagsItem.builder()
                        .id (UPDATED_VALID_CATEGORY_ID)
                        .name(UPDATED_CATEGORY_NAME)
                        .build())
                .name(UPDATED_NAME)
                .photoUrls(List.of(UPDATED_PHOTO_URL))
                .tags(List.of(
                        PetModel.CategoryAndTagsItem.builder()
                                .id(VALID_TAG_ID)
                                .name(VALID_TAG_NAME)
                                .build()))
                .status(UPDATED_VALID_STATUS)
                .build();
    }
}
