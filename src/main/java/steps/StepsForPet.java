package steps;

import dto.models.PetModel;
import io.restassured.specification.RequestSpecification;
import utils.ResponseWrapper;

import static io.restassured.RestAssured.given;
import static utils.utilsForPet.TestDataHelperForPet.VALID_PET_ID;

/**
 * Класс с реализацией всех Rest шагов для "Pet".
 */
public class StepsForPet {

    /**
     * Экземепляр спецификации RestAssured.
     */
    private final RequestSpecification requestSpecification;

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    /**
     * Часть URL для запросов "/pet".
     */
    private final static String PET_PATH = "pet";

    /**
     * Часть URL для запросов /findByStatus.
     */
    private final static String FIND_BY_STATUS_PATH = "pet/findByStatus";

    /**
     * Часть URL для запросов с параметром "id".
     */
    private final static String FIND_BY_ID_PATH = "pet/" + VALID_PET_ID;

    /**
     * Параметр "status" для запроса.
     */
    private final static String STATUS_PARAMETER = "status";

    /**
     * Параметр "id" для запроса.
     */
    private final static String ID_PARAMETER = "id";

    /**
     * Конструктор для создания экземпляра класса.
     *
     * @param requestSpecification - спецификация RestAssured.
     */
    public StepsForPet(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * Метод для создания питомца.
     *
     * @param request - тело запроса.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper createNewPetToTheStore (PetModel request) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .body(request)
                        .post(PET_PATH)
                        .andReturn());
    }

    /**
     * Метод для обновления параметров питомца.
     *
     * @param request - тело запроса.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper putAnExistingPet(PetModel request) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .body(request)
                        .put(PET_PATH)
                        .andReturn());
    }

    /**
     * Метод поиска питомцев по статусу.
     *
     * @param status - статус.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper getPetByStatus (String status) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .param(STATUS_PARAMETER, status)
                        .get(FIND_BY_STATUS_PATH)
                        .andReturn());
    }

    /**
     * Метод поиска питомца по id.
     *
     * @param id - id.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper getPetById (String id) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .param(ID_PARAMETER, id)
                        .get(FIND_BY_ID_PATH)
                        .andReturn());
    }

    /**
     * Метод для удаления питомца по id.
     *
     * @param id - id.
     * @return - оболочка для работы с ответом.
     */
    public ResponseWrapper deletePetById (String id) {
        return new ResponseWrapper(
                given(requestSpecification)
                        .when()
                        .param(ID_PARAMETER, id)
                        .delete(FIND_BY_ID_PATH)
                        .andReturn());
    }
}
