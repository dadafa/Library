/**
 * 
 */
package com.xyd.client;

import com.xyd.manager.UserManager;

/**
 * @author scott
 * @date 2017年12月20日下午3:18:29
 * @version 
 * @description 
 */
public class BookApp {

	public static void main(String[] args) {

		UserManager.getInstance().start();
		
	}

}
