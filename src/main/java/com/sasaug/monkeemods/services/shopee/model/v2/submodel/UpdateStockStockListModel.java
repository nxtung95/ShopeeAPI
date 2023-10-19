package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.SellerStock;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockStockListModel {

	@SerializedName("model_id")
	public long id;

//	@SerializedName("normal_stock")
//	public int stock;

	@SerializedName("seller_stock")
	public List<SellerStock> sellerStock = new ArrayList<>();

}
