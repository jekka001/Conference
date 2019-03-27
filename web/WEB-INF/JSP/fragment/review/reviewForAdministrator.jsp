<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 22.03.2019
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="review_list" items="${listItems}">
    <c:if test="${review_list.state == 'Accepted'}">
<form action="/review" method="post" novalidate>
    <div class="row">
      <div class="form-group col-xs-12">
            <input type="hidden" class="form-control" name="idReview" value="${review_list.id}">
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="topic"  value="${review_list.topic}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="hidden" name="speakerName" value="${review_list.speaker.name}">
                    <input type="text" class="form-control"  value="${review_list.speaker.name}  ${review_list.speaker.surname} " readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="register" value="${review_list.getIntRegistered()}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" name="visitors" value="${review_list.visitors}" style="${(timeOver) ? '' : 'display:none;' }">
                </div>
            </div>
            <button type="submit" class="btn btn-default" style="${(timeOver) ? '' : 'display:none;' }">Save</button>
        </div>
    </div>
</form>
    </c:if>
</c:forEach>