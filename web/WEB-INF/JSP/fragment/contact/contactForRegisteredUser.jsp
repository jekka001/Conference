<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 19.03.2019
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row control-group">
    <div class="form-group col-xs-12 floating-label-form-group controls">
        <label>Phone Number</label>
        <input type="tel" class="form-control" placeholder="Phone Number" id="phone" required data-validation-required-message="Please enter your phone number.">
        <p class="help-block text-danger"></p>
    </div>
</div>

<div class="row control-group">
    <div class="form-group col-xs-12 floating-label-form-group controls">
        <label>Message</label>
        <textarea rows="5" class="form-control" placeholder="Message" id="message" required data-validation-required-message="Please enter a message."></textarea>
        <p class="help-block text-danger"></p>
    </div>
</div>