package com.presente.services.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (String v : vet) {
			list.add(Integer.parseInt(v));
		}
		return list;
	}

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
