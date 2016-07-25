<%@include file="init.jsp" %>
hello world emp form
<portlet:actionURL var="addEmployeeURL">
    <portlet:param name="action" value="save"/>
</portlet:actionURL>
<form:form id="employeeForm" commandName="employee" method="post" action="${addEmployeeURL}">
    <form:input path="id" readonly="true"/>
    <form:input path="name" />
    <input type="submit" />
</form:form>