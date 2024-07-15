package com.jg.ebook.util;

import jakarta.servlet.http.HttpServletRequest;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	private static UserAgentAnalyzer userAgentAnalyzer;

	public CommonUtil() {
		userAgentAnalyzer = UserAgentAnalyzer.newBuilder().hideMatcherLoadStats().withCache(10000).build();
	}

	/**
	 *모바일, 태블릿, PC 구분
	 * @param req
	 * @return
	 */
	public static String isDevice(HttpServletRequest req){
		String userAgent = req.getHeader("User-Agent").toUpperCase();
		if(userAgent.contains("MOBILE")){
			if(userAgent.contains("PHONE")){
				return "MOBILE";
			}
			return "TABLET";
		}else{
			return "PC";
		}
	}

	/**
	 *모바일, 태블릿, PC 구분 (yauaa library)
	 * @param req
	 * @return
	 */
	public static String isDevice2(HttpServletRequest req){
		String userAgentString = req.getHeader("User-Agent");
		if (userAgentString == null || userAgentString.isEmpty()) {
			return "User-Agent header is missing";
		}

		UserAgent userAgent = userAgentAnalyzer.parse(userAgentString);
		String deviceClass = userAgent.getValue("DeviceClass");

		switch (deviceClass) {
			case "Desktop":
				return "pc";
			case "Phone":
				return "mobile";
			case "Tablet":
				return "tablet";
			case "Watch":
				return "watch";
			case "Mobile":
				return "mobile";
			default:
				return "Unknown device class: " + deviceClass;
		}
	}
}
