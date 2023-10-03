package com.sasaug.monkeemods.services.shopee.model.v1.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UpdateItemImageResponse extends BaseResponse {
	public List<String> images = new ArrayList<>();
}
