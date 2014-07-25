<%--
  Created by IntelliJ IDEA.
  User: wlane
  Date: 7/8/14
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Alfresco Java Plugin Demo</title>
    <link href="<c:url value="/resources/css/theme.css" />" rel="stylesheet">
</head>
<body>
    <ul id="nav" class="tabs" data-tab>
        <li class="tab-title"><a href="/hello">Hello World</a></li>
        <li class="tab-title active"><a href="/helloAgain">Hello World2</a></li>
        <li class="tab-title"><a href="/index">Get by Path</a></li>
        <li class="tab-title"><a href="/filter.jsp">Filter by file type</a></li>
        <li class="tab-title"><a href="/upload.jsp">Upload</a></li>
        <li class="tab-title"> <a href="/upload.jsp">Select</a></li>
        <li class="tab-title"><a href="/video.jsp">Video</a></li>
    </ul>
    <p>A secondary test page ${variable}</p>
</body>
</html>
