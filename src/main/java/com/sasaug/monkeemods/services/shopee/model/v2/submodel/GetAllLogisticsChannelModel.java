package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllLogisticsChannelModel {

	@SerializedName(value = "logistics_channel_id")
	public long logisticsChannelId;

	@SerializedName(value = "preferred")
	public Boolean preferred;

	@SerializedName(value = "logistics_channel_name")
	public String logisticsChannelName;

	@SerializedName("cod_enabled")
	public Boolean codEnabled;

	@SerializedName("enabled")
	public Boolean enabled;

	@SerializedName("fee_type")
	public String feeType;

	@SerializedName("size_list")
	private List<Object> sizeList;

	@SerializedName("weight_limit")
	private Object weightLimit;

	@SerializedName("item_max_dimension")
	private Object itemMaxDimension;

	@SerializedName("volume_limit")
	private Object volumeLimit;

	@SerializedName("logistics_description")
	private String logisticsDescription;

	@SerializedName("force_enable")
	private Boolean forceEnable;

	@SerializedName("mask_channel_id")
	private int maskChannelId;
}
