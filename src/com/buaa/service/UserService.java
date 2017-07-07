package com.buaa.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.buaa.dao.UserDao;
import com.buaa.po.Userinfo;

@Service
public class UserService {
	private UserDao userDao = new UserDao();

	public String doLogin(String username, String psw, String loginurl,
			HttpServletRequest request) {
		String result = "";
		if ((username == "") || (username == null) || (username.length() > 20)) {
			try {
				result = "请输入用户名(不能超过20个字符)!";
				request.setAttribute("message", result);
				return loginurl;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ((psw == "") || (psw == null) || (psw.length() > 20)) {
			try {
				result = "请输入密码(不能超过20个字符)!";
				request.setAttribute("message", result);
				return loginurl;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<Userinfo> pojos = userDao.getUserByUserNameAndPsw(username, psw);
		HttpSession session = request.getSession();
		try {
			if (pojos.size() != 0) { // 如果记录集非空，表明有匹配的用户名和密码，登陆成功
				// 登录成功后将username设置为session变量的username
				// 这样在后面就可以通过 session.getAttribute("username") 来获取用户名，
				// 同时这样还可以作为用户登录与否的判断依据
				Userinfo userinfo = pojos.get(0);
				session.setAttribute("age", userinfo.getAge().intValue());
				session.setAttribute("sex", userinfo.getSex());
				session.setAttribute("weight", userinfo.getWeight().intValue());
				session.setAttribute("username", username);
				return "/success";
			} else {
				session.setAttribute("message", "用户名或密码不匹配。");
				return "/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loginurl;
	}

	public Map<String, Object> showUsers(String rows, String page) {
		return userDao.showUsers(rows, page);
	}
}