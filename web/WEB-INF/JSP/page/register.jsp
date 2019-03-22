<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>Register</p>
        <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
        <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
        <!-- NOTE: To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->
        <form name="sentRegisterDate"  action="/register" method="post" novalidate>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Name</label>
                    <input type="text" class="form-control" placeholder="Name" name="name" value ="${name}" required data-validation-required-message="Please enter your name.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Surname</label>
                    <input type="text" class="form-control" placeholder="Surname" name="surname" value ="${surname}" required data-validation-required-message="Please enter your surname.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Email Address</label>
                    <input type="email" class="form-control" placeholder="Email Address" name="email" value ="${login}" required data-validation-required-message="Please enter your email address.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password" required data-validation-required-message="Please enter your password.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Repeat Password</label>
                    <input type="password" class="form-control" placeholder="Repeat Password" name="repeat_password" required data-validation-required-message="Please enter your password.">
                    <p class="help-block text-danger"></p>
                </div>
            </div>

            <br>
            <div id="success"></div>
            <div class="row">
                <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Register</button>
                </div>
            </div>
        </form>
    </div>
</div>