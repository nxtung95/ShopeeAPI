package com.sasaug.monkeemods.services.shopee.helper;

import com.google.common.hash.Hashing;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RequestHelperV2 {
	public String generateSignature(String base, String key) {
		return Hashing.hmacSha256(key.getBytes(StandardCharsets.UTF_8)).hashString(base, StandardCharsets.UTF_8).toString();
	}

	public RequestBody getRequestBody(String json) {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, json);
		return body;
	}

	public Request.Builder getGETRequestBuilder(String url) {
		return new Request.Builder().url(url).get().addHeader("Content-type", "application/json");
	}

	public Request.Builder getPOSTRequestBuilder(String url, String json) {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, json);
		return new Request.Builder().url(url).post(body).addHeader("Content-type", "application/json");
	}

	public Request.Builder getPOSTRequestFormBuilder(String url, String fileKey, String fileName, String mediaType, File file) {
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(fileKey, fileName, RequestBody.create(MediaType.parse(mediaType), file)).build();
		return new Request.Builder().url(url).post(body).addHeader("Content-type", "application/json");
	}

}
