<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="callout">
    <c:choose>
        <c:when test="${is404 && url != null}">
            <h6>Requested resource <strong>${url }</strong> not found!</h6>
        </c:when>
        <c:when test="${is404}">
            <h6>Requested resource not found!</h6>
        </c:when>
        <c:otherwise>
            <h6>Error, Please try again later...</h6>
        </c:otherwise>
    </c:choose>
</div>