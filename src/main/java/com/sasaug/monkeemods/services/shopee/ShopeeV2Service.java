package com.sasaug.monkeemods.services.shopee;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v2.*;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ItemStatus;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.OrderStatus;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ShippingDocumentType;
import com.sasaug.monkeemods.services.shopee.model.v2.response.*;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.*;
import com.sasaug.monkeemods.services.shopee.utils.IO;
import com.sasaug.monkeemods.services.shopee.utils.NetUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShopeeV2Service {

//	private long categoryId = 101728;
	private long categoryId = 102079;
//	private long logisticId = 2000; //standard
	private long logisticId = 20088;

	@Autowired
	ShopeeAPIService shopeeAPIService;

	@Autowired
	ShopeeAPIV2Service shopeeAPIV2Service;

	@Autowired
	Gson gson;

//	@Autowired
//	JedisHelper jedisHelper;


	Map<String, String> replacements = new HashMap<>();

	@PostConstruct
	public void init() {
		replacements.put("Burnt Titanium(Rainbow)", "Burnt Titanium");
		replacements.put("Bronze/Black Buffer Tube", "Bronze/Black Tube");
		replacements.put("Red/Black Buffer Tube", "Red/Black Tube");
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
	//getLogin url
	public String getLoginUrl(String redirectUrl) {
		try {
			return shopeeAPIV2Service.getLoginUrl(redirectUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//AUTHORIZATION
	public void setAccessToken(String code) {
		try {
			shopeeAPIV2Service.getAccessToken(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//ITEMS
	public List<ItemList> getAllItems(boolean needDeleteItem) throws Exception {
		List<ItemList> shopeeItems = new ArrayList<>();
		List<ItemList> normalItems = new ArrayList<>();
		List<ItemList> bannedItems = new ArrayList<>();
		List<ItemList> deletedItems = new ArrayList<>();

		//fetch all normal items
		int index = 0;
		while (true) {
			GetItemsListResponse itemsListResponse = shopeeAPIV2Service.getItemList(index, ItemStatus.NORMAL);
			normalItems.addAll(itemsListResponse.getResponse().getItem());
			shopeeItems.addAll(itemsListResponse.getResponse().getItem());
			if (!itemsListResponse.getResponse().isHasNextPage()) {
				break;
			}
			index += 100;
		}
		log.info("[NORMAL]Item@size: " + normalItems.size());

		index = 0;
		while (true) {
			GetItemsListResponse itemsListResponse = shopeeAPIV2Service.getItemList(index, ItemStatus.BANNED);
			shopeeItems.addAll(itemsListResponse.getResponse().getItem());
			bannedItems.addAll(itemsListResponse.getResponse().getItem());
			if (!itemsListResponse.getResponse().isHasNextPage()) {
				break;
			}
			index += 100;
		}
		log.info("[BANNED]Item@size: " + bannedItems.size());

		if (needDeleteItem) {
			index = 0;
			while (true) {
				GetItemsListResponse itemsListResponse = shopeeAPIV2Service.getItemList(index, ItemStatus.DELETED);
				shopeeItems.addAll(itemsListResponse.getResponse().getItem());
				deletedItems.addAll(itemsListResponse.getResponse().getItem());
				if (!itemsListResponse.getResponse().isHasNextPage()) {
					break;
				}
				index += 100;
			}
		}
		log.info("[DELETED]Item@size: " + deletedItems.size());
		return shopeeItems;
	}

	public List<ItemBase> getItemBaseInfo(List<Long> itemIds) throws Exception {
		List<ItemBase> shopeeItems = new ArrayList<>();

		List<List<Long>> list = Lists.partition(itemIds, 50);

		for (List<Long> l : list) {
			GetItemBaseInfoResponse response = shopeeAPIV2Service.getItemBaseInfo(l);
			log.info("GetItemBaseInfoResponse@res: " + gson.toJson(response));
			shopeeItems.addAll(response.getResponse().getItemList());
		}
		return shopeeItems;
	}

	public List<Category> getAllCategory() throws Exception {
		try {
			return shopeeAPIV2Service.getAllCategory().getResponse().getCategoryList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public List<GetAllLogisticsChannelModel> getAllLogisticsChannel() throws Exception {
		try {
			return shopeeAPIV2Service.getAllLogisticsChannel().getResponse().getLogisticsList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public AddItemResponseModel addItem(String name, String description, long sku, double price, int stock, double weight, int length, int width, int height, String brand, List<String> images) throws Exception {
		List<Long> logistics = new ArrayList<>();
//		List<AddItemAttribute> attributes = new ArrayList<>();
//		AddItemAttribute attr = new AddItemAttribute();
//		attr.setId(100002);
////		attr.setValue(brand);
//		attributes.add(attr);
		logistics.add(logisticId);

		if (name.length() < 20) {
			name = name + " - Monkee Mods";
		}
		AddItemResponse resp = shopeeAPIV2Service.addItem(categoryId, name, description, sku + "", price, stock, weight, length, width, height, images, logistics);
		log.info("addItem@res: " + gson.toJson(resp));
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}


	public UpdateItemResponseModel updateItem(Long itemId, String name, String description, double weight, int length, int width, int height, String brand, List<String> images) throws Exception {


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
		UpdateItemResponse resp = shopeeAPIV2Service.updateItem(itemId, categoryId, name, description, weight, logistics);
		log.info("updateItem@res: " + gson.toJson(resp));
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public long removeItem(long id) throws Exception {
		DeleteItemResponse resp = shopeeAPIV2Service.deleteItem(id);
		if (resp.error == null || resp.error.isEmpty()) {
			resp.itemId = id;
			return resp.itemId;
		}
		return -1;
	}

	public UnlistItemResponseModel unlistItem(Map<Long, Boolean> mapItem) throws Exception {

		UnlistItemResponse resp = shopeeAPIV2Service.unlistItem(mapItem);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public UpdatePriceItemResponseModel updateItemPrice(long itemId, long modelId, double price) throws Exception {
		UpdateItemPriceResponse resp = shopeeAPIV2Service.updateItemPrice(itemId, modelId, price);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public UpdateStockResponseModel updateItemStock(long itemId, long modelId, int quantity) throws Exception {
		UpdateStockResponse resp = shopeeAPIV2Service.updateItemStock(itemId, modelId, quantity);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public GetModelListResponseModel getModelList(long itemId) throws Exception {
		GetModelListResponse resp = shopeeAPIV2Service.getModelList(itemId);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public TierVariationModel initTierVariation(Long itemId, List<TierVariation> tierVariations, List<Model> modelList) throws Exception {
		TierVariationResponse resp = shopeeAPIV2Service.initItemTierVariation(itemId, tierVariations, modelList);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public TierVariationModel updateTierVariation(Long itemId, List<TierVariation> tierVariations, List<Model> modelList) throws Exception {
		TierVariationResponse resp = shopeeAPIV2Service.updateItemTierVariation(itemId, tierVariations, modelList);
		if (resp.error == null || resp.error.isEmpty()) {
			resp.getResponse().setModelList(modelList);
			resp.getResponse().setTierVariationList(tierVariations);
			resp.getResponse().setId(itemId);
			return resp.getResponse();
		}
		return null;
	}

	public ModelResponseModel addModelList(long itemId, List<Model> modelList) throws Exception {
		ModelResponse resp = shopeeAPIV2Service.addModelList(itemId, modelList);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public ModelResponseModel updateModelList(long itemId, List<Model> modelList) throws Exception {
		ModelResponse resp = shopeeAPIV2Service.updateModelList(itemId, modelList);
		if (resp.error == null || resp.error.isEmpty()) {
			if (resp.getResponse() == null) {
				ModelResponseModel responseModel = new ModelResponseModel();
				responseModel.setModel(modelList);
				return responseModel;
			}
		}
		return null;
	}

	public ModelResponseModel deleteModel(long itemId, long modelId) throws Exception {
		ModelResponse resp = shopeeAPIV2Service.deleteModel(itemId, modelId);
		if (resp.error == null || resp.error.isEmpty()) {
			ModelResponseModel responseModel = new ModelResponseModel();
			responseModel.setModelId(modelId);
			responseModel.setItemId(itemId);
			return responseModel;
		}
		return null;
	}

	//MEDIA SPACE
	public AddImageResponseModel uploadImage(String url) throws Exception {
		byte[] data = NetUtils.getBin(url);
		IO.write("temp.jpg", data);
		File file = new File("temp.jpg");
		AddImageResponse resp = shopeeAPIV2Service.addImage("temp", new File("temp.jpg"));
		file.delete();
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}

		throw new Exception(resp.getError());
	}

	public GetOrdersListResponseModel getOrderList(String cursor, int pageSize, Long createTimeFrom, Long createTimeTo, OrderStatus orderStatus) throws Exception {
		GetOrdersListResponse resp = shopeeAPIV2Service.getOrdersList(cursor, pageSize, createTimeFrom, createTimeTo, orderStatus);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public GetOrderDetailResponseModel getOrderDetails(List<String> orderIds) throws Exception {
		GetOrderDetailsResponse resp = shopeeAPIV2Service.getOrderDetails(orderIds);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public BaseResponse shipOrder(String orderId, Pickup pickup, Dropoff dropoff) throws Exception {
		BaseResponse resp = shopeeAPIV2Service.shipOrder(orderId, pickup, dropoff);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp;
		}
		return null;
	}

	public GetTrackingNumberResponseModel getTrackingNumber(String orderId) throws Exception {
		GetTrackingNumberResponse resp = shopeeAPIV2Service.getTrackingNumber(orderId);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public CreateShippingDocumentResponseModel createShippingDocument(String orderId, String trackingNumber, ShippingDocumentType shippingDocumentType) throws Exception {
		CreateShippingDocumentResponse resp = shopeeAPIV2Service.createShippingDocument(orderId, trackingNumber, shippingDocumentType);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}
}
