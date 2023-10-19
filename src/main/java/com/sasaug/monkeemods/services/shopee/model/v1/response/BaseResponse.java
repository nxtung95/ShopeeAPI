package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class BaseResponse {
	@SerializedName(value = "request_id")
	public String requestId;

	public String error;
	public String msg;
	public String warning;
	public String message;
}
