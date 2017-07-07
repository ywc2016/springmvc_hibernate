package com.buaa.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.naming.java.javaURLContextFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.buaa.po.Userinfo;

public class UserDao extends BaseDao<Userinfo> {

	public List<Userinfo> getUserByUserNameAndPsw(String username, String psw) {
		try {
			String queryString = "from " + typeClass().getCanonicalName()
					+ " where username = :username and userpsw = :userpsw";
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(queryString);
			query.setString("username", username);
			query.setString("userpsw", psw);
			List<Userinfo> pojos = (List<Userinfo>) query.list();
			session.getTransaction().commit();
			return pojos;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map showUsers(String rows, String page) {
		int intRows = ("".equals(rows) ? 5 : Integer.parseInt(rows)); // 一页显示的记录数
		int intPage = ("".equals(page) ? 1 : Integer.parseInt(page)); // 待显示的页码

		long intRowCount = 0; // 记录的总数
		long intPageCount = 0; // 总页数
		List<Userinfo> pojos = null;
		try {
			String queryString = "from " + typeClass().getCanonicalName() + "";
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(queryString);
			query.setFirstResult((intPage - 1) * intRows);
			query.setMaxResults(intRows);
			pojos = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			throw re;
		}

		// 获取记录总数
		try {
			String queryString = "select count(*) from "
					+ typeClass().getCanonicalName() + "";
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(queryString);
			intRowCount = Long.parseLong(query.uniqueResult().toString());
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			throw re;
		}
		// 计算机总页数
		intPageCount = intRowCount / intRows
				+ (intRowCount % intRows == 0 ? 0 : 1);

		Map<String, Object> map = new HashMap<>();
		map.put("rows", pojos);
		map.put("total", intPageCount);
		return map;
	}
}
