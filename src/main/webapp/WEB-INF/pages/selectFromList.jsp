<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Alfresco Java Plugin Demo</title>
    <link href="<c:url value="/resources/css/theme.css" />" rel="stylesheet"/>
    <link rel="shortcut icon" href="https://www.byu.edu/templates/2.1.5/images/favicon.ico">
</head>
<body>
<ul id="nav" class="tabs" data-tab>
    <li class="tab-title"><a href="<c:url value="/"/>"><b>Get by Path</b></a></li>
    <li class="tab-title"><a href="<c:url value="/filterByType"/>"><b>Filter by file type</b></a></li>
    <li class="tab-title"><a href="<c:url value="/upload"/>"><b>Upload</b></a></li>
    <li class="tab-title active"> <a href="<c:url value="/selectFromList"/>"><b>Select and Edit</b></a></li>
    <li class="tab-title"><a href="<c:url value="/videoDemo"/>"><b>Video</b></a></li>
</ul>

<!-- form -->
<div id="form">
    <p>The dropdown has been hard-coded to contain all the items located in the '/User Homes/abbott/' test folder.<br>Please browse and select the file you would like to view:</p>
    <form:form method="POST" action="/selectFromList">
        <form:select path="selected" commandName="selected">
            <form:option value="NONE" label="--- Select ---"/>
            <form:options items="${selectModel}"/>
            <input type="submit" value="Submit">
        </form:select>
    </form:form>
</div>

<!-- result -->
<div id="results">
<c:if test="${docName!=null}">
    <a href=${full}>
        <img src=${thumbUrl}/>
    </a>
    <p>
        <b>Name: </b> ${docName} <br><br>
        <b>Description: </b>${docDescription}<br><br>
        <b>Thumbnail URL: </b> ${thumbUrl}<br><br>
        <b>Full Document URL: </b> ${full}<br><br>
    </p>
    <p>
        <b>Edit:</b> <br>
        <form:form method="POST" action="/selectFromList">
            <form:label path="selectedPath">Current File (read-only):</form:label>
            <form:input readonly="true" width="200" value= "/User Homes/abbott/${docName}" path="selectedPath"/><br>
            <form:label path="newName">Name: </form:label>
            <form:input path="newName"/><br>
            <form:label path="newDescription">Description: </form:label>
            <form:textarea value="${docDescription}" path="newDescription"/>
            <br>
            <input type="submit" value="Submit"/>
        </form:form>

    </p>
</c:if>
  <c:if test="${didNameChange ==true}">
      <p><b>Name change to ${newName} was successful.</b></p>
</c:if>
<c:if test="${didDescriptionChange ==true}">
    <p><b>The description for ${newName} was successfully updated.</b></p>
</c:if>
<c:if test="${noEditsMade==true}">
    <p><b>No edits made</b></p>
</c:if>

</div>
</body>
</html>
