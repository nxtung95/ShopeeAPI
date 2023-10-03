package com.sasaug.monkeemods.services.shopee;

import com.google.gson.Gson;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemVariation;
import com.sasaug.monkeemods.services.shopee.model.v1.Dropoff;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import com.sasaug.monkeemods.services.shopee.model.v1.Order;
import com.sasaug.monkeemods.services.shopee.model.v1.OrderBasic;
import com.sasaug.monkeemods.services.shopee.model.v1.OrderStatusUpdatePush;
import com.sasaug.monkeemods.services.shopee.model.v1.Push;
import com.sasaug.monkeemods.services.shopee.model.v1.TrackingNumberPush;
import com.sasaug.monkeemods.services.shopee.model.v1.enumeration.OrderStatus;
import com.sasaug.monkeemods.services.shopee.model.v1.response.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopeeV1Service {

	private long categoryId = 101728;
	private long logisticId = 2000; //standard

	@Autowired
	ShopeeAPIService shopeeAPIService;

	Map<String, String> replacements = new HashMap<>();

	@PostConstruct
	public void init() {
		replacements.put("Burnt Titanium(Rainbow)", "Burnt Titanium");
		replacements.put("Bronze/Black Buffer Tube", "Bronze/Black Tube");
		replacements.put("Red/Black Buffer Tube", "Red/Black Tube");
		replacements.put("Black/Red Buffer Tube", "Black/Red Tube");
		replacements.put("Semi Translucent Black", "Semi Black");
		replacements.put("Semi Translucent Blue", "Semi Blue");
		replacements.put("Semi Translucent Sand", "Semi Sand");
		replacements.put("Top rail only", "Top Rail");
		replacements.put("Top rail and side rail set", "Top and Side Rail");
		replacements.put("14mm CCW & 19mm Direct Attach", "14mm ccw and 19mm");
		replacements.put("19mm Direct Attach Muzzle Only", "19mm only");
	}

	//ITEMS
	public List<ItemData> getAllItems(boolean needDeleteItem) {
		List<ItemData> shopeeItems = new ArrayList<>();
		int index = 0;
		while (true) {
			GetItemsListResponse itemsListResponse = shopeeAPIService.getItemsList(index, 100, needDeleteItem);
			shopeeItems.addAll(itemsListResponse.getItems());
			if (!itemsListResponse.more) {
				break;
			}
			index += 100;
		}
		Optional<ItemData> optional = shopeeItems.stream().filter(item -> item.getItemSku().equals("525")).findFirst();
		return shopeeItems;
	}

	public ItemData getItem(long id) {
		GetItemDetailResponse resp = shopeeAPIService.getItemDetail(id);
		return resp.item;
	}

	public ItemData addItem(String name, String description, long sku, double price, int stock, double weight, int length, int width, int height, String brand, List<String> images, List<AddItemVariation> variations) throws Exception {
		List<Long> logistics = new ArrayList<>();
		List<AddItemAttribute> attributes = new ArrayList<>();
		AddItemAttribute attr = new AddItemAttribute();
		attr.setId(0);
		attr.setValue(brand);
		attributes.add(attr);
		logistics.add(logisticId);

		if (name.length() < 20) {
			name = name + " - Monkee Mods";
		}
		AddItemResponse resp = shopeeAPIService.addItem(categoryId, name, description, sku + "", price, stock, weight, length, width, height, images, logistics, attributes, variations);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.item;
		}

		throw new Exception(resp.msg);
	}

	public ItemData updateItem(long itemId, String name, String description, long sku, double weight, String brand) {
		List<AddItemAttribute> attributes = new ArrayList<>();
		AddItemAttribute attr = new AddItemAttribute();
		attr.setId(0);
		attr.setValue(brand);
		attributes.add(attr);

		UpdateItemResponse resp = shopeeAPIService.updateItem(itemId, categoryId, name, description, sku + "", weight, attributes);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.item;
		}
		return null;
	}

	public long removeItem(long id) {
		DeleteItemResponse resp = shopeeAPIService.deleteItem(id);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.itemId;
		}
		return -1;
	}

	public ItemData updateItemStock(long itemId, int quantity) {
		UpdateItemStockResponse resp = shopeeAPIService.updateItemStock(itemId, quantity);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.item;
		}
		return null;
	}

	public ItemData updateItemVariationStock(long itemId, long variationId, int quantity) {
		UpdateItemVariationStockResponse resp = shopeeAPIService.updateItemVariationStock(itemId, variationId, quantity);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.item;
		}
		return null;
	}

	public ItemData updateItemPrice(long itemId, double price) {
		UpdateItemPriceResponse resp = shopeeAPIService.updateItemPrice(itemId, price);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.item;
		}
		return null;
	}

	//ORDERS

	public List<OrderBasic> getOrdersList(long from, long to) {
		List<OrderBasic> shopeeOrders = new ArrayList<>();
		int index = 0;
		while (true) {
			GetOrdersListResponse listResponse = shopeeAPIService.getOrdersList(index, 100, from, to);
			shopeeOrders.addAll(listResponse.getOrders());
			if (!listResponse.more) {
				break;
			}
			index += 100;
		}
		return shopeeOrders;
	}

	public List<OrderBasic> getOrdersListByStatus(OrderStatus status) {
		List<OrderBasic> shopeeOrders = new ArrayList<>();
		int index = 0;
		while (true) {
			GetOrdersListResponse listResponse = shopeeAPIService.getOrdersListByStatus(status, index, 100);
			shopeeOrders.addAll(listResponse.getOrders());
			if (!listResponse.more) {
				break;
			}
			index++;
		}
		return shopeeOrders;
	}

	public List<Order> getOrderDetails(List<String> orderIds) {
		GetOrderDetailsResponse resp = shopeeAPIService.getOrderDetails(orderIds);
		return resp.orders;
	}

	//LOGISTIC
	public InitLogisticsResponse arrangePickup(String orderId) {
		Dropoff dropoff = new Dropoff();
		dropoff.setBranchId(1);
		dropoff.setTrackingNo("");
		dropoff.setSenderRealName("Monkee Mods");
		InitLogisticsResponse response = shopeeAPIService.initLogistic(orderId, dropoff);
		return response;
	}


	public String getAirwayBill(String orderId) {
		List<String> orderIds = new ArrayList<>();
		orderIds.add(orderId);
		GetAirwayBillResponse response = shopeeAPIService.getAirwayBill(orderIds);
		if (response.getResult().getErrors().size() == 0) {
			return response.getResult().getAirwayBills().get(0).getUrl();
		}
		return null;
	}

	//PUSH
	public void handleWebHook(String json) {
		Gson gson = new Gson();
		Push push = gson.fromJson(json, Push.class);
		if (push.getCode() == Push.CODE_ORDERSTATUS) {
			OrderStatusUpdatePush orderStatusUpdatePush = gson.fromJson(json, OrderStatusUpdatePush.class);
			//TODO: hande order status update
		} else if (push.getCode() == Push.CODE_TRACKINGNO) {
			TrackingNumberPush trackingNumberPush = gson.fromJson(json, TrackingNumberPush.class);


		}
	}

	//Extra function
	public String getShopeeOption(String option) {
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			if (entry.getKey().equals(option)) {
				return entry.getValue();
			}

		}
		return option;
	}

	public String getProductOption(String option) {
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			if (entry.getValue().equals(option)) {
				return entry.getKey();
			}

		}
		return option;
	}


}
