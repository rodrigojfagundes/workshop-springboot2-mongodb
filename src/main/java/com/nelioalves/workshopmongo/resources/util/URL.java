package com.nelioalves.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//CLASSE URL... basicamente era vai ENCODAR as requisicoes de busca
//exemplo TITULO: "BOM DIA" dai dps de encondar fica BOM%DIA
//ou seja sem o ESPACO
public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
