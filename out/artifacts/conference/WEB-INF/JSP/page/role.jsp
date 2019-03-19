<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>Choose your role:</p>
        <form name="sentMessage" id="contactForm" action="/role" method="post" novalidate>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <p>
                        <input type="radio" name="role" value="rad1"> Адміністратор <Br>
                        <input type="radio" name="role" value="rad2"> Модератор <Br>
                        <input type="radio" name="role" value="rad3"> Спікер <Br>
                        <input type="radio" name="role" value="rad4"> Користувач <Br>
                    </p>
                </div>
            </div>
            <br>
            <div id="success"></div>
            <div class="row">
                <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Continue</button>
                </div>
            </div>
        </form>
    </div>
</div>
