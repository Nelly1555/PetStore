package utils;

import dto.models.PetModel;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Вспомогательный класс, оболочка для работы с ответами.
 */
@AllArgsConstructor
public class ResponseWrapper {

    /**
     * Ответ в RestAssured.
     */
    private final Response response;

    /**
     * Наименование поля "id" из тела ответа.
     */
    public static final String ID_FIELD = "id";

    /**
     * Наименование поля "category.id" из тела ответа.
     */
    public static final String CATEGORY_ID_FIELD = "category.id";

    /**
     * Наименование поля "category.name" из тела ответа.
     */
    public static final String CATEGORY_NAME_FIELD = "category.name";

    /**
     * Наименование поля "name" из тела ответа.
     */
    public static final String NAME_FIELD = "name";

    /**
     * Наименование поля "photoUrls" из тела ответа.
     */
    public static final String PHOTO_URL_FIELD = "photoUrls[0]";

    /**
     * Наименование поля "status" из тела ответа.
     */
    public static final String STATUS_FIELD = "status";

    /**
     * Метод преобразования тела к объекту.
     *
     * @param Clazz - класс объекта.
     * @param <T> - тип данных объекта.
     * @return - объект.
     */
    public <T> T as(Class<T> Clazz) {
        return response.as(Clazz);
    }

    /**
     * Метод преобразования тела ответа к списку объектов.
     *
     * @param Clazz - массив классов объекта.
     * @param <T> - тип данных объекта.
     * @return - список объектов.
     */
    public <T> List<T> asList(Class<T[]> Clazz) {
        return List.of(response.as(Clazz));
    }

    /**
     * Метод получения статус кода ответа.
     *
     * @return - статус код.
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * Метод для поиска питомца по ID в списке питомцев.
     *
     * @param petList - список объектов PetModel, в котором осуществляется поиск
     * @param validPetId - ID питомца, которого нужно найти в списке.
     * @return - true, если элемент с заданным идентификатором найден в списке,
     *           в противном случае - false.
     */

    public boolean isPetFound(List<PetModel> petList, String validPetId) {
        boolean isFound = false;
        for (PetModel pet : petList) {
            if (pet.getId().equals(validPetId)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    /**
     * Метод получения значения полей из тела ответа.
     *
     * @return - значение поля.
     */
    public String getFieldValue(String field) {
        ResponseBody body = response.getBody();
        String value = body.jsonPath().getString(field);
        return value;
    }
}

