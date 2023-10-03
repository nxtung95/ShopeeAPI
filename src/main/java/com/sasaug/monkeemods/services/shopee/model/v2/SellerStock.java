package com.sasaug.monkeemods.services.shopee.model.v2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerStock {

	@SerializedName(value = "location_id")
	public String locationId;

	public int stock;


}
