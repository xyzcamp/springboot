<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, sample02.model.Customer, sample02.model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Hello!</title>
	<link rel="stylesheet" type="text/css" href="${urls.version('/theme01/css/main.css')}" />
</head>

<body>
	<p>Hello, JSP.</p>
	<p>${memo1}, name=${content.name}, getName()=${content.getName()}</p>
	<img src="${urls.version('/theme01/image/aspire.jpg')}" />

	<br>
	<% Customer customer = (Customer)request.getAttribute("content"); %>

	<% if (customer.active) { %>
	active = true
	<% } else { %>
	active = false
	<% } %>

	<br>

	<!-- product.id 無須有 getter() -->
	<table>
		<% for (Product product : customer.products) { %>
		<tr>
			<td><%=product.id %></td>
			<td><%=product.title %></td>
			<td><%=product.descr %></td>
		</tr>
		<% } %>
	</table>

</body>
</html>
