package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetOrderDetailsRequest extends BaseRequest {
	@SerializedName(value = "order_sn_list")
	public List<String> orderIdsList = new ArrayList<>();
}
