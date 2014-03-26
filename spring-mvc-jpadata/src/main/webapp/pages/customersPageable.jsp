<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<h1>Message : ${message} ------</h1>

	number: ${page.number}
	<br /> size: ${page.size}
	<br /> totalPages: ${page.totalPages}
	<br /> numberOfElements: ${page.numberOfElements}
	<br /> totalElements: ${page.totalElements}
	<br />
	<%-- 	hasNextPage: ${page.hasNextPage} <br /> --%>

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>S No</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<c:forEach items="${page.content}" var="tempCustomer">
			<tr>
				<td>${tempCustomer.id}</td>
				<td>${tempCustomer.firstname}</td>
				<td>${tempCustomer.lastname}</td>
			</tr>
		</c:forEach>
	</table>

	<a href="?page.page=1">1</a>
	<a href="?page.page=2">2</a>

<!-- 	<spring:url value="" var="next"> -->
<%-- 		<spring:param name="page.page" value="${page.number + 1}"></spring:param> --%>
<%-- 		<spring:param name="page.size" value="${page.size}"></spring:param> --%>
<!-- 	</spring:url> -->

</body>
</html>