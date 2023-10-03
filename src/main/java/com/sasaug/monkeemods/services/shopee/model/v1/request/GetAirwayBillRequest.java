package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAirwayBillRequest extends BaseRequest {

	@SerializedName("ordersn_list")
	public List<String> orderIds = new ArrayList<>();

	@SerializedName("is_batch")
	public boolean isBatch = false;
}
