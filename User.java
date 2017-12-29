/**
 * 
 */
package com.xyd.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author scott
 * @date 2017年12月20日下午3:16:54
 * @version 
 * @description  用户类
 * 
 */
public class User {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User() {
	}
	
	//每个用户要有一个存放书籍的书包
	private Map<Book, Integer> books = new HashMap<>();
	private Integer put;
	
	/**
	 *  1.用户有那些书籍
	 *  
	 *  2.如果没有这本书  添加 书籍   和   借阅的数量 
	 *  
	 *  3.有这本书 直接 添加数量
	 */
	public void saveBook(HashMap<Book, Integer> bookB) {
		
		//借阅的书籍 
		Set<Entry<Book, Integer>> entrySet = bookB.entrySet();
		
		for (Entry<Book, Integer> entry : entrySet) {
			
			Book book = entry.getKey();
			Integer value = entry.getValue();
			
			//书包中 有没有这本书      没有书 直接添加    有   数量 + 新借阅的数量   
			Integer count = books.get(book);
			
			books.put(book, count == null ? value :count +value);
		}
		System.out.println("结束成功...");
		
	}
	
	/**
	 * 查看图书
	 */
	public void showBooks() {

		Set<Entry<Book, Integer>> entrySet = books.entrySet();

		System.out.println("书名\t数量");
		for (Entry<Book, Integer> entry : entrySet) {
			System.out.println(entry.getKey().getName() + "\t" + entry.getValue());
		}
	}
}
