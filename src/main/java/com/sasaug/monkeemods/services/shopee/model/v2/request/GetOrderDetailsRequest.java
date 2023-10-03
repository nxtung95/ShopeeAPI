package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDetailsRequest extends BaseRequest {
	@SerializedName(value = "order_sn_list")
	public List<String> orderIdsList = new ArrayList<>();
}
