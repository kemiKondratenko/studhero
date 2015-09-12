<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle menu-button" data-toggle="collapse" data-target="#menu">
                        <span class="glyphicon glyphicon-align-justify"></span>                        
                    </button>                    
                </div>
                <span data-bind="click: showMain" class="logo">STUDHERO</span>
            </div>            
            <div class="col-md-8">
                <nav class="collapse navbar-collapse" role="navigation" id="menu">
                    <ul class="nav navbar-nav menu">
                        <li><a href="main" data-bind="css: {'active': currentPage() == 'index.jsp'}">Головна</a></li>
                        <li><a href="events" data-bind="css: {'active': currentPage() == 'events.jsp'}">Курси і тренінги</a></li>
                        <li><a href="faq" data-bind="css: {'active': currentPage() == 'faq.jsp'}">FAQ</a></li>
                        
                        
                    </ul>
                    <a class="btn btn-main-green" href="login" data-bind="">Логін</a>
                    <a class="btn btn-main-green" href="registration" data-bind="">Реєстрація</a>
                </nav>
            </div>
        </div>
    </div>
</header>