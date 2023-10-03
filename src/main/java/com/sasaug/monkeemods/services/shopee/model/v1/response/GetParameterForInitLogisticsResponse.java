package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetParameterForInitLogisticsResponse extends BaseResponse {

	public List<String> pickup;
	public List<String> dropoff;

	@SerializedName("non_integrated")
	public List<String> nonIntegrated;

}
