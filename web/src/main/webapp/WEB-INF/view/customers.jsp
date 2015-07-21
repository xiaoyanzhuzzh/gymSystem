<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Javaee首页</title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
</head>
<body>
<div class="jumbotron">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/web/">健身房管理系统</a>
                <a class="navbar-brand" href="/web/courses/">课程信息</a>
                <a class="navbar-brand" href="/web/schedules/">课表信息</a>
                <a class="navbar-brand" href="/web/customers/">顾客信息</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/web/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div><a href="/web/customers/create">addCustomer</a></div>
        <table class="table table-bordered">
            <caption><h2>课程表</h2></caption>
            <thead>
            <tr>
                <th>Name</th>
                <th>Course</th>
                <th>Private Coach</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.name}</td>
                    <td>
                        <c:forEach items="${schedules}" var="schedule">
                            <div>
                                <span>${schedule.course.name}</span>
                                <span>${schedule.time}</span>
                            </div>
                        </c:forEach>
                    </td>
                    <td>${customer.employee.name}</td>
                    <td>
                            <%--<a href="/web/courses/delete/${customer.id}">delete</a>--%>
                        <a href="/web/courses/update/${customer.id}">update</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<spring:url value="/lib/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/lib/js/jquery-1.11.1.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>