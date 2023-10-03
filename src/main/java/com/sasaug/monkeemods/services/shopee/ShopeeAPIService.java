package com.sasaug.monkeemods.services.shopee;

import com.google.gson.Gson;
import com.sasaug.monkeemods.services.shopee.helper.RequestHelper;
import com.sasaug.monkeemods.services.shopee.helper.RequestUrlBuilder;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemVariation;
import com.sasaug.monkeemods.services.shopee.model.v1.Dropoff;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemImage;
import com.sasaug.monkeemods.services.shopee.model.v1.Logistic;
import com.sasaug.monkeemods.services.shopee.model.v1.UnlistItem;
import com.sasaug.monkeemods.services.shopee.model.v1.enumeration.OrderStatus;
import com.sasaug.monkeemods.services.shopee.model.v1.request.*;
import com.sasaug.monkeemods.services.shopee.model.v1.response.*;
import jakarta.annotation.PostConstruct;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ShopeeAPIService {

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
	RequestHelper requestHelper;

	private static String URL_VERSION = "/api/v1";

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
		try {
			String token = "";
//			String token = SHA256.hash(PARTNER_KEY + redirectUrl);
			url += "?" + new RequestUrlBuilder().add("id", PARTNER_ID + "").add("token", token).add("redirect", redirectUrl).build();
		} catch (Exception e) {
		}
		return url;
	}

	/*
	 * Items
	 * */

	public GetItemCategoriesResponse getCategories() {
		String path = URL_VERSION + "/item/categories/get";
		String url = endpoint + path;

		GetItemCategoriesRequest request = new GetItemCategoriesRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);

		return performRequest(url, gson.toJson(request), GetItemCategoriesResponse.class);
	}

	public GetItemAttributesResponse getAttributes(long categoryId) {
		String path = URL_VERSION + "/item/attributes/get";
		String url = endpoint + path;

		GetItemAttributesRequest request = new GetItemAttributesRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setCategoryId(categoryId);

		return performRequest(url, gson.toJson(request), GetItemAttributesResponse.class);
	}

	public GetItemsListResponse getItemsList(int offset, int pageSize, boolean needDeletedItem) {
		String path = URL_VERSION + "/items/get";
		String url = endpoint + path;

		GetItemsListRequest request = new GetItemsListRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setPaginationOffset(offset);
		request.setPageSize(pageSize);
		request.setNeedDeletedItem(needDeletedItem);

		return performRequest(url, gson.toJson(request), GetItemsListResponse.class);
	}

	public GetItemDetailResponse getItemDetail(long id) {
		String path = URL_VERSION + "/item/get";
		String url = endpoint + path;

		GetItemDetailRequest request = new GetItemDetailRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(id);

		return performRequest(url, gson.toJson(request), GetItemDetailResponse.class);
	}

	public AddItemResponse addItem(long categoryId, String name, String description, String sku, double price, int stock, double weight, int length, int width, int height, List<String> images, List<Long> logistics, List<AddItemAttribute> itemAttributes, List<AddItemVariation> itemVariations) {
		String path = URL_VERSION + "/item/add";
		String url = endpoint + path;

		AddItemRequest request = new AddItemRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setName(name);
		request.setDescription(description);
		request.setCategoryId(categoryId);
		request.setItemSku(sku);

		List<ItemImage> imgs = new ArrayList<>();
		for (String s : images) {
			ItemImage img = new ItemImage();
			img.setUrl(s);
			imgs.add(img);
		}

		List<Logistic> logisticList = new ArrayList<>();
		for (Long id : logistics) {
			Logistic l = new Logistic();
			l.setEnabled(true);
			l.setId(id);
			logisticList.add(l);
		}

		request.setAttributes(itemAttributes);
		request.setImages(imgs);
		request.setPrice(price);
		request.setStock(stock);
		request.setWeight(weight);
		request.setPackageLength(length);
		request.setPackageWidth(width);
		request.setPackageHeight(height);
		request.setLogistics(logisticList);
		request.setAttributes(itemAttributes);
		request.setVariations(itemVariations);
		request.setCondition("NEW");
		request.setStatus("NORMAL");
		request.setPreOrder(false);

		return performRequest(url, gson.toJson(request), AddItemResponse.class);
	}

	public UpdateItemResponse updateItem(long itemId, long categoryId, String name, String description, String sku, double weight, List<AddItemAttribute> itemAttributes) {
		String path = URL_VERSION + "/item/update";
		String url = endpoint + path;

		UpdateItemRequest request = new UpdateItemRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);

		request.setName(name);
		request.setDescription(description);
		request.setCategoryId(categoryId);
		request.setItemSku(sku);
		request.setWeight(weight);
		request.setPreOrder(false);

		request.attributes.addAll(itemAttributes);
		return performRequest(url, gson.toJson(request), UpdateItemResponse.class);
	}

	public UnlistItemResponse unlistItem(Map<Long, Boolean> map) {
		String path = URL_VERSION + "/item/unlist";
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

		return performRequest(url, gson.toJson(request), UnlistItemResponse.class);
	}

	public UpdateItemPriceResponse updateItemPrice(long itemId, double price) {
		String path = URL_VERSION + "/items/update_price";
		String url = endpoint + path;

		UpdateItemPriceRequest request = new UpdateItemPriceRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);
		request.setPrice(price);

		return performRequest(url, gson.toJson(request), UpdateItemPriceResponse.class);
	}

	public UpdateItemStockResponse updateItemStock(long itemId, int stock) {
		String path = URL_VERSION + "/items/update_stock";
		String url = endpoint + path;

		UpdateItemStockRequest request = new UpdateItemStockRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);
		request.setStock(stock);

		return performRequest(url, gson.toJson(request), UpdateItemStockResponse.class);
	}

	public UpdateItemVariationStockResponse updateItemVariationStock(long itemId, long variationId, int stock) {
		String path = URL_VERSION + "/items/update_variation_stock";
		String url = endpoint + path;

		UpdateItemVariationStockRequest request = new UpdateItemVariationStockRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);
		request.setVariationId(variationId);
		request.setStock(stock);

		return performRequest(url, gson.toJson(request), UpdateItemVariationStockResponse.class);
	}

	public DeleteItemResponse deleteItem(long itemId) {
		String path = URL_VERSION + "/item/delete";
		String url = endpoint + path;

		DeleteItemRequest request = new DeleteItemRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);

		return performRequest(url, gson.toJson(request), DeleteItemResponse.class);
	}

	public UpdateItemImageResponse updateItemImage(long itemId, List<String> images) {
		String path = URL_VERSION + "/item/img/update";
		String url = endpoint + path;

		UpdateItemImageRequest request = new UpdateItemImageRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setItemId(itemId);
		request.setImages(images);

		return performRequest(url, gson.toJson(request), UpdateItemImageResponse.class);
	}


	/*
	 * Orders
	 * */
	public GetOrdersListResponse getOrdersList(int offset, int pageSize, long createTimeFrom, long createTimeTo) {
		String path = URL_VERSION + "/orders/basics";
		String url = endpoint + path;

		GetOrdersListRequest request = new GetOrdersListRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setPaginationOffset(offset);
		request.setPageSize(pageSize);
		request.setCreateTimeFrom(createTimeFrom);
		request.setCreateTimeTo(createTimeTo);

		return performRequest(url, gson.toJson(request), GetOrdersListResponse.class);
	}

	public GetOrdersListResponse getOrdersListByStatus(OrderStatus status, int page, int pageSize) {
		String path = URL_VERSION + "/orders/get";
		String url = endpoint + path;

		GetOrdersByStatusRequest request = new GetOrdersByStatusRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderStatus(status.toString());
		request.setPage(page);
		request.setPageSize(pageSize);

		return performRequest(url, gson.toJson(request), GetOrdersListResponse.class);
	}

	public GetOrderDetailsResponse getOrderDetails(List<String> orderIds) {
		String path = URL_VERSION + "/orders/detail";
		String url = endpoint + path;

		GetOrderDetailsRequest request = new GetOrderDetailsRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderIdsList(orderIds);

		return performRequest(url, gson.toJson(request), GetOrderDetailsResponse.class);
	}


	/*
	 * Logistics
	 * */
	public GetLogisticsResponse getLogistics() {
		String path = URL_VERSION + "/logistics/channel/get";
		String url = endpoint + path;

		GetLogisticsRequest request = new GetLogisticsRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);

		return performRequest(url, gson.toJson(request), GetLogisticsResponse.class);
	}

    /*public GetLogisticsResponse getLogisticsBranch(String orderId){
        String path = URL_VERSION + "/logistics/branch/get";
        String url = endpoint + path;

        GetLogisticsBranchRequest request = new GetLogisticsBranchRequest();
        request.setShopId(SHOP_ID);
        request.setPartnerId(PARTNER_ID);
        request.setOrderId(orderId);

        return performRequest(url, gson.toJson(request), GetLogisticsResponse.class);
    }*/

	public InitLogisticsResponse getParameterForInitLogistic(String orderId) {
		String path = URL_VERSION + "/logistics/init_parameter/get";
		String url = endpoint + path;
		GetParameterForInitLogisticsRequest request = new GetParameterForInitLogisticsRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderId(orderId);

		return performRequest(url, gson.toJson(request), InitLogisticsResponse.class);
	}

	public InitLogisticsResponse initLogistic(String orderId, Dropoff dropoff) {
		String path = URL_VERSION + "/logistics/init";
		String url = endpoint + path;
		InitLogisticsRequest request = new InitLogisticsRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderId(orderId);
		request.setDropoff(dropoff);

		return performRequest(url, gson.toJson(request), InitLogisticsResponse.class);
	}

	public GetAirwayBillResponse getAirwayBill(List<String> orderIds) {
		String path = URL_VERSION + "/logistics/airway_bill/get_mass";
		String url = endpoint + path;

		GetAirwayBillRequest request = new GetAirwayBillRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);
		request.setOrderIds(orderIds);
		request.setBatch(orderIds.size() > 1);

		return performRequest(url, gson.toJson(request), GetAirwayBillResponse.class);
	}


	/*
	 * Shop
	 * */

	public GetShopInfoResponse getShopInfo() {
		String path = URL_VERSION + "/shop/get";
		String url = endpoint + path;

		GetShopInfoRequest request = new GetShopInfoRequest();
		request.setShopId(SHOP_ID);
		request.setPartnerId(PARTNER_ID);

		return performRequest(url, gson.toJson(request), GetShopInfoResponse.class);
	}


	/*
	 * Private
	 * */

	private <T> T performRequest(String url, String request, Class<T> responseClass) {
		Request r = requestHelper.getRequestBuilder(PARTNER_KEY, url, request).build();
		try {
			Response response = client.newCall(r).execute();
			String json = response.body().string();

			T result = new Gson().fromJson(json, responseClass);
			return result;
		} catch (Exception e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		return null;
	}

}
