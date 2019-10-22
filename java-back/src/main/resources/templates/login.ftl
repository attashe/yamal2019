<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authentication Service</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="../css/login.css" rel="stylesheet">
</head>
<body>


<div class="wrapper fadeInDown">
    <div id="formContent">
        <div class="fadeIn first">
            <p>Authentication form</p>
        </div>

        <!-- Login Form -->
        <form method="POST" action="/web/login">
            <input type="text" id="p" class="fadeIn second" name="phoneNumber" placeholder="phoneNumber">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>


    </div>
</div>

<#--<form method="POST" action="/web/login">-->
<#--    <h2>Log in</h2>-->
<#--    <input name="phoneNumber" type="text" placeholder="PhoneNumber"-->
<#--           autofocus="true"/>-->
<#--    <br>-->
<#--    <input name="password" type="password" placeholder="Password"/>-->
<#--    <br>-->
<#--    <button type="submit">Log In</button>-->
<#--</form>-->
</body>
</html>
