package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemBase;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemList;
import lombok.Getter;

@Getter
public class GetItemBaseInfoResponseModel {
	@SerializedName("item_list")
	public List<ItemBase> itemList = new ArrayList<>();
}
