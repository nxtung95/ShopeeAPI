package com.sasaug.monkeemods.services.shopee.model.v2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TierVariation {

	public String name;

	@SerializedName(value = "option_list")
	public List<TierVariationOption> optionList = new ArrayList<>();

}
