<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 22.03.2019
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form name="sentChange" action="/conference" method="post" novalidate>
    <div class="row">
        <div class="form-group col-xs-12">
            <input type="hidden" placeholder="idReview">
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Topic">
                    <button type="submit" name = "suggest" class="btn btn-default">Suggest</button>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Speaker:" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Register" readonly>
                </div>
            </div>
            <div class="row control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                    <input type="text" class="form-control" placeholder="Visitors" readonly>
                </div>
            </div>
            <button type="submit" name = "saveChange" class="btn btn-default">Save</button>
        </div>
    </div>
</form>