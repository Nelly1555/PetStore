package base;

import config.BaseConfig;
import dto.models.PetModel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import steps.StepsForPet;
import utils.ResponseWrapper;

/**
 * Базовый тестовый класс с общими настройками для работы с "Pet".
 */
public class BaseTestForPet {

    /**
     * Экземпляр интерфейса с конфигурацией.
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с REST шагами.
     */
    protected final StepsForPet steps = new StepsForPet(getRequestSpecificationForPet());

    /**
     * Экземпляр класс с телом запроса.
     */
    protected PetModel request;

    /**
     * Экземпляр класса с оболочкой ответа.
     */
    protected ResponseWrapper responseWrapper;

    /**
     * Метод для получения спецификации RestAssured.
     * @return - спецификация.
     */
    private RequestSpecification getRequestSpecificationForPet() {
        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
