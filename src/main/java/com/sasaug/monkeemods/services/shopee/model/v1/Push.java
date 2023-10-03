package com.sasaug.monkeemods.services.shopee.model.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Push {

	public static int CODE_ORDERSTATUS = 3;
	public static int CODE_TRACKINGNO = 4;
	public static int CODE_CHAT = 10;

	public int code;
	public long timestamp;
}
