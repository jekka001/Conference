<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>TITLE</p>
        <c:choose>
            <c:when test="${currentRole == 'Admin'}">
                <jsp:include page="/WEB-INF/JSP/fragment/review/reviewForAdministrator.jsp"/>
            </c:when>
            <c:when test="${currentRole == 'Moderator'}">
                <jsp:include page="/WEB-INF/JSP/fragment/review/reviewForModerator.jsp"/>
            </c:when>
            <c:when test="${currentRole == 'Speaker'}">
                <jsp:include page="/WEB-INF/JSP/fragment/review/reviewForSpeaker.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="../fragment/review/review.jsp"/>
            </c:otherwise>
        </c:choose>
        <form name="sentConferenceRegistered" action="${(user != null) ? '/conference' : '/signIn' }" method="get" novalidate>
            <!--idConference-->
            <button type="submit" name="ConferenceRegister" class="btn btn-default">Register</button>
        </form>
    </div>
</div>
