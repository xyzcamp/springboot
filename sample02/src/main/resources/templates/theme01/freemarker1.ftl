<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Hello!</title>
    <link rel="stylesheet" type="text/css" href="${urls.version('/theme01/css/main.css')}" />
</head>
<body>
	<p>Hello, Freemarker.</p>
	<p>${memo1}, name=${content.name}, getName()=${content.getName()}</p>
	<img src="${urls.version('/theme01/image/aspire.jpg')}" />
	
	<br>

	<#if content.active>
	active = true
	<#else>
	active = false
	</#if>

	<br>

	<!-- product.id 必須有public getter() -->
	<table>
	<#list content.products as product>
	    <tr>
	        <td>${product.id}</td>
	        <td>${product.title}</td>
	        <td>${product.descr}</td>
		</tr>
	</#list>
	</table>
</body>
</html>
