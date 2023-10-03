package com.sasaug.monkeemods.services.shopee.model.v2.response;

import java.util.ArrayList;
import java.util.List;

import com.sasaug.monkeemods.services.shopee.model.v1.Order;
import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import lombok.Getter;

@Getter
public class GetOrderDetailsResponse extends BaseResponse {

	public List<Order> orders = new ArrayList<>();
	public List<String> errors = new ArrayList<>();
}
