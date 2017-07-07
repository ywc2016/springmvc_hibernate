<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<%
		String userName = session.getAttribute("username").toString();
		String age = session.getAttribute("age").toString();
		String weight = session.getAttribute("weight").toString();
		String sex = (String) session.getAttribute("sex").toString();
		if (sex.trim().equals("M")) {
			sex = "男";
		} else {
			sex = "女";
		}
	%>
	<div align="center">
		<%=userName%>
		欢迎您，登陆成功！<br /> <br /> <font color="blue">登陆用户信息：</font>
		<table border=1 style="margin-top: 20px;margin-bottom: 20px;">
			<tr>
				<td>&nbsp;姓名：&nbsp;</td>
				<td>&nbsp;&nbsp;<%=userName%>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>&nbsp;年龄：&nbsp;</td>
				<td>&nbsp;&nbsp;<%=age%>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>&nbsp;体重：&nbsp;</td>
				<td>&nbsp;&nbsp;<%=weight%>&nbsp;kg&nbsp;</>
			</tr>
			<tr>
				<td>&nbsp;性别：&nbsp;</td>
				<td>&nbsp;&nbsp;<%=sex%>&nbsp;&nbsp;</>
			</tr>
		</table>
	</div>

	<div align="center" style="margin-top: 20px">
		<a href="./showUsers">查看用户</a> <a href="">添加用户</a> <a href="">修改用户</a>
		<a href="">删除用户</a>
	</div>

	<div align="center" style="margin-top: 20px">
		<a href="./logout">退出登录</a>
	</div>
</body>
</html>