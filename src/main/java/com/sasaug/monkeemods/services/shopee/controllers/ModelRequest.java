package com.sasaug.monkeemods.services.shopee.controllers;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelRequest {
	private long itemId;
	private long modelId;
	private List<Model> modelList;
}
