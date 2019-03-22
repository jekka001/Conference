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
        <a href="/conference">
        <h2 class="post-title">
            ${conference_list.title}
        </h2>
    </a>
        <div class="row">
            <div class="form-group col-xs-12">
                <input type="hidden" class="form-control" placeholder="${conference_list.id}" >
                <input type="text" class="form-control" placeholder="Time conduction: ${conference_list.timeConduction}" readonly> <Br>
                <input type="text" class="form-control" placeholder="Venue: ${conference_list.venue}" readonly>
            </div>
        </div>
</div>

<hr>
    </c:forEach>
