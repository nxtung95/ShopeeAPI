package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarningGetShippingDocument {
    @SerializedName(value = "order_sn")
    private String orderSn;
    @SerializedName(value = "package_number")
    private String packageNumber;
}
