<%--
  Created by IntelliJ IDEA.
  User: thiendn
  Date: 7/25/2016
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="init.jsp"%>
<h1>Add new member</h1>
<portlet:actionURL var="employeeAddUrl">
  <portlet:param name="action" value="save"/>
</portlet:actionURL>
<form:form commandName="employee" method="post" action="${employeeAddUrl}">
  <form:input path="name"/>
  <input type="submit" />
</form:form>

