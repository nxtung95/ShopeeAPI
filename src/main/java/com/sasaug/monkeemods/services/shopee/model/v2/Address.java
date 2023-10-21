package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	public String name;
	public String phone;
	public String town;
	public String district;
	public String city;
	public String state;
	public String country;
	public String zipcode;

	@SerializedName(value = "full_address")
	public String fullAddress;


}
