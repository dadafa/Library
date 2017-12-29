/**
 * 
 */
package com.xyd.manager;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.xyd.bean.User;
import com.xyd.utils.InputUtils;
import com.xyd.utils.UIMeanUtils;

/**
 * @author scott
 * @date 2017年12月20日下午3:17:57
 * @version
 * @description
 */
public class UserManager {

	private Set<User> users = new HashSet<>();

	// 单例
	private UserManager() {
	}

	// 读取 class 会加载 属性
	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {

		return instance; // 调用都是同一对象
	}

	/**
	 * 程序 入口
	 */
	public void start() {

		// 初始化几个用户
		users.add(new User("admin", "123"));
		users.add(new User("scott", "tiger"));
		users.add(new User("root", "root"));

		while (true) {
			UIMeanUtils.welcome();
			int choose = InputUtils.inputInt("请输入选择:(数字)");
			switch (choose) {
			case 1:
				// 登录操作
				User user = login();
				if (user != null) {
					System.out.println(user.getUsername() + "登录成功");
					
					BookManger.getInstance().start(user);
					
				} else {
					System.out.println("登录失败...用户名或者密码");
				}

				break;
			case 2:
				boolean isRegister = register();
				System.out.println(isRegister ? "注册成功" : "注册失败");
				break;
			default:
				System.out.println("退出");
				// 退出程序 0 是正常退出
				System.exit(0);
				break;
			}
		}

	}

	/**
	 * 注册用户
	 * 
	 */
	private boolean register() {
		while (true) {
			// 输入用户名
			String username = InputUtils.inputStr("请输入用户名:(exit输入退出)");
			// 输入 exit 就是 返回
			if (username.equals("exit")) {
				return false;
			}
			boolean checkUser = checkUser(username);
			if (checkUser) {
				System.out.println("用户名已存在,请重新输入");
				continue;
			}
			
			String password = InputUtils.inputStr("请输入密码");
			
			User user = new User(username, password);
			return users.add(user);
		}
	}

	/**
	 * 用户登录 1.输入用户名 :密码
	 * 
	 * 2.判断是否有这个用户
	 */
	private User login() {

		while (true) {

			// 输入用户名
			String username = InputUtils.inputStr("请输入用户名:(exit输入退出)");

			// 输入 exit 就是 返回
			if (username.equals("exit")) {
				return null;
			}

			// 判断是否存在 这个用户
			boolean isCheck = checkUser(username);
			if (!isCheck) {
				System.out.println("用户 不存在  ,请重新输入 ");
				continue;
			}
			// 输入密码
			String password = InputUtils.inputStr("请输入密码:");

			for (User user : users) {
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					return user;
				}
			}
			return null;
		}
	}

	/**
	 * 判断是否存在 这个用户
	 */
	private boolean checkUser(String username) {

		for (User user : users) {

			if (user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

}
