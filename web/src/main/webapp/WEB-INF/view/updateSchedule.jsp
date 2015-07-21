<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>添加新用户</title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
</head>
<body>
<div class="jumbotron">
    <nav class="navbar navbar-default">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">健身房管理系统</a>
                <a class="navbar-brand" href="/web/courses/">课程页</a>
                <a class="navbar-brand" href="/web/schedules/">课表页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/web/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <form class="form-horizontal" action="/web/schedules/update" method="post">
            <input type="hidden" value="${schedule.id}" name="id"><br>
            <div class="form-group">
                <label for="time" class="col-sm-2 control-label">Time</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="time" name="time" value="${schedule.time}">
                </div>
            </div>
            <div class="form-group">
                <label for="courseName" class="col-sm-2 control-label">Course</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="courseName" name="courseName" value="${schedule.course.name}" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
<spring:url value="/lib/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/lib/js/jquery-1.11.1.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>
