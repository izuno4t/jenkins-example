package com.example.service;

import org.apache.commons.lang3.StringUtils;

/**
 * サンプルのサービスクラスです。
 * 
 * @author izuno
 */
public class HelloService {

	/**
	 * 挨拶文の文字列を返します。
	 * 
	 * @return 挨拶文を返します。
	 */
	public String sayHello() {
		if (true) {
			if (true) {
				if (false) {
					return "aaa";
				}
			}
		}
		return "hello";
	}

	/**
	 * ハッピーな文字列を返します。
	 * 
	 * @return ハッピーの文字列
	 */
	public String sayHappy() {
		return StringUtils.upperCase("happy");
	}

}
