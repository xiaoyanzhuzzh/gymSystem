<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        用户注册页面
    </title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/lib/css/login.css" var="loginCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${loginCss}" rel="stylesheet"/>
</head>
<body>
    <div class="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-md-12" id="loginTitle">
                    <h2>用户注册</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <form class="form-horizontal" action="" method="post">
                        <div class="form-group">
                            <label for="nickname" class="col-sm-2 control-label">Nickname:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nickname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Role:</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="role" value="coach"> coach
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="role" value="hr"> hr
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="role" value="ops"> ops
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">Password:</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Gender:</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="gender" value="female"> female
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender" value="male"> male
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="age" class="col-sm-2 control-label">Age:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="age" name="age" placeholder="Age">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email:</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Register</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
