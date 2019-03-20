<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form name="sentMessage" action="/conference" method="post" novalidate>
    <div class="row">
        <div class="form-group col-xs-12">
            <input type="hidden" placeholder="idReview">
            <input type="text" placeholder="Title" readonly>
            <input type="text" placeholder="Topic" readonly>
            <input type="text" placeholder="Speaker:" readonly>
            <input type="text" placeholder="Register" readonly>
            <input type="text" placeholder="Visitors" readonly>
            <button type="submit" class="btn btn-default">Register</button>
        </div>
    </div>
</form>