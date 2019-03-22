<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 22.03.2019
  Time: 16:31
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
        <form name="sentConference" action="/conference" method="post" novalidate>
            <div class="row">
                <div class="form-group col-xs-12">
                    <input type="hidden" class="form-control" placeholder="${conference_list.id}" >
                    <input type="text" class="form-control" placeholder="Time conduction: ${conference_list.timeConduction}" > <Br>
                    <input type="text" class="form-control" placeholder="Venue: ${conference_list.venue}" ><Br>
                    <div class="row">
                        <div class="form-group col-xs-12">
                            <button type="submit" name="Save" class="btn btn-default">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <hr>
</c:forEach>
