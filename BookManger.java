/**
 * 
 */
package com.xyd.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xyd.bean.Book;
import com.xyd.bean.User;
import com.xyd.utils.InputUtils;
import com.xyd.utils.UIMeanUtils;

/**
 * @author scott
 * @date 2017年12月20日下午4:26:24
 * @version
 * @description
 */
public class BookManger {

	// 书架的集合选择 数目 数量
	private Map<Book, Integer> books = new HashMap<>();

	private BookManger() {

		// map key 重复 keySet
		books.put(new Book("笑傲江湖"), 5);
		books.put(new Book("天龙八部"), 5);
		books.put(new Book("神雕侠侣"), 5);
		books.put(new Book("射雕英雄传"), 5);

	}

	private static BookManger instance = new BookManger();

	public static BookManger getInstance() {

		return instance; // 调用都是同一对象
	}

	/**
	 * 借书 开始的方法
	 */
	public void start(User user) {

		while (true) {

			UIMeanUtils.borrowBook();
			int choose = InputUtils.inputInt("请输入选择:(数字)");

			switch (choose) {
			case 1:

				showBooks();

				break;
			case 2:

				// 书架上的书 减少了
				HashMap<Book, Integer> bookB = borrowBook();
				// 借到 的书 要放在 指定用户那里
				if (bookB != null) {
					user.saveBook(bookB);
				}

				break;
			case 3:

				System.out.println("3.还书");
				break;
			case 4:
				user.showBooks();
				break;

			default:
				System.out.println("5.返回上一级");
				return;
			}
		}

	}

	/**
	 * 借书
	 * 
	 * 1.输入书名
	 * 
	 * 2.判断是否有这本书
	 * 
	 * 3.请输入数量
	 * 
	 * 4.数量够不够
	 * 
	 * 5.返回借阅的书籍 （书架上数量要减少）
	 */
	private HashMap<Book, Integer> borrowBook() {

		while (true) {
			// 1.输入书名
			String bookName = InputUtils.inputStr("请输入书名");
			Book book = new Book(bookName);
			// * 2.判断是否有这本书
			boolean containsKey = books.containsKey(book);
			if (!containsKey) {
				System.out.println("没有这本书,请重新输入");
				continue;
			}
			// * 3.请输入数量
			int count = InputUtils.inputInt("请输入数量");
			// 拿到书架上得数

			Integer value = books.get(book);
			// 4.数量够不够
			if (count > value) {
				System.out.println("没有那么多的数量");
				return null;
			}
			// 5.返回借阅的书籍 （书架上数量要减少）
			// 借阅的书籍
			HashMap<Book, Integer> hashMap = new HashMap<>();
			hashMap.put(book, count);

			// 书架上的书 要减少 之前的数量 - 借阅的数量
			books.put(book, value - count);
			return hashMap;
		}
	}

	/**
	 * 查看图书
	 */
	private void showBooks() {

		Set<Entry<Book, Integer>> entrySet = books.entrySet();

		System.out.println("书名\t数量");
		for (Entry<Book, Integer> entry : entrySet) {
			System.out.println(entry.getKey().getName() + "\t" + entry.getValue());
		}
	}

}
