package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GetAccessTokenResponse extends BaseResponse {

	@SerializedName(value = "refresh_token")
	public String refreshToken;

	@SerializedName(value = "access_token")
	public String accessToken;

	@SerializedName(value = "expire_in")
	public long expireIn;

}
