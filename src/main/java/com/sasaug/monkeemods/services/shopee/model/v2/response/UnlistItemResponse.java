package com.sasaug.monkeemods.services.shopee.model.v2.response;

import java.util.ArrayList;
import java.util.List;

import com.sasaug.monkeemods.services.shopee.model.v1.UnlistItem;
import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import lombok.Getter;

@Getter
public class UnlistItemResponse extends BaseResponse {
	public List<UnlistItem> success = new ArrayList<>();
	public List<UnlistItem> failed = new ArrayList<>();
}
