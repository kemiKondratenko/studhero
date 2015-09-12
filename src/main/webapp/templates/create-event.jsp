<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Banner START -->
<div class="container-fluid" id="create-event-block">
    <div class="row">
        <div class="col-md-12 main-background">
            <div class="col-md-4 col-md-offset-4">
                
                <div class="row">
                    <h2 class="text-center">Нова подія</h2>
                    <form id="student-registarte-form" method="POST" action="" novalidate="novalidate" data-bind="visible: user() == 'student'">
                        <div class="form-group">
                            <label for="username" class="control-label">Заголовок</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Дата</label>
                            <input type="date" class="form-control" id="username" name="username" required="" title="Please enter you username">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Час</label>
                            <input type="time" class="form-control" id="username" name="username" value="" required="" title="Please enter you username">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Місце</label>
                            <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label">Опис</label>
                            <textarea class="form-control" id="username" name="username" required="" title="Please enter you username" placeholder=""></textarea>
                            <span class="help-block"></span>
                        </div>
                        
                        <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                        
                        <button type="submit" class="btn btn-default btn-main-green">Створити</button>
                    </form>    
                    
                </div>
            </div>
        </div>                
    </div>
</div>   
<!-- Banner END -->
