<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<!--Created by Andrey on 01.03.2015-->
<head>
    <title>CCloud|Welcome</title>
    <link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/welcome.css" />">
</head>

<body>

<c:url var="signinup" value="/signinup" />
<c:url var="home" value="/home" />
<c:url var="welcome" value="/welcome" />
<c:url var="about" value="#" />
<c:url var="ourteam" value="#" />

<div class="main">
    <div class="header">
        <div class="nav navigator">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="${relevantPage}"> <i class="fa fa-cloud"></i>ChallengeCloud
                        </a>
                    </div>

                    <div class="col-md-9">
                        <ul class="pull-right">
                            <li><a href="${home}">Home</a></li>
                            <li><a href="${about}">About</a></li>
                            <li><a href="${ourteam}">Our Team</a></li>
                            <li><a href="${signinup}">
                                <button type="button" class="btn btn-primary btn-lg">Sign In</button>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="jumbotron">
        <div class="container">
            <h1>Change your life through Challenge!</h1>
            <%--<ul>--%>
                <%--<li>--%>
                    <%--<a class="btn btn-primary btn-lg" href="${signinup}" role="button">Sign In</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a class="btn btn-primary btn-lg" href="${about}" role="button">About</a>--%>
                <%--</li>--%>
            <%--</ul>--%>

            <form:form name="signinupForm" action="${about}" method="GET" cssClass="pull-right">
                <button class="btn btn-primary btn-lg" type="submit">About</button>
            </form:form>

            <form:form name="signinupForm" action="${signinup}" method="GET" cssClass="pull-right">
                <button class="btn btn-primary btn-lg" type="submit">Sign In</button>
            </form:form>

        </div>
    </div>

    <div class="learn-more">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h3><i class="fa fa-wechat"></i> Social</h3>

                    <p>Use your favourite social network to track your progress and let others know of your progress</p>
                </div>
                <div class="col-md-4">
                    <h3><i class="fa fa-list-ul"></i> What you need</h3>

                    <p>Already decided how you wish to develop? Then choose one of many categories. Otherwise we'll help
                        you chose something suitable for you</p>
                </div>
                <div class="col-md-4">
                    <h3><i class="fa fa-check-square"></i> Track</h3>

                    <p>...your progress and unlock various achievements! Build your own path to the clouds</p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    <form action="<c:url value="/signin/twitter" />" method="POST" class="submitForm">
        <button type="submit"><i class="fa fa-twitter fa-3x"></i></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <form action="#" method="POST" class="submitForm">
        <button type="submit"><i class="fa fa-vk fa-3x"></i></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <form action="#" method="POST" class="submitForm">
        <button type="submit"><i class="fa fa-facebook-official fa-3x"></i></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <form action="#" method="POST" class="submitForm">
        <button type="submit"><i class="fa fa-instagram fa-3x"></i></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>