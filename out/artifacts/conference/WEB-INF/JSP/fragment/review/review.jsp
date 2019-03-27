<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="review_list" items="${listItems}">
    <c:if test="${review_list.state == 'Accepted' || speaker != null}">
    <div class="row">
        <div class="form-group col-xs-12">
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="topic"  value="${review_list.topic}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="speaker" value="${review_list.speaker.name}  ${review_list.speaker.surname}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="register" value="${review_list.getIntRegistered()}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="visitors" value="${review_list.visitors}" readonly>
                </div>
            </div>
            <form name="sentConferenceRegistered" action="${(user != null) ? '/review' : '/signIn' }" method="get" novalidate>
                <button type="submit" class="btn btn-default" style="${(review_list.getRegistered().contains(user)) ? 'display:none;' : ''}">Register</button>
                <input type="hidden" name="idReview" value="${review_list.id}">
                <input type="hidden" name="idUser" value="${user.id}">
            </form>
        </div>
    </div>
    </c:if>
</c:forEach>