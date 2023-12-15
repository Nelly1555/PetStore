package utils.utilsForPet;

import com.github.javafaker.Faker;

/**
 * Вспомогательный класс с тестовыми данными для "Pet".
 */
public class TestDataHelperForPet {

    /**
     * Валидный идентификатор питомца.
     */
    public static final String VALID_PET_ID = "9936529387491037";

    /**
     * Невалидный идентификатор питомца.
     */
    public static final String NOT_VALID_PET_ID = "[a-z]{5}";

    /**
     * Валидный идентификатор категории.
     */
    public static final int VALID_CATEGORY_ID = 0;

    /**
     * Валидный обновлённый идентификатор категории.
     */
    public static final int UPDATED_VALID_CATEGORY_ID = 5;

    /**
     * Валидное обновлённое наименование категории.
     */
    public static final String UPDATED_CATEGORY_NAME = "Tibetan Mastiff";

    /**
     * Валидное обновлённое имя питомца.
     */
    public static final String UPDATED_NAME = "Maggie";

    /**
     * Валидный обновлённый URL.
     */
    public static final String UPDATED_PHOTO_URL = "http:\\tacto";

    /**
     * Валидный идентификатор тега.
     */
    public static final int VALID_TAG_ID = 0;

    /**
     * Валидное имя тега.
     */
    public static final String VALID_TAG_NAME = "tagOne";

    /**
     * Валидный статус питомца.
     */
    public static final String VALID_STATUS = "available";

    /**
     * Валидный обновлённый статус питомца.
     */
    public static final String UPDATED_VALID_STATUS = "sold";

    /**
     * Статус код успешного результата запроса.
     */
    public static final int STATUS_CODE_OK = 200;

    /**
     * Статус код неуспешного результата запроса.
     */
    public static final int STATUS_CODE_ERROR_500 = 500;

    /**
     * Статус код неуспешного результата запроса..
     */
    public static final int STATUS_CODE_ERROR_404 = 404;

    /**
     * Экземпляр класса Faker.
     */
    public static final Faker faker = new Faker();

    /**
     * Регулярное выражение для генерации случайного URL.
     */
    public static final String REGEXP_PHOTO_URL = "http:\\\\[a-z]{5}";

    /**
     * Невалидный статус.
     */
    public static final String NOT_VALID_STATUS = "[a-z]{5}";

    /**
     * Метод получения случайного невалидного "id" питомца.
     *
     * @return - случайное невалидное "id" питомца.
     */
    public static String getRandomNotValidPetId () {
        return faker.regexify(NOT_VALID_PET_ID);
    }

    /**
     * Метод получения случайного имени категории.
     *
     * @return - случайное имя категории.
     */
    public static String getRandomCategoryName () {
        return faker.dog().breed();
    }

    /**
     * Метод получения случайного имени питомца.
     *
     * @return - случайное имя питомца.
     */
    public static String getRandomPetName () {
        return faker.dog().name();
    }

    /**
     * Метод получения случайного URL.
     *
     * @return - случайный URL.
     */
    public static String getRandomUrl () {
        return faker.regexify(REGEXP_PHOTO_URL);
    }

    /**
     * Метод получения случайного невалидного статуса.
     *
     * @return - случайный невалидный статус.
     */
    public static String getRandomNotValidStatus () {
        return faker.regexify(NOT_VALID_STATUS);
    }
}
