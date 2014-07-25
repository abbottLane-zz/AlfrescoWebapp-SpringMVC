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
        <li class="tab-title active"><a href="<c:url value="/"/>"><b>Get by Path</b></a></li>
        <li class="tab-title"><a href="<c:url value="/filterByType"/>"><b>Filter by file type</b></a></li>
        <li class="tab-title"><a href="<c:url value="/upload"/>"><b>Upload</b></a></li>
        <li class="tab-title"> <a href="<c:url value="/selectFromList"/>"><b>Select and Edit</b></a></li>
        <li class="tab-title"><a href="<c:url value="/videoDemo"/>"><b>Video</b></a></li>
    </ul>

    <!-- form -->
    <div id="form">
    <p>Retrieve an Alfresco Document by typing the path to the document in the following form: </p>
    <form:form method="POST" action="/">
            <form:label path="Path">Path: </form:label>
            <form:input path="Path"/><br>
            <input type="submit" value="Submit"/>
    </form:form>
    </div>

    <!-- results -->
    <div id="results">
        <c:if test="${docName!=null}">
            <a href=${fullUrl}>
                <img src=${thumbnail}/>
            </a>
            <p>
                <b>Name: </b> ${docName} <br><br>
                <b>Description: </b>${docDescription}<br><br>
                <b>Thumbnail URL: </b> ${thumbnail}<br><br>
                <b>Full Document URL: </b> ${fullUrl}<br><br>
            </p>
        </c:if>
    </div>

</body>
</html>