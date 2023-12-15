package dto.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(exclude = {"shipDate"})
public class StoreOrderModel {

	private String id;
	private String petId;
	private int quantity;
	private String shipDate;
	private String status;
	private boolean complete;
}

