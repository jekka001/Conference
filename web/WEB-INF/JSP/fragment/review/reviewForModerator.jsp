<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 22.03.2019
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="review_list" items="${listItems}">
<form name="sentChange" action="/review" method="post" novalidate>
    <div class="row">
        <div class="form-group col-xs-12">
            <input type="hidden" placeholder="idReview">
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Topic" name="topic" value="${review_list.topic}">
                    <button type="submit"  class="btn btn-default">Suggest</button>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Speaker:" name="speaker" value="${review_list.speaker.name}  ${review_list.speaker.surname}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Register" name="register" value="${review_list.getIntRegistered()}" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Visitors" name="visitors" value="${review_list.visitors}" readonly>
                </div>
            </div>
            <button type="submit" class="btn btn-default">Save</button>
        </div>
    </div>
</form>
</c:forEach>