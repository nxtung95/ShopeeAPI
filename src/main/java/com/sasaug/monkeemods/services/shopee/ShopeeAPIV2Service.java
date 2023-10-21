package com.sasaug.monkeemods.services.shopee;

import com.google.gson.Gson;
import com.sasaug.monkeemods.services.shopee.helper.JedisHelper;
import com.sasaug.monkeemods.services.shopee.helper.RequestHelperV2;
import com.sasaug.monkeemods.services.shopee.helper.RequestUrlBuilder;
import com.sasaug.monkeemods.services.shopee.model.v2.*;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ItemStatus;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.OrderStatus;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ShippingDocumentType;
import com.sasaug.monkeemods.services.shopee.model.v2.request.*;
import com.sasaug.monkeemods.services.shopee.model.v2.response.*;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ShopeeAPIV2Service {

	@Value("${app.shopee.endpoint}")
	protected String PROD_SERVER_ENDPOINT;

	@Value("${app.shopee.endpoint.test}")
	protected String TEST_SERVER_ENDPOINT;

	@Value("${app.shopee.mode}")
	protected String mode;

	@Value("${app.shopee.partner.id}")
	protected long PARTNER_ID;

	@Value("${app.shopee.partner.key}")
	protected String PARTNER_KEY;

	@Value("${app.shopee.shop.id}")
	protected long SHOP_ID;

	@Autowired
	JedisHelper jedisHelper;

	@Autowired
	RequestHelperV2 requestHelper;

	private static String JEDIS_KEY_ACCESS_TOKEN_KEY = "shopee_v2_access_token";
	private static String JEDIS_KEY_REFRESH_TOKEN_KEY = "shopee_v2_acess_token";

	private static String URL_VERSION = "/api/v2";

	private OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
	private Gson gson = new Gson();

	private String endpoint;

	@PostConstruct
	public void init() {
		endpoint = mode.equalsIgnoreCase("prod") ? PROD_SERVER_ENDPOINT : TEST_SERVER_ENDPOINT;
	}

	public String getLoginUrl(String redirectUrl) {
		String path = URL_VERSION + "/shop/auth_partner";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;
		try {
			Map<String, String> params = new HashMap<>();
			params.put("redirect", redirectUrl);
			url = generateUrl(url, path, timestamp, params);
		} catch (Exception e) {
		}
		return url;
	}

	public GetAccessTokenResponse getAccessToken(String code) throws Exception {
		String path = URL_VERSION + "/auth/token/get";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		GetAccessTokenRequest request = new GetAccessTokenRequest();
//		request.setTimestamp(timestamp);
		request.setPartnerId(PARTNER_ID);
		request.setShopId(SHOP_ID);
		request.setCode(code);

		url = generateUrl(url, path, timestamp, new HashMap<>());

		String json = gson.toJson(request);
		GetAccessTokenResponse response = performPostRequest(url, json, GetAccessTokenResponse.class);
		log.info("GetAccessToken@res: " + gson.toJson(response));
		if (response.accessToken != null && !response.accessToken.isEmpty()) {
			setCurrentAccessToken(response.accessToken, response.refreshToken, response.expireIn);
		}
		return response;
	}

	public GetAccessTokenResponse refreshAccessToken(String refreshToken) throws Exception {
		String path = URL_VERSION + "/auth/access_token/get";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		RefreshAccessTokenRequest request = new RefreshAccessTokenRequest();
		request.setTimestamp(timestamp);
		request.setPartnerId(PARTNER_ID);
		request.setShopId(SHOP_ID);
		request.setRefreshToken(refreshToken);

		url = generateUrl(url, path, timestamp, null);

		String json = gson.toJson(request);
		GetAccessTokenResponse response = performPostRequest(url, json, GetAccessTokenResponse.class);
		if (response.accessToken != null && !response.accessToken.isEmpty()) {
			setCurrentAccessToken(response.accessToken, response.refreshToken, response.expireIn);
		} else {
			//throw error, this should not happen. Most likely refresh token invalid
		}
		return response;
	}

	/*
	 * Items
	 * */

	public GetItemsListResponse getItemList(int offset, ItemStatus itemStatus) throws Exception {
		String path = URL_VERSION + "/product/get_item_list";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");
		params.put("offset", offset + "");
		params.put("page_size", "100");
		params.put("item_status", itemStatus.name());

		url = generateShopUrl(url, path, timestamp, params);
		return performGetRequest(url, GetItemsListResponse.class);
	}

	public GetItemBaseInfoResponse getItemBaseInfo(List<Long> itemIds) throws Exception {
		String path = URL_VERSION + "/product/get_item_base_info";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");

		if (itemIds.size() > 50) {
			throw new Exception("getItemBaseInfo only support 50 or less items");
		}

		String ids = "";
		for (int i = 0; i < itemIds.size(); i++) {
			ids += itemIds.get(i);
			if (i != itemIds.size() - 1) {
				ids += ",";
			}
		}
		params.put("item_id_list", ids);

		url = generateShopUrl(url, path, timestamp, params);
		return performGetRequest(url, GetItemBaseInfoResponse.class);
	}

	public GetCategoryResponse getAllCategory() throws Exception {
		String path = URL_VERSION + "/product/get_category";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		//		params.put("shop_id", SHOP_ID + "");

		url = generateShopUrl(url, path, timestamp, params);
		return performGetRequest(url, GetCategoryResponse.class);
	}

	public GetLogisticsChannelResponse getAllLogisticsChannel() throws Exception {
		String path = URL_VERSION + "/logistics/get_channel_list";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		//		params.put("shop_id", SHOP_ID + "");

		url = generateShopUrl(url, path, timestamp, params);
		return performGetRequest(url, GetLogisticsChannelResponse.class);
	}


	public AddItemResponse addItem(long categoryId, String name, String description, String sku, double price, int stock, double weight, int length, int width, int height, List<String> images, List<Long> logistics) throws Exception {
		String path = URL_VERSION + "/product/add_item";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		AddItemRequest request = new AddItemRequest();
		request.categoryId = categoryId;
		request.name = name;
		request.description = description;
		request.itemSku = sku;
		request.price = price;
		AddItemSellerStockModel sellerStock = new AddItemSellerStockModel();
		sellerStock.stock = stock;
		request.sellerStock.add(sellerStock);
		request.weight = weight;
		request.dimension = new AddItemDimensionModel();
		request.dimension.setHeight(height);
		request.dimension.setLength(length);
		request.dimension.setWidth(width);
		request.image = new AddItemImageModel();
		request.image.imageIds = images;
		request.logistics = new ArrayList<>();
		BrandInfo brandInfo = new BrandInfo();
		brandInfo.id = 0;
		request.setBrand(brandInfo);
		for (Long l : logistics) {
			LogisticInfo logisticInfo = new LogisticInfo();
			logisticInfo.setId(l);
			logisticInfo.setEnabled(true);
			logisticInfo.setFree(false);
			logisticInfo.setSizeId(1);
			logisticInfo.setShippingFee(58.0);
			request.logistics.add(logisticInfo);
		}

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");
		url = generateShopUrl(url, path, timestamp, params);
		log.info("AddItem@url: " + url);
		log.info("AddItem@rq: " + gson.toJson(request));
		return performPostRequest(url, gson.toJson(request), AddItemResponse.class);
	}


	public UpdateItemResponse updateItem(long id, long categoryId, String name, String description, double weight, List<Long> logistics) throws Exception {
		String path = URL_VERSION + "/product/update_item";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		UpdateItemRequest request = new UpdateItemRequest();
		request.id = id;
		request.categoryId = categoryId;
		request.name = name;
		request.description = description;
		request.weight = weight;
		request.logistics = new ArrayList<>();
		BrandInfo brandInfo = new BrandInfo();
		brandInfo.id = 0;
		request.setBrand(brandInfo);
		for (Long l : logistics) {
			LogisticInfo logisticInfo = new LogisticInfo();
			logisticInfo.setId(l);
			logisticInfo.setEnabled(true);
			request.logistics.add(logisticInfo);
		}

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");
		url = generateShopUrl(url, path, timestamp, params);
		log.info("UpdateItem@url: " + url);
		log.info("UpdateItem@rq: " + gson.toJson(request));
		return performPostRequest(url, gson.toJson(request), UpdateItemResponse.class);
	}

	public TierVariationResponse initItemTierVariation(Long itemId, List<TierVariation> tierVariations, List<Model> models) throws Exception {
		String path = URL_VERSION + "/product/init_tier_variation";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		TierVariationRequest request = new TierVariationRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);
		request.setTierVariationList(tierVariations);
		request.setModelList(models);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), TierVariationResponse.class);
	}

	public TierVariationResponse updateItemTierVariation(Long itemId, List<TierVariation> tierVariations, List<Model> models) throws Exception {
		String path = URL_VERSION + "/product/update_tier_variation";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		TierVariationRequest request = new TierVariationRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setTierVariationList(tierVariations);
		request.setModelList(models);
		request.setItemId(itemId);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), TierVariationResponse.class);
	}

	public UnlistItemResponse unlistItem(Map<Long, Boolean> map) throws Exception {
		String path = URL_VERSION + "/product/unlist_item";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		UnlistItemRequest request = new UnlistItemRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);

		for (Map.Entry<Long, Boolean> entry : map.entrySet()) {
			UnlistItem unlistItem = new UnlistItem();
			unlistItem.setItemId(entry.getKey());
			unlistItem.setUnlist(entry.getValue());
			request.getItems().add(unlistItem);
		}

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());

		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), UnlistItemResponse.class);
	}

	public DeleteItemResponse deleteItem(long itemId) throws Exception {
		String path = URL_VERSION + "/product/delete_item";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		DeleteItemRequest request = new DeleteItemRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);
		return performPostRequest(url, gson.toJson(request), DeleteItemResponse.class);
	}

	public UpdateStockResponse updateItemStock(long itemId, long modelId, int stock) throws Exception {
		String path = URL_VERSION + "/product/update_stock";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		UpdateItemStockRequest request = new UpdateItemStockRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setId(itemId);

		UpdateStockStockListModel m = new UpdateStockStockListModel();
		if (modelId > 0) {
			m.setId(modelId);
		}
		List<SellerStock> sellerStocks = new ArrayList<>();
		SellerStock sellerStock = new SellerStock();
		sellerStock.setStock(stock);
		sellerStocks.add(sellerStock);
		m.setSellerStock(sellerStocks);
		request.getStockList().add(m);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		UpdateStockResponse res =  performPostRequest(url, gson.toJson(request), UpdateStockResponse.class);
		return res;
	}

	public UpdateItemPriceResponse updateItemPrice(long itemId, long modelId, double price) throws Exception {
		String path = URL_VERSION + "/product/update_price";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		UpdateItemPriceRequest request = new UpdateItemPriceRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setId(itemId);

		UpdatePricePriceListModel m = new UpdatePricePriceListModel();
		if (modelId > 0) {
			m.setId(modelId);
		}
		m.setPrice(price);
		request.getPriceList().add(m);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), UpdateItemPriceResponse.class);
	}

	public GetModelListResponse getModelList(long itemId) throws Exception {
		String path = URL_VERSION + "/product/get_model_list";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("item_id", String.valueOf(itemId));
		url = generateShopUrl(url, path, timestamp, params);

		return performGetRequest(url, GetModelListResponse.class);
	}

	public ModelResponse addModelList(long itemId, List<Model> modelList) throws Exception {
		String path = URL_VERSION + "/product/add_model";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		ModelRequest request = new ModelRequest();
		request.setModelList(modelList);
		request.setId(itemId);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), ModelResponse.class);
	}

	public ModelResponse updateModelList(long itemId, List<Model> modelList) throws Exception {
		String path = URL_VERSION + "/product/update_model";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		ModelRequest request = new ModelRequest();
		request.setUpdateModelList(modelList);
		request.setId(itemId);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), ModelResponse.class);
	}

	public ModelResponse deleteModel(long itemId, long modelId) throws Exception {
		String path = URL_VERSION + "/product/delete_model";
		long timestamp = System.currentTimeMillis() / 1000L;
		String url = endpoint + path;

		ModelRequest request = new ModelRequest();
		request.setModelId(modelId);
		request.setId(itemId);

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), ModelResponse.class);
	}

	/*
	 * Orders
	 * */
	public GetOrdersListResponse getOrdersList(String cursor, int pageSize, long createTimeFrom, long createTimeTo, OrderStatus orderStatus) throws Exception {
		String path = URL_VERSION + "/order/get_order_list";
		String url = endpoint + path;

		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("time_range_field", "create_time");
		params.put("page_size", pageSize + "");
		params.put("cursor", cursor);
		params.put("time_from", createTimeFrom + "");
		params.put("time_to", createTimeTo + "");
		if (!orderStatus.toString().equals(OrderStatus.ALL.toString())) {
			params.put("order_status", orderStatus.toString());
		}

		url = generateShopUrl(url, path, timestamp, params);

		return performGetRequest(url, GetOrdersListResponse.class);
	}

	public GetOrderDetailsResponse getOrderDetails(List<String> orderIds) throws Exception {
		String path = URL_VERSION + "/order/get_order_detail";
		String url = endpoint + path;

		long timestamp = System.currentTimeMillis() / 1000L;

		String list = "";
		for (int i = 0; i < orderIds.size(); i++) {
			list += orderIds.get(i);
			if (i < orderIds.size() - 1) {
				list += ",";
			}
		}

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");
		params.put("order_sn_list", list);
		url = generateShopUrl(url, path, timestamp, params);

		return performGetRequest(url, GetOrderDetailsResponse.class);
	}

	public BaseResponse shipOrder(String orderId, Pickup pickup, Dropoff dropoff) {
		String path = URL_VERSION + "/logistics/ship_order";
		String url = endpoint + path;
		ShipOrderRequest request = new ShipOrderRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderId(orderId);
		request.setDropoff(dropoff);
		request.setPickup(pickup);

		return performPostRequest(url, gson.toJson(request), BaseResponse.class);
	}

	public GetTrackingNumberResponse getTrackingNumber(String orderId) throws Exception {
		String path = URL_VERSION + "/logistics/get_tracking_number";
		String url = endpoint + path;

		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("order_sn", orderId);
		url = generateShopUrl(url, path, timestamp, params);

		return performGetRequest(url, GetTrackingNumberResponse.class);
	}

	public CreateShippingDocumentResponse createShippingDocument(String orderId, String trackingNumber, ShippingDocumentType shippingDocumentType) throws Exception {
		String path = URL_VERSION + "/logistics/create_shipping_document";
		String url = endpoint + path;

		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());

		CreateShippingDocumentRequest request = new CreateShippingDocumentRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		CreateShippingDocumentRequestModel model = new CreateShippingDocumentRequestModel();
		model.setOrderId(orderId);
		model.setTrackingNumber(trackingNumber);
		model.setShippingDocumentType(shippingDocumentType.toString());
		request.getOrderList().add(model);

		url = generateShopUrl(url, path, timestamp, params);

		return performPostRequest(url, gson.toJson(request), CreateShippingDocumentResponse.class);
	}


	/*
	 * Media Upload
	 * */
	public AddImageResponse addImage(String imageName, File file) throws Exception {
		String path = URL_VERSION + "/media_space/upload_image";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);
		return performPostFormRequest(url, "image", imageName, "application/jpg", file, AddImageResponse.class);
	}


	/*
	 * Raw dump
	 * */
	public String getChannelList() throws Exception {
		String path = URL_VERSION + "/logistics/get_channel_list";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("partner_id", PARTNER_ID + "");
		params.put("shop_id", SHOP_ID + "");
		params.put("access_token", getCurrentAccessToken());
		url = generateShopUrl(url, path, timestamp, params);
		String response = performGetRequest(url);
		return response;
	}

	/*
	 * Chat
	 * */

	public BaseResponse getChatConversationList() throws Exception {
		String path = URL_VERSION + "/sellerchat/get_conversation_list";
		String url = endpoint + path;
		long timestamp = System.currentTimeMillis() / 1000L;

		Map<String, String> params = new HashMap<>();
		params.put("access_token", getCurrentAccessToken());
		params.put("shop_id", SHOP_ID + "");
		params.put("direction", "latest");
		params.put("type", "all");
		params.put("page_size", "60");

		url = generateShopUrl(url, path, timestamp, params);
		return performGetRequest(url, BaseResponse.class);
	}



	/*
	 * Private
	 * */

	private String getCurrentAccessToken() throws Exception {
		System.out.println("access token: " + jedisHelper.get(JEDIS_KEY_ACCESS_TOKEN_KEY));
		System.out.println("refresh token: " + jedisHelper.get(JEDIS_KEY_REFRESH_TOKEN_KEY));
		String s = jedisHelper.get(JEDIS_KEY_ACCESS_TOKEN_KEY);
		if (s == null || s.isEmpty() || s.equals("null")) {
			//purge key
			System.out.println("purge key");
			jedisHelper.del(JEDIS_KEY_ACCESS_TOKEN_KEY);
		}
		System.out.println("access token cached: " + jedisHelper.isCached(JEDIS_KEY_ACCESS_TOKEN_KEY));

		if (jedisHelper.isCached(JEDIS_KEY_ACCESS_TOKEN_KEY)) {
			return jedisHelper.get(JEDIS_KEY_ACCESS_TOKEN_KEY);
		} else {
			if (jedisHelper.isCached(JEDIS_KEY_REFRESH_TOKEN_KEY)) {
				//use refresh token to obtain new key
				System.out.println("refreshing access token");
				refreshAccessToken(jedisHelper.get(JEDIS_KEY_REFRESH_TOKEN_KEY));
				return jedisHelper.get(JEDIS_KEY_ACCESS_TOKEN_KEY);
			}
		}
		//temp, should return null;
		return "fa945414497e3e650dea142a3b30ef5c";
	}

	private void setCurrentAccessToken(String accessToken, String refreshToken, long expireInSeconds) {
		jedisHelper.set(JEDIS_KEY_ACCESS_TOKEN_KEY, accessToken, (int) expireInSeconds);
		jedisHelper.set(JEDIS_KEY_REFRESH_TOKEN_KEY, refreshToken);
	}

	private String generateUrl(String url, String path, long timestamp, Map<String, String> params) {
		RequestUrlBuilder builder = new RequestUrlBuilder().add("partner_id", PARTNER_ID + "").add("sign", generateSignature(path, timestamp)).add("timestamp", timestamp + "");
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder = builder.add(entry.getKey(), entry.getValue());
			}
		}
		return url + "?" + builder.build();
	}

	private String generateUrl1(String url, String path, long timestamp) {
		RequestUrlBuilder builder = new RequestUrlBuilder().add("partner_id", PARTNER_ID + "").add("main_account_id", PARTNER_ID + "").add("sign", generateSignature(path, timestamp, PARTNER_ID + "")).add("timestamp", timestamp + "");
		return url + "?" + builder.build();
	}

	private String generateShopUrl(String url, String path, long timestamp, Map<String, String> params) throws Exception {
		RequestUrlBuilder builder = new RequestUrlBuilder().add("partner_id", PARTNER_ID + "").add("shop_id", SHOP_ID + "").add("sign", generateShopSignature(path, timestamp)).add("timestamp", timestamp + "");
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder = builder.add(entry.getKey(), entry.getValue());
			}
		}
		return url + "?" + builder.build();
	}

	private String generateSignature(String path, long timestamp, String... extras) {
		String sign = PARTNER_ID + path + timestamp;
		for (String s : extras) {
			sign += s;
		}
		return requestHelper.generateSignature(sign, PARTNER_KEY);
	}

	private String generateShopSignature(String path, long timestamp) throws Exception {
		String sign = PARTNER_ID + path + timestamp + getCurrentAccessToken() + SHOP_ID;
		return requestHelper.generateSignature(sign, PARTNER_KEY);
	}

	private String performGetRequest(String url) {
		Request r = requestHelper.getGETRequestBuilder(url).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();
			return json;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

	private <T> T performGetRequest(String url, Class<T> responseClass) {
		Request r = requestHelper.getGETRequestBuilder(url).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();

			log.info("Response " + url);
			log.info("Json: " + json);

			T result = new Gson().fromJson(json, responseClass);
			return result;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

	private <T> T performPostRequest(String url, String request, Class<T> responseClass) {
		Request r = requestHelper.getPOSTRequestBuilder(url, request).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();

			log.info("Response " + url);
			log.info("Json: " + json);

			T result = new Gson().fromJson(json, responseClass);
			return result;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

	private String performPostRequest(String url, String request) {
		Request r = requestHelper.getPOSTRequestBuilder(url, request).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();
			return json;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

	private <T> T performPostFormRequest(String url, String fileKey, String fileName, String mediaType, File file, Class<T> responseClass) {
		Request r = requestHelper.getPOSTRequestFormBuilder(url, fileKey, fileName, mediaType, file).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();
			log.info("Response " + url);
			log.info("Json: " + json);

			T result = new Gson().fromJson(json, responseClass);
			return result;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

}
