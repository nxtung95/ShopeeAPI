package com.sasaug.monkeemods.services.shopee;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v2.*;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ItemStatus;
import com.sasaug.monkeemods.services.shopee.model.v2.response.*;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

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
		List<AddItemAttribute> attributes = new ArrayList<>();
		AddItemAttribute attr = new AddItemAttribute();
		attr.setId(100002);
//		attr.setValue(brand);
		attributes.add(attr);
		logistics.add(logisticId);

		if (name.length() < 20) {
			name = name + " - Monkee Mods";
		}
		AddItemResponse resp = shopeeAPIV2Service.addItem(categoryId, name, description, sku + "", price, stock, weight, length, width, height, images, logistics, attributes);
		log.info("addItem@res: " + gson.toJson(resp));
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}


	public UpdateItemResponseModel updateItem(String name, String description, double weight, int length, int width, int height, String brand, List<String> images) throws Exception {


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
		UpdateItemResponse resp = shopeeAPIV2Service.updateItem(categoryId, name, description, weight, logistics);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}

	public UpdateItemResponseModel unlistItem(String name, String description, double weight, int length, int width, int height, String brand, List<String> images) throws Exception {


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
		UpdateItemResponse resp = shopeeAPIV2Service.updateItem(categoryId, name, description, weight, logistics);
		if (resp.error == null || resp.error.isEmpty()) {
			return resp.getResponse();
		}
		return null;
	}


	//MEDIA SPACE
//	public AddImageResponseModel uploadImage(String url) throws Exception {
//		byte[] data = NetUtils.getBin(url);
//		IO.write("temp.jpg", data);
//		File file = new File("temp.jpg");
//		AddImageResponse resp = shopeeAPIV2Service.addImage("temp", new File("temp.jpg"));
//		file.delete();
//		if (resp.error == null || resp.error.isEmpty()) {
//			return resp.getResponse();
//		}
//
//		throw new Exception(resp.getError());
//	}
}
