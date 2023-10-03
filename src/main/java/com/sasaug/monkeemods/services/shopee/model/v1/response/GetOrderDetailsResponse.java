package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.Order;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetOrderDetailsResponse extends BaseResponse {

	public List<Order> orders = new ArrayList<>();
	public List<String> errors = new ArrayList<>();
}
