package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.UpdatePricePriceListModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetModelListRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long id;

}
