<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 21.03.2019
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav navbar-right">
        <li>
        <c:out value="${user.name}"/>
        </li>
        <li>
            <a href="/logOut">Log out</a>
        </li>
        <li>
            <a href="/news">Home</a>
        </li>
        <li>
            <a href="/about">About</a>
        </li>
        <li>
            <a href="/contact">Contact</a>
        </li>
    </ul>
</div>
