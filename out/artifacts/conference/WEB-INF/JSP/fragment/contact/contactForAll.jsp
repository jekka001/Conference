<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row control-group">
    <div class="form-group col-xs-12 floating-label-form-group controls">
        <label>Name</label>
        <input type="text" class="form-control" placeholder="Name" name="name" required data-validation-required-message="Please enter your name.">
        <p class="help-block text-danger"></p>
    </div>
</div>

<div class="row control-group">
    <div class="form-group col-xs-12 floating-label-form-group controls">
        <label>Email Address</label>
        <input type="email" class="form-control" placeholder="Email Address" name="email" required data-validation-required-message="Please enter your email address.">
        <p class="help-block text-danger"></p>
    </div>
</div>

<jsp:include page="contactForRegisteredUser.jsp"/>
