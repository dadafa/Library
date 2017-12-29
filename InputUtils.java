/**
 * 
 */
package com.xyd.utils;

import java.util.Scanner;

/**
 * @author scott
 * @date 2017年12月20日下午3:34:45
 * @version 
 * @description 
 */
public class InputUtils {

	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 返回字符串 
	 */
	public static String inputStr(String msg) {
		
		System.out.println(msg);
		
		return sc.next();
		
	}
	
	/**
	 * 获取键盘输入的数字
	 */
	public static int inputInt(String msg) {
		
		boolean isFlag = true;
		System.out.println(msg);
		
		while (isFlag) {
			try {
				int parseInt = Integer.parseInt(sc.next());
				isFlag = false;
				return parseInt;
			} catch (Exception e) {
				//出现了异常
				System.out.println("输入非法  请重新输入数字");
				isFlag = true;
			} 
		}
		return -1;
	}
	
	
}
