<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User" %>

<%
User user = (User)session.getAttribute("user");

//セッション切れか確認
if(user == null){
	request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd", "logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>