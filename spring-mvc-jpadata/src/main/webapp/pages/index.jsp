<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="<c:url value="/js/jquery-1.9.1.min.js" />"></script>

<script>
	function showCustomer() {
	
		var id = $('#custid').val();
		if( id == null || id.trim() == "" ) {
			alert( "Please enter valid id" );
			return;
		}
		
		$.ajax({
			type: "Get",
			url: "customer/" + id,
			success: function (data) {
				var cust;
				if( data != null && data != "" ) {
					cust = data.id + ": " + data.firstname + ". " + data.lastname;
				}else {
					cust = "Not available";
				}
				$('#custdata').text( cust );
			}
		});
	
// 		$('#custdata').html($('#custid').val());
	}
	
</script>

</head>
<body>

	<h1>Spring MVC, JPA Data Demo</h1>
	<p>Click me away!</p>
	<form:form name="indexForm" action="demoController"
		modelAttribute="customer2" method="get">
		<table border="1" bordercolor="#888" cellspacing="0"
			style="border-collapse: collapse; border-color: rgb(136, 136, 136); border-width: 1px">
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Insert" id="insert"></td>
					<td>Firstname <form:input path="firstname" type="text" name="firstname" /> <br> 
						Lastname <form:input path="lastname" type="text" name="custname" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="button" value="Select"	onclick="showCustomer()" /></td>
					<td><input id="custid" /> <span id="custdata"></span></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Update</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Delete</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</form:form>

	${message}

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>S No</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<c:forEach items="${customerList}" var="tempCustomer">
			<tr>
				<td>${tempCustomer.id}</td>
				<td>${tempCustomer.firstname}</td>
				<td>${tempCustomer.lastname}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>