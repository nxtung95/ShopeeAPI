package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.OrderBasic;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetOrdersListResponse extends BaseResponse {

	public List<OrderBasic> orders = new ArrayList<>();
	public boolean more;
}
