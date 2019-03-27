<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 22.03.2019
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="review.jsp"/>

<form name="sentReview" action="/review" method="post" style="${(timeOver) ? 'display:none;' : ''}" novalidate>
    <div class="row">
        <div class="form-group col-xs-12">
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="My Topic" name="topic">
                </div>
            </div>
            <button type="submit" class="btn btn-default">Send</button>
        </div>
    </div>
</form>
