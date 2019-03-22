<%--
  Created by IntelliJ IDEA.
  User: Jekka
  Date: 18.03.2019
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <p>
            Want to get in touch with me? Fill out the form below to send me a message and
            I will try to get back to you within 24 hours!
        </p>

        <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
        <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
        <!-- NOTE: To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->

        <form name="sentMessage" id="contactForm" action="/contact" method="post" novalidate>
            <c:choose>
                <c:when test="${currentRole != null}">
                    <jsp:include page="/WEB-INF/JSP/fragment/contact/contactForRegisteredUser.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="/WEB-INF/JSP/fragment/contact/contactForAll.jsp"/>
                </c:otherwise>
            </c:choose>

            <br>
            

            <div class="row">
                <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Send</button>
                </div>
            </div>

        </form>

    </div>
</div>