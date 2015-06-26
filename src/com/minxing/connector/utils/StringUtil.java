package com.minxing.connector.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class StringUtil {

		public static String convertContentToHtml(String content){
			content = content.replaceAll("(\r\n|\n)", "<br/>");
			content = content.replaceAll(" ", "&nbsp;");
			return content;
		}
		
		public static String pathDecode(String path) throws UnsupportedEncodingException{
			if(path != null){
				path = path.replace("+", "%2b");
				path = URLDecoder.decode(path,"UTF-8");
			}
			return path;
		}
		//图文内容里带\n \r \r\n  会造成图文消息结构混乱，导致客户端crash、web显示消息异常
		public static String convertContent(String content){
			if(content!=null){
				content = content.replaceAll("(\n)", "\\\\n");
				content = content.replaceAll("(\r)", "\\\\r");
			}
			return content;
		}
}
