package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order extends OrderBasic {
	@SerializedName(value = "country")
	public String country;

	@SerializedName(value = "currency")
	public String currency;

	@SerializedName(value = "cod")
	public boolean cod;

	@SerializedName(value = "tracking_no")
	public String trackingNo;

	@SerializedName(value = "days_to_ship")
	public int daysToShip;

	@SerializedName(value = "recipient_address")
	public Address recipientAddress;

	@SerializedName(value = "estimated_shipping_fee")
	public String estimatedShippingFee;

	@SerializedName(value = "actual_shipping_cost")
	public String actualShippingCost;

	@SerializedName(value = "total_amount")
	public String totalAmount;

	@SerializedName(value = "escrow_amount")
	public String escrowAmount;

	@SerializedName(value = "shipping_carrier")
	public String shippingCarrier;

	@SerializedName(value = "payment_method")
	public String paymentMethod;

	@SerializedName(value = "goods_to_declare")
	public boolean goodsToDeclare;

	@SerializedName(value = "message_to_seller")
	public String messageToSeller;

	@SerializedName(value = "note")
	public String note;

	@SerializedName(value = "note_update_time")
	public long noteUpdateTime;

	@SerializedName(value = "create_time")
	public long createTime;

	public List<OrderItem> items = new ArrayList<>();

	@SerializedName(value = "pay_time")
	public long payTime;

	public String dropshipper;

	@SerializedName(value = "buyer_username")
	public String buyerUsername;

	@SerializedName(value = "dropshipper_phone")
	public String dropshipperPhone;

	@SerializedName(value = "credit_card_number")
	public String creditCardNumber;

	@SerializedName(value = "is_split_up")
	public boolean isSplitUp;

	@SerializedName(value = "ship_by_date")
	public long shipByDate;

	@SerializedName(value = "buyer_cancel_reason")
	public String buyerCancelReason;

	@SerializedName(value = "fm_tn")
	public String firstMileTrackingNumber;

	@SerializedName(value = "checkout_shipping_carrier")
	public String checkoutShippingCarrier;

	@SerializedName(value = "plp_number")
	public String plpNumber;


}
