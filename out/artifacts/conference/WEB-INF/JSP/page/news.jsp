<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <jsp:include page="../fragment/allSpeaking.jsp"/>
        <hr>

        <ul class="pager">
            <li class="previous">
                <a href="#">&larr; New Review</a>
            </li>
            <li class="next">
                <a href="#">Older Review &rarr;</a>
            </li>
        </ul>
    </div>
</div>