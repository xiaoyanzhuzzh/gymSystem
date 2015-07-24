<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>添加私教课程</title>
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
                <a class="navbar-brand" href="/web/employees/">雇员管理</a>
                <a class="navbar-brand" href="/web/users/">用户管理</a>
                <a class="navbar-brand" href="/web/courses/">课程管理</a>
                <a class="navbar-brand" href="/web/schedules/">课表管理</a>
                <a class="navbar-brand" href="/web/customers/">顾客管理</a>
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
        <form class="form-horizontal" action="/web/schedules/createPrivate" method="post">
            <div class="form-group">
                <label for="customerId" class="col-sm-2 control-label">Customer</label>
                <div class="col-sm-4">
                    <select class="form-control" name="customerId" id="customerId">
                        <option value="Customer"> Customer</option>
                        <c:forEach items="${customers}" var="customer">
                            <option value="${customer.id}">${customer.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="coachId" class="col-sm-2 control-label">Coach</label>
                <div class="col-sm-4">
                    <select class="form-control" name="coachId" id="coachId">
                        <option value="Coach"> Coach</option>
                        <c:forEach items="${coaches}" var="coach">
                            <option value="${coach.id}">${coach.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="courseId" class="col-sm-2 control-label">Course</label>
                <div class="col-sm-4">
                    <select class="form-control" name="courseId" id="courseId">
                        <option value="Course"> Course</option>
                        <c:forEach items="${courses}" var="course">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="time" class="col-sm-2 control-label">Time</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="time" name="time" placeholder="Time">
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
</body>
</html>