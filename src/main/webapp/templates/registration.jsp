<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Banner START -->
<div class="container-fluid" id="registrate-block">
    <div class="row">
        <div class="col-md-12 main-background">
            <div class="col-md-4 col-md-offset-4">
                <div class="row">
                    <ul class="nav nav-tabs">
                        <li role="presentation" data-bind="click: setUser.bind($data, 'student'), css: {'active': user() == 'student'}"><a>Студент</a></li>
                        <li role="presentation" data-bind="click: setUser.bind($data, 'company'), css: {'active': user() == 'company'}"><a>Компанія</a></li>
                    </ul>
                    <form id="student-registarte-form" method="POST" action="" novalidate="novalidate" data-bind="visible: user() == 'student'">
                        <div class="form-group">
                            <label for="username" class="control-label">Name</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Sername</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Login</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Email</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="example@gmail.com">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label">Password Repeat</label>
                            <input type="password" class="form-control" id="password2" name="password" value="" required="" title="Please enter your password">
                            <span class="help-block"></span>
                        </div>
                        <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                    </form>    
                    <form id="company-registarte-form" method="POST" action="" novalidate="novalidate" data-bind="visible: user() == 'company'">
                        <div class="form-group">
                            <label for="username" class="control-label">Company Name</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>                        
                        <div class="form-group">
                            <label for="username" class="control-label">Login</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Email</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="example@gmail.com">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label">Password Repeat</label>
                            <input type="password" class="form-control" id="password2" name="password" value="" required="" title="Please enter your password">
                            <span class="help-block"></span>
                        </div>
                        <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>                    
                    </form> 
                    <button type="submit" class="btn btn-default btn-main-green">Зареєструвати</button>
                </div>
            </div>
        </div>                
    </div>
</div>   
<!-- Banner END -->
