<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <c:choose>
            <c:when test="${currentRole == 'Moderator'}">
                <jsp:include page="/WEB-INF/JSP/fragment/allConference/allConferenceForModerator.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/WEB-INF/JSP/fragment/allConference/allConference.jsp"/>
            </c:otherwise>
        </c:choose>
        <tags:pagination pagination="${pagination}"/>

    </div>
</div>