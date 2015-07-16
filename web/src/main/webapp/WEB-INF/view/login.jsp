<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登陆页面</title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/lib/css/login.css" var="loginCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${loginCss}" rel="stylesheet"/>
</head>
<body>
    <div class="jumbotron" id="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-md-12" id="loginTitle">
                    <h2>用户登录</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <form class="form-horizontal" action="" method="post">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Password:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Login</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
