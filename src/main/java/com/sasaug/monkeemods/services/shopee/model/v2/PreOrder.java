package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreOrder {
    @SerializedName("is_pre_order")
    private boolean isPreOrder;

    @SerializedName("days_to_ship")
    private int daysToShip;
}
