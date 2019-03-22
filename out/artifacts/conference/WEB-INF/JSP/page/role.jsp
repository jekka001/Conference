<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>Choose your role:</p>

        <form name="sentUserRole" action="/role" method="post" novalidate>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <p>
                        <c:choose>
                            <c:when test="${user.role == 'Admin'}">
                                <jsp:include page="/WEB-INF/JSP/fragment/role/admin.jsp"/>
                            </c:when>
                            <c:when test="${user.role == 'Moderator'}">
                                <jsp:include page="/WEB-INF/JSP/fragment/role/moderator.jsp"/>
                            </c:when>
                            <c:when test="${user.role == 'Speaker'}">
                                <jsp:include page="/WEB-INF/JSP/fragment/role/speaker.jsp"/>
                            </c:when>
                            <c:when test="${user.role == 'User'}">
                                <jsp:include page="/WEB-INF/JSP/fragment/role/user.jsp"/>
                            </c:when>
                        </c:choose>
                    </p>
                </div>
            </div>

            <br>

            <div class="row">
                <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Continue</button>
                </div>
            </div>

        </form>
    </div>
</div>
