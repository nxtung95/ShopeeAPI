package com.sasaug.monkeemods.services.shopee.model.v2.response;

import lombok.Getter;

import java.io.File;

@Getter
public class DownloadShippingDocumentResponse extends BaseResponse {
	private File waybill;
}
