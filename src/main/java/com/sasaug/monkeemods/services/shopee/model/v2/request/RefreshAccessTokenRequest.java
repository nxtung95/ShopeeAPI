package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshAccessTokenRequest extends BaseRequest {
	@SerializedName(value = "refresh_token")
	public String refreshToken;
}
