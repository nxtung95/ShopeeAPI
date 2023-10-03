package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.ItemAttribute;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetItemAttributesResponse extends BaseResponse {

	public List<ItemAttribute> attributes = new ArrayList<>();

}
