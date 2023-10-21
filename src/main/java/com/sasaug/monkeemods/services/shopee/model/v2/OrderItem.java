package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderItem {

	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "item_sku")
	public String itemSku;

	public String name;

	@SerializedName(value = "variation_id")
	public long variationId;

	@SerializedName(value = "variation_name")
	public String variationName;

	@SerializedName(value = "variation_sku")
	public String variationSku;

	@SerializedName(value = "variation_quantity_purchased")
	public int variationQuantityPurchased;

	@SerializedName(value = "variation_original_price")
	public double variationOriginalPrice;

	@SerializedName(value = "variation_discounted_price")
	public double variationDiscountedPrice;

	@SerializedName(value = "is_wholesale")
	public boolean isWholesale;

	public double weight;

	@SerializedName(value = "is_add_on_deal")
	public boolean isAddOnDeal;

	@SerializedName(value = "is_main_item")
	public boolean isMainItem;

	@SerializedName(value = "add_on_deal_id")
	public long addOnDealId;

	@SerializedName(value = "order_item_id")
	public long orderItemId;

	@SerializedName(value = "group_id")
	public long groupId;

	@SerializedName(value = "promotion_type")
	public String promotionType;

	@SerializedName(value = "promotion_id")
	public long promotionId;


}
