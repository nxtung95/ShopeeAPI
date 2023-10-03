package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.Logistic;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetLogisticsResponse extends BaseResponse {

	public List<Logistic> logistics = new ArrayList<>();

}
