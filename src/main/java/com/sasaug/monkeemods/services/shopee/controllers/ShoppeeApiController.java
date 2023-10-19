package com.sasaug.monkeemods.services.shopee.controllers;

import com.sasaug.monkeemods.services.shopee.ShopeeV2Service;
import com.sasaug.monkeemods.services.shopee.model.v2.Category;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemBase;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemList;
import com.sasaug.monkeemods.services.shopee.model.v2.response.DeleteItemResponse;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ShoppeeApiController {
	@Autowired
	private ShopeeV2Service shopeeV2Service;

	@RequestMapping(method = RequestMethod.GET, value = "/get-access-token")
	public ResponseEntity<String> getAccessToken(@RequestParam(name = "code") String code) {
		shopeeV2Service.setAccessToken(code);
		return ResponseEntity.ok("Success");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-login-url")
	public ResponseEntity<String> getLoginUrl() {
		String url = shopeeV2Service.getLoginUrl("http://localhost:8080/callback/shopee/redirect");
		return ResponseEntity.ok(url);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-item-list")
	public ResponseEntity<List<ItemList>> getItemList() throws Exception {
		List<ItemList> itemLists = shopeeV2Service.getAllItems(true);
		return ResponseEntity.ok(itemLists);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-item-base-info")
	public ResponseEntity<List<ItemBase>> getItemBaseInfo(@RequestParam(name = "itemIds") String itemIdList) throws Exception {
		List<Long> itemIds = Arrays.asList(itemIdList.split(",")).stream().map(i -> Long.parseLong(i)).collect(Collectors.toList());
		List<ItemBase> itemBases = shopeeV2Service.getItemBaseInfo(itemIds);
		return ResponseEntity.ok(itemBases);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-all-category")
	public ResponseEntity<List<Category>> getAllCategory() throws Exception {
		List<Category> categoryList = shopeeV2Service.getAllCategory();
		return ResponseEntity.ok(categoryList);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get-all-logistics")
	public ResponseEntity<List<GetAllLogisticsChannelModel>> getAllLogistics() throws Exception {
		List<GetAllLogisticsChannelModel> res = shopeeV2Service.getAllLogisticsChannel();
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add-item")
	public ResponseEntity<AddItemResponseModel> addItem(@RequestBody AddItemRequest rq) throws Exception {
		AddItemResponseModel res = shopeeV2Service.addItem(rq.getName(), rq.getDescription(), rq.getSku(), rq.getPrice(), rq.getStock(), rq.getWeight(),
				rq.getLength(), rq.getWidth(), rq.getHeight(), rq.getBrand(), Arrays.asList(rq.getImages().split(",")).stream().toList());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update-item")
	public ResponseEntity<UpdateItemResponseModel> updateItem(@RequestBody UpdateItemRequest rq) throws Exception {
		UpdateItemResponseModel res = shopeeV2Service.updateItem(rq.getItemId(), rq.getName(), rq.getDescription(), rq.getWeight(),
				rq.getLength(), rq.getWidth(), rq.getHeight(), rq.getBrand(), Arrays.asList(rq.getImages().split(",")).stream().toList());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unlist-item")
	public ResponseEntity<UnlistItemResponseModel> unlistItem(@RequestBody UnlistItemRequest rq) throws Exception {
		Map<Long,Boolean> unlistItems = new HashMap<>();
		Arrays.stream(rq.getUnlistItems().split(",")).forEach(item -> unlistItems.put(Long.parseLong(item), false));
		UnlistItemResponseModel res = shopeeV2Service.unlistItem(unlistItems);
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete-item")
	public ResponseEntity<Long> deleteItem(@RequestBody UnlistItemRequest rq) throws Exception {
		Long res = shopeeV2Service.removeItem(Long.parseLong(rq.unlistItems));
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update-item-price")
	public ResponseEntity<UpdatePriceItemResponseModel> updateItemPrice(@RequestBody ItemRequest rq) throws Exception {
		UpdatePriceItemResponseModel res = shopeeV2Service.updateItemPrice(rq.itemId, rq.modelId, rq.price);
		return ResponseEntity.ok(res);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update-item-stock")
	public ResponseEntity<UpdateStockResponseModel> updateItemStock(@RequestBody ItemRequest rq) throws Exception {
		UpdateStockResponseModel res = shopeeV2Service.updateItemStock(rq.itemId, rq.modelId, rq.getQuantity());
		return ResponseEntity.ok(res);
	}
}
