<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="conference_list" items="${list}">
<div class="post-preview">
    <form name="sentConference" action="/conference" method="get" novalidate>
        <button type="submit" name="title" class="btn-link" value="${conference_list.title}">${conference_list.title}</button>
        <input type="hidden" class="form-control" value="${conference_list.id}" name = "idConference" >
        <input type="hidden" class="form-control" value="${conference_list.timeConduction}" name = "timeConduction" >
    </form>
        <div class="row">
            <div class="form-group col-xs-12">
                <input type="text" class="form-control" placeholder="Time conduction: " value="${conference_list.timeConduction}" name = "timeConduction" readonly> <Br>
                <input type="text" class="form-control" placeholder="Venue: " value="${conference_list.venue}" name="venue" readonly>
            </div>
        </div>
</div>

<hr>
    </c:forEach>
