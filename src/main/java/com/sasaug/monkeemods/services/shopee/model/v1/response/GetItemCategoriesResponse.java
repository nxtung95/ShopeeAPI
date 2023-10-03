package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetItemCategoriesResponse extends BaseResponse {

	public List<Category> categories = new ArrayList<>();

}
