<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Spring MVC, JPA Data Demo</h1>

	<form:form name="indexForm" action="demoController.do" modelAttribute="customer2" method="get">
		<table border="1" bordercolor="#888" cellspacing="0"
			style="border-collapse: collapse; border-color: rgb(136, 136, 136); border-width: 1px">
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Insert"></td>
					<td>Firstname <form:input path="firstname" type="text" name="firstname" /> <br>
						Lastname <form:input path="lastname" type="text" name="custname" />
					</td>
				</tr>
				<tr>
					<td style="width: 127px; height: 15px">&nbsp;</td>
					<td style="width: 62px; height: 15px">&nbsp;Select</td>
					<td style="width: 201px; height: 15px">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 127px; height: 15px">&nbsp;</td>
					<td style="width: 62px; height: 15px">&nbsp;Update</td>
					<td style="width: 201px; height: 15px">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 127px; height: 15px">&nbsp;</td>
					<td style="width: 62px; height: 15px">&nbsp;Delete</td>
					<td style="width: 201px; height: 15px">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	
	${message}
</body>
</html>