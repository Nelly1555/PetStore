package base;

import config.BaseConfig;
import dto.models.StoreOrderModel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import steps.StepsForStore;
import utils.ResponseWrapper;

/**
 * Базовый тестовый класс с общими настройками для работы со "Store".
 */
public class BaseTestForStore {

    /**
     * Экземпляр интерфейса с конфигурацией.
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с REST шагами.
     */
    protected final StepsForStore steps = new StepsForStore(getRequestSpecification());

    /**
     * Экземпляр класс с телом запроса.
     */
    protected StoreOrderModel request;

    /**
     * Экземпляр класса с оболочкой ответа.
     */
    protected ResponseWrapper responseWrapper;

    /**
     * Метод для получения спецификации RestAssured.
     * @return - спецификация.
     */
    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
