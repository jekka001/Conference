<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>Sign in</p>

        <form name="sentMessage"  action="/signIn" method="post" novalidate>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Login</label>
                    <input type="email" class="form-control" placeholder="Login" id="login" required data-validation-required-message="Please enter your login.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Password" id="password" required data-validation-required-message="Please enter your password.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <br>
            <div id="success"></div>

            <div class="row">
                <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Sing in</button>
                </div>
            </div>
        </form>
    </div>
</div>