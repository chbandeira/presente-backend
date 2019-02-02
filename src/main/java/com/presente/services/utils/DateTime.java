package com.presente.services.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTime {

	public static Date getDataAtual() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	    return Date.from(now.toInstant(ZoneOffset.UTC));
	}
}
