package com.sasaug.monkeemods.services.shopee.helper;

import com.google.common.hash.Hashing;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RequestHelper {
	public String generateSignature(String base, String key) {
		return Hashing.hmacSha256(key.getBytes(StandardCharsets.UTF_8)).hashString(base, StandardCharsets.UTF_8).toString();
	}

	public RequestBody getRequestBody(String json) {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, json);
		return body;
	}

	public Request.Builder getRequestBuilder(String partnerKey, String url, String json) {
		String authorization = getAuthorization(partnerKey, url, json);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, json);

		return new Request.Builder().url(url).post(body).addHeader("content-type", "application/json").addHeader("Authorization", authorization);
	}

	public String getAuthorization(String partnerKey, String url, String body) {
		String base = url + "|" + body;
		return generateSignature(base, partnerKey);
	}

}
