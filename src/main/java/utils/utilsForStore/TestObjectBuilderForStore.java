package utils.utilsForStore;

import dto.models.StoreOrderModel;
import static utils.utilsForStore.TestDataHelperForStore.*;

public class TestObjectBuilderForStore {

    public static StoreOrderModel getPlaceAnOrderInTheStore(String id) {
        return StoreOrderModel.builder()
                .id(VALID_ORDER_ID)
                .petId(VALID_ORDER_PET_ID)
                .quantity(VALID_QUANTITY_IN_ORDER)
                .shipDate(VALID_SHIP_DATE)
                .status(VALID_ORDER_STATUS)
                .complete(IS_ORDER_COMPLETE)
                .build();
    }
}
