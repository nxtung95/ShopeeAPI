package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.UpdatePricePriceListModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.UpdateStockStockListModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemStockRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "stock_list")
	public List<UpdateStockStockListModel> stockList = new ArrayList<>();

}
