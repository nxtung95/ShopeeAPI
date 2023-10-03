package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetLogisticsChannelResponseModel {
	@SerializedName("logistics_channel_list")
	public List<GetAllLogisticsChannelModel> logisticsList = new ArrayList<>();
}
