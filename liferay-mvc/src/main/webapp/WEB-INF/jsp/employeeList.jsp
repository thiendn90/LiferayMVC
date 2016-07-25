<%@include file="init.jsp"%>
hello world
<portlet:defineObjects/>
<portlet:renderURL var="addEmployeeUrl">
    <portlet:param name="render" value="employeeAdd"/>
</portlet:renderURL>
<portlet:renderURL var="updateEmployeeUrl">
    <portlet:param name="render" value="employeeUpdate"/>
</portlet:renderURL>
<portlet:renderURL var="viewEmployeeUrl">
    <portlet:param name="render" value="employeeView"/>
</portlet:renderURL>

<a href="${addEmployeeUrl}">Add New Member</a>
<table border="1" width="100%">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td><a href="${viewEmployeeUrl}&employeeId=${emp.id}" title="View employee" ><c:out value="${emp.id}"/></a></td>
            <td><c:out value="${emp.name}"/></td>
            <td><a href="${updateEmployeeUrl}&employeeId=${emp.id}" title="Update">Update</a></td>
            <td><a href="<portlet:resourceURL id="processDelete" />&employeeId=${emp.id}" title="Delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<script>
    function deleteItem(id) {
        if (id)
            location.href = "VIEW/deleteName?name=" + id;
    }
</script>