package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetItemsListResponse extends BaseResponse {

	public List<ItemData> items = new ArrayList<>();

	public boolean more;
	public int total;
}
