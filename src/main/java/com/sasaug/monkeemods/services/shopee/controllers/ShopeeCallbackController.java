package com.sasaug.monkeemods.services.shopee.controllers;

import com.sasaug.monkeemods.services.shopee.ShopeeV2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback/shopee")
@Slf4j
public class ShopeeCallbackController {


	@Autowired
	ShopeeV2Service shopeeV2Service;

	@GetMapping(value = {"/redirect"})
	public String redirect(@RequestParam(value = "code") String code) throws Exception {
		log.info("Shopee callback with code: " + code);
		shopeeV2Service.setAccessToken(code);
		return "shopee/orders";
	}
}
