package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AirwayBillResult {
	@SerializedName("total_count")
	public int totalCount;

	@SerializedName("airway_bills")
	public List<AirwayBill> airwayBills = new ArrayList<>();

	@SerializedName("errors")
	public List<AirwayBillError> errors = new ArrayList<>();
}
