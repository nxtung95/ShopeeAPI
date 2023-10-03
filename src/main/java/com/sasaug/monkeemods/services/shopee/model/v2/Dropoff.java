package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dropoff {
	@SerializedName("branch_id")
	public long branchId;

	@SerializedName("sender_real_name")
	public String senderRealName;

	@SerializedName("tracking_no")
	public String trackingNo;
}
