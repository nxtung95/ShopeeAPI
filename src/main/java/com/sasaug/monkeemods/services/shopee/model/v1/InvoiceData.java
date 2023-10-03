package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceData {

	@SerializedName(value = "number")
	public String number;

	@SerializedName(value = "series_number")
	public String seriesNumber;

	@SerializedName(value = "access_key")
	public String accessKey;

	@SerializedName(value = "issue_date")
	public long issueDate;

	@SerializedName(value = "total_value")
	public double totalValue;

	@SerializedName(value = "products_total_value")
	public double productsTotalValue;

	@SerializedName(value = "tax_code")
	public String taxCode;

}
