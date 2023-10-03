package com.sasaug.monkeemods.services.shopee.model.v1.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetItemCategoriesRequest extends BaseRequest {
	public String language = "en";
}
