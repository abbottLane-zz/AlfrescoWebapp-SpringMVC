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
    <li class="tab-title active"><a href="<c:url value="/filterByType"/>"><b>Filter by file type</b></a></li>
    <li class="tab-title"><a href="<c:url value="/upload"/>"><b>Upload</b></a></li>
    <li class="tab-title"> <a href="<c:url value="/selectFromList"/>"><b>Select and Edit</b></a></li>
    <li class="tab-title"><a href="<c:url value="/videoDemo"/>"><b>Video</b></a></li>
</ul>

<!-- form -->
<div id="form">
<p>You can retrieve one or several alfresco documents at a time by executing queries. Choose a
    filetype to retrieve filtered query results:</p>
<form:form method="POST" action="/filterByType">
    <form:radiobutton path="filetype" value="image"/>Images
    <form:radiobutton path="filetype" value="txt"/>Text Documents
    <form:radiobutton path="filetype" value="mp3"/>Audio
    <form:radiobutton path="filetype" value="mp4"/>Video<br>
    <td colspan="2">
        <input type="submit" value="Submit">
    </td>
</form:form>
</div>

<!-- results -->
<div id="results">

<c:if test="${numberOfResults!=null}">
    <p>Number of results: ${numberOfResults}</p>

    <c:forEach var="i" begin="0" end="${numberOfResults}" >
        <c:if test="${i==numberOfResults || thumbUrlList[0]==null}">
             <!-- leave empty...this just prevents a broken link image from showing up at the ned of our query result list -->
        </c:if>
        <c:if test="${i!=numberOfResults}">
            <a id="imgRef" title="${documentNames[i]}" href="${fullUrlList[i]}" >
            <img src=<c:out value="${thumbUrlList[i]}" />/>
            </a>
        </c:if>
    </c:forEach>
</c:if>
</div>

</body>
</html>