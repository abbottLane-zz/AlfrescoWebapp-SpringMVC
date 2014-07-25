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
    <li class="tab-title"> <a href="<c:url value="/selectFromList"/>"><b>Select and Edit</b></a></li>
    <li class="tab-title active"><a href="<c:url value="/videoDemo"/>"><b>Video</b></a></li>
</ul>

<div id="form">
<p>This is an example of the newly upgraded video media preview capability with Alfresco v4.2:</p>
<h2>Fantasia on a Theme by Thomas Tallis, by Ralph Vaughan Williams</h2>
<p>Performed by the BBC Philharmonic in Gloucester Cathedral, where it was first performed in 1910. </p>
<video width=1000, height=600 controls>
    <source src="${video}" type="video/mp4">
    <source src="${video}" type="video/ogg">
</video>
<br>
</div>
</body>
</html>
