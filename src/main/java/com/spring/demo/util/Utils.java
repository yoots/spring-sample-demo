/*
 * Copyright (c) 2013 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.util;

import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2013. 5. 13.
 */

public class Utils {


	/**
	 * 스크립트 제거...
	 * @param content
	 * @return
	 */
	public static String getText(String content) {
		
		Pattern SCRIPTS = Pattern.compile("&lt;(no)?script[^&gt;]*&gt;.*?&lt;/(no)?script&gt;",Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("&lt;style[^&gt;]*&gt;.*&lt;/style&gt;",Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("&lt;(\"[^\"]*\"|\'[^\']*\'|[^\'\"&gt;])*&gt;");
		Pattern nTAGS = Pattern.compile("&lt;\\w+\\s+[^&lt;]*\\s*&gt;");
		Pattern ENTITY_REFS = Pattern.compile("&amp;[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");
		
		Matcher m;
		
		m = SCRIPTS.matcher(content);
		content = m.replaceAll("");
		m = STYLE.matcher(content);
		content = m.replaceAll("");
		m = TAGS.matcher(content);
		content = m.replaceAll("");
		m = ENTITY_REFS.matcher(content);
		content = m.replaceAll("");
		m = WHITESPACE.matcher(content);
		content = m.replaceAll(" "); 		
		
		return content;
	}

	/**
	 * 꺽쇠 제거
	 * @param str
	 * @return
	 */
	public static String tagRemove(String str) {
		if(str == null) return "";

		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("script|cookie|alert", "");
		
		return str;
	}
	
	public static String tagRestoration(String str) {
		
		if(str == null) return "";
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @param len
	 * @param tail
	 * @return
	 */
	public static String cutString(String str, int len, String tail)
    {
        if(str.length() <= len)
            return str;
        StringCharacterIterator sci = new StringCharacterIterator(str);
        StringBuffer buffer = new StringBuffer();
        buffer.append(sci.first());
        for(int i = 1; i < len; i++)
            if(i < len - 1)
            {
                buffer.append(sci.next());
            } else
            {
                char c = sci.next();
                if(c != ' ')
                    buffer.append(c);
            }

        buffer.append(tail);
        return buffer.toString();
    }
	
}
