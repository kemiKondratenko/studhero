<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Banner START -->
<div class="container-fluid" id="login-page">
    <div class="row">
        <div class="col-md-12 main-background">

            <div class="col-md-6 col-md-offset-3">
                <div class="row">
                    <div class="col-xs-6">
                        <ul class="nav nav-tabs">
                            <li role="presentation" data-bind="click: setUser.bind($data, 'student'), css: {'active': user() == 'student'}"><a>Студент</a></li>
                            <li role="presentation" data-bind="click: setUser.bind($data, 'company'), css: {'active': user() == 'company'}"><a>Компанія</a></li>
                        </ul>
                        <form id="student-login-form" method="POST" action="" data-bind="visible: user() == 'student'" novalidate="novalidate">
                            <div class="form-group">
                                <label for="username" class="control-label">User Name</label>
                                <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you name" placeholder="">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                                <span class="help-block"></span>
                            </div>
                            <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="remember" id="remember"> Remember login
                                </label>
                            </div>
                            <button type="submit" class="btn btn-default btn-main-green">Login</button>
                            <a href="" class="btn btn-default btn-block">Help to login</a>
                        </form>
                        <form id="company-login-form" method="POST" action="" data-bind="visible: user() == 'company'" novalidate="novalidate">
                            <div class="form-group">
                                <label for="username" class="control-label">Company Name</label>
                                <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                                <span class="help-block"></span>
                            </div>
                            <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="remember" id="remember"> Remember login
                                </label>
                            </div>
                            <button type="submit" class="btn btn-default btn-main-green">Login</button>
                            <a href="" class="btn btn-default btn-block">Help to login</a>
                        </form>

                    </div>
                    <div class="col-xs-6">
                        <p class="lead">Register now for <span class="text-success">FREE</span></p>
                        <ul class="list-unstyled" style="line-height: 2">
                            <li><span class="fa fa-check text-success"></span> See all your orders</li>
                            <li><span class="fa fa-check text-success"></span> Fast re-order</li>
                            <li><span class="fa fa-check text-success"></span> Save your favorites</li>
                            <li><span class="fa fa-check text-success"></span> Fast checkout</li>
                            <li><span class="fa fa-check text-success"></span> Get a gift <small>(only new customers)</small></li>
                            <li><a href="/read-more/"><u>Read more</u></a></li>
                        </ul>
                        <p><a href="registration" class="btn btn-info btn-block">Yes please, register now!</a></p>
                    </div>
                </div>
            </div>
        </div>                
    </div>
</div>   
<!-- Banner END -->
