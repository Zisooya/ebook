package com.jg.ebook.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	/**
	 *모바일, 태블릿, PC 구분
	 * @param req
	 * @return
	 */
	public static String isDevice(HttpServletRequest req){
		String userAgent = req.getHeader("User-Agent").toUpperCase();
		if(userAgent.indexOf("MOBILE") > -1){
			if(userAgent.indexOf("PHONE") == -1){
				return "MOBILE";
			}
			return "TABLET";
		}else{
			return "PC";
		}
	}
}
