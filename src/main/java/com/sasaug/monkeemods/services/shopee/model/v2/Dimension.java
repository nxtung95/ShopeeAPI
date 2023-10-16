package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dimension {
    @SerializedName("package_length")
    private int packageLength;

    @SerializedName("package_width")
    private int packageWidth;

    @SerializedName("package_height")
    private int packageHeight;
}
