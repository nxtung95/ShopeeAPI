package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.UnlistItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UnlistItemResponse extends BaseResponse {
	public List<UnlistItem> success = new ArrayList<>();
	public List<UnlistItem> failed = new ArrayList<>();
}
