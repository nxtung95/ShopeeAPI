package com.sasaug.monkeemods.services.shopee.controllers;

import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import com.sasaug.monkeemods.services.shopee.model.v2.TierVariation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TierVariationRequest {
	private long itemId;
	private List<TierVariation> tierVariationList;
	private List<Model> modelList;
}
