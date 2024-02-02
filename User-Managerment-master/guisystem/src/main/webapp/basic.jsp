<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang JSP căn bản</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");	
	//Lấy giá trị tham số
		String name = request.getParameter("txtName");
		if(name!=null && !"".equalsIgnoreCase(name)){
			//out.append("<h3>Xin chào: ").append(name).append("</h3>");
			%>
			
			<h3>Xin chào: <%=name %></h3>
			
			
			<%
		}else{
		
	%>
	

	<form action="basic.jsp" method="post">
		<label>Nhập tên: </label>
		<input type="text" name="txtName">
		<button type="submit">Gửi</button>
	</form>
	
	<%
		}//else
	%>

</body>
</html>