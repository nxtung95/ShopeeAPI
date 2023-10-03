package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemData {

    @SerializedName(value= "item_id")
    public long id;

    @SerializedName(value= "shopid")
    public long shopId;

    public String status;

    @SerializedName(value= "item_sku")
    public String itemSku;

    public List<ItemVariation> variations = new ArrayList<>();

    @SerializedName(value= "is_2tier_item")
    public boolean is2TierItem;

    public String name;

    public String description;

    public List<String> images = new ArrayList<>();

    public String currency;

    @SerializedName(value = "has_variation")
    public boolean hasVariation;

    public double price;

    public int stock;

    @SerializedName(value= "create_time")
    public long createTime;

    @SerializedName(value= "update_time")
    public long updateTime;

    public double weight;

    @SerializedName(value= "category_id")
    public long categoryId;

    @SerializedName(value= "original_price")
    public double originalPrice;

    public List<ItemAttribute> attributes = new ArrayList<>();

    public List<Logistic> logistics = new ArrayList<>();

    @SerializedName(value= "rating_star")
    public double ratingStar;

    @SerializedName(value= "cmt_count")
    public int commentCount;

    public int sales;
    public int views;
    public int likes;

    @SerializedName(value= "package_length")
    public double packageLength;

    @SerializedName(value= "package_width")
    public double packageWidth;

    @SerializedName(value= "package_height")
    public double packageHeight;

    @SerializedName(value= "days_to_ship")
    public int daysToShip;

    public String condition;

    @SerializedName(value= "discount_id")
    public long discountId;

    @SerializedName(value= "reserved_stock")
    public int reservedStock;

    @SerializedName(value= "is_pre_order")
    public boolean isPreOrder;

    @SerializedName(value= "inflated_price")
    public double inflatedPrice;

    @SerializedName(value= "inflated_original_price")
    public double inflatedOriginalPrice;

    @SerializedName(value= "sip_item_price")
    public double sipItemPrice;

    @SerializedName(value= "price_source")
    public String priceSource;

    @SerializedName(value= "item_dangerous")
    public int itemDangerous;


}