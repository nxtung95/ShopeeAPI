package com.sasaug.monkeemods.services.shopee.helper;


import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

public class RequestUrlBuilder {
	TreeMap<String, String> map = new TreeMap<>();

	public RequestUrlBuilder add(String key, String value) {
		map.put(key, value);
		return this;
	}

	public String build() {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (builder.length() != 0) {
				builder.append("&");
			}
			builder.append(entry.getKey());
			builder.append("=");
			builder.append(URLEncoder.encode(entry.getValue()));
		}
		return builder.toString();
	}
}
