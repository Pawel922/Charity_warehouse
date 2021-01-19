<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer id="contact">
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact" action="/send">
            <div class="form-group form-group--50"><input type="text" name="name" placeholder="Imię"/><p class="warning"></p></div>
            <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Nazwisko"/><p class="warning"></p></div>
            <div class="form-group"><textarea name="message" placeholder="Wiadomość" rows="1"></textarea><p class="warning"></p></div>
            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="/resources/images/icon-facebook.svg"/>" alt="Facebook icon"/></a>
            <a href="#" class="btn btn--small"><img src="<c:url value="/resources/images/icon-instagram.svg"/>" alt="Instagram icon"/></a>
        </div>
    </div>
</footer>

<script src="resources/js/app-footer.js"></script>
