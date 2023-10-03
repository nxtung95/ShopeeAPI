package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAccessTokenRequest extends BaseRequest {
	@SerializedName(value = "code")
	public String code;
}
