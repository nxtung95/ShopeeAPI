package com.sasaug.monkeemods.services.shopee.controllers;

import com.sasaug.monkeemods.services.shopee.ShopeeV1Service;
import com.sasaug.monkeemods.services.shopee.ShopeeV2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback/shopee")
@Slf4j
public class ShopeeCallbackController {
	@Autowired
	ShopeeV1Service shopeeService;

	@Autowired
	ShopeeV2Service shopeeV2Service;

	@GetMapping(value = {"/webhook"})
	public ResponseEntity trigger(@RequestBody(required = false) String json) throws Exception {
		if(json != null && !json.isEmpty()) {
			shopeeService.handleWebHook(json);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = {"/redirect"})
	public String redirect(@RequestParam(value = "code") String code) throws Exception {
		log.info("Shopee callback with code: " + code);
		shopeeV2Service.setAccessToken(code);
		return "shopee/orders";
	}
}
