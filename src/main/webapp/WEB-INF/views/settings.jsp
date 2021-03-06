<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<!--Created by Andrey on 01.03.20115-->
<head>
    <title>CCloud | Settings</title>
    <link rel="shortcut icon" href="<c:url value="/resources/img/cloud-icon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />">
</head>

<body>
    <c:url var="welcome" value="/welcome"/>
    <c:url var="home" value="/home"/>
    <c:url var="settings" value="/settings"/>
    <c:url var="subscription" value="/subscription"/>
    <c:url var="about" value="#"/>
    <c:url var="ourteam" value="#"/>
    <c:url var="challenges" value="/challenges"/>
    <c:url var="trend" value="#"/>
    <c:url var="trophyroom" value="/trophy"/>
    <c:url var="history" value="/history"/>
    <c:url var="relevantPage" value="/"/>
    <c:url var="user" value="#"/>
    <c:url var="general" value="#"/>
    <c:url var="socialnetworks" value="#"/>
    <c:url var="security" value="#"/>
    <c:url value="/connect/twitter" var="connect"/>
    <c:url var="update" value="/settings"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

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
                            <li>
                                <form:form name="logoutForm" action="${logoutUrl}" method="POST"
                                           cssClass="logoutForm pull-right">
                                    <button class="btn btn-default btn-lg" type="submit"> Log out</button>
                                </form:form>
                            </li>
                        </ul>
                        <div class="pull-right">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <div class="main">
        <div class="row">
            <div class="col-md-2 col-md-offset-2">
                <div class="menu">
                    <!-- <ul class="nav nav-pills nav-stacked"> -->
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="${home}">Home</a></li>
                        <li role="presentation"><a href="${challenges}">Challenges</a></li>
                        <li role="presentation"><a href="${trophyroom}">Trophy Room</a></li>
                        <li role="presentation"><a href="${history}">My history</a></li>
                        <li role="presentation" class="active"><a href="${settings}">Settings</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-6">
                <div class="settings">
                    <h2> User </h2>
                    <c:set value="${CCloudEmail}" var="CCloudEmail"/>
                    <c:set value="${CCloudLogin}" var="CCloudLogin"/>
                    <c:set value="${CCloudName}" var="CCloudName"/>
                    <c:set value="${CCloudPassword}" var="CCloudPassword"/>
                    <form:form commandName="user" name="updateForm" action="${update}"
                               method='POST'>
                        <div class="form-group">
                            <label for="inputName" class="control-label col-md-2"></label>

                            <div>
                                <form:input path="name" type="login" class="form-control" id="inputName"
                                            placeholder="Name" style="font-size: 20px;"
                                            value="${CCloudName}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputLogin" class="control-label col-md-2"></label>

                            <div>
                                <form:input path="login" type="login" class="form-control" id="inputLogin"
                                            placeholder="Login" style="font-size: 20px;"
                                            value="${CCloudLogin}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="control-label col-md-2"></label>

                            <div>
                                <c:choose>
                                    <c:when test="${not empty CCloudPassword}">
                                        <c:set value="true" var="isDisabled"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="false" var="isDisabled"/>
                                    </c:otherwise>
                                </c:choose>
                                <form:password path="password" class="form-control" id="inputPassword"
                                               placeholder="Password" style="font-size: 20px;"
                                               value="${CCloudPassword}" disabled="${isDisabled}" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail" class="control-label col-md-2"></label>

                            <div>
                                <form:input path="email" type="email" class="form-control" id="inputEmail"
                                            placeholder="Email" style="font-size: 20px;"
                                            value="${CCloudEmail}"/>
                            </div>
                        </div>
                        <div class="text-right">
                            <button type="submit" class="btn btn-primary"> Save</button>
                        </div>
                    </form:form>

                    <h2>Social networks</h2>

                    <h3>Twitter</h3>

                    <div>
                        <c:if test="${not empty twitterName && not empty profileUrl}">
                            <img src="${imgSrc}"/>
                            <a href="${profileUrl}">${twitterName}</a>
                        </c:if>
                        <c:choose>
                            <c:when test="${isConnectedToTwitter == 'true'}">
                                <form:form action="${connect}" method="DELETE" cssClass="provider-connect">
                                    <button class="btn btn-warning pull-right" type="submit">Disconnect</button>
                                </form:form>
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-twitter fa-3x"></i>
                                <form:form action="${connect}" method="POST" cssClass="provider-connect">
                                    <button class="btn btn-primary pull-right" type="submit">Connect</button>
                                </form:form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${not empty setSave}">
                        <div>
                            <p>${setSave}</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>