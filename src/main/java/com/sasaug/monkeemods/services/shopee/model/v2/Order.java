package com.sasaug.monkeemods.services.shopee.model.v2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends OrderBasic {
	@SerializedName(value = "region")
	public String region;

	@SerializedName(value = "country")
	public String country;

	@SerializedName(value = "currency")
	public String currency;

	@SerializedName(value = "cod")
	public boolean cod;

	@SerializedName(value = "order_status")
	public String orderStatus;

	@SerializedName(value = "pending_terms")
	public String[] pendingTerms;

	@SerializedName(value = "tracking_no")
	public String trackingNo;

	@SerializedName(value = "days_to_ship")
	public int daysToShip;

	@SerializedName(value = "recipient_address")
	public Address recipientAddress;

	@SerializedName(value = "actual_shipping_fee")
	public String actualShippingFee;

	@SerializedName(value = "estimated_shipping_fee")
	public String estimatedShippingFee;

	@SerializedName(value = "actual_shipping_cost")
	public String actualShippingCost;

	@SerializedName(value = "actual_shipping_fee_confirmed")
	public boolean actualShippingFeeConfirmed;

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

	@SerializedName(value = "update_time")
	public long update_time;

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

	@SerializedName(value = "split_up")
	public boolean splitUp;

	@SerializedName(value = "ship_by_date")
	public long shipByDate;

	@SerializedName(value = "buyer_user_id")
	public Long buyerUserId;

	@SerializedName(value = "buyer_cancel_reason")
	public String buyerCancelReason;

	@SerializedName(value = "cancel_by")
	public String cancelBy;

	@SerializedName(value = "cancel_reason")
	public String cancelReason;

	@SerializedName(value = "fm_tn")
	public String firstMileTrackingNumber;

	@SerializedName(value = "checkout_shipping_carrier")
	public String checkoutShippingCarrier;

	@SerializedName(value = "plp_number")
	public String plpNumber;

	@SerializedName(value = "item_list")
	private List<Object> itemList;

	@SerializedName(value = "buyer_cpf_id")
	public String buyerCpfId;

	@SerializedName(value = "fulfillment_flag")
	public String fulfillmentFlag;

	@SerializedName(value = "pickup_done_time")
	public Long pickupDoneTime;

	@SerializedName(value = "package_list")
	public List<Object> packageList;

	@SerializedName(value = "invoice_data")
	public Object invoiceData;

	@SerializedName(value = "reverse_shipping_fee")
	public Double reverseShippingFee;

	@SerializedName(value = "order_chargeable_weight_gram")
	public int orderChargeableWeightGram;

}
