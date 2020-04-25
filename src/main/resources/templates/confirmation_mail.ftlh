<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Confirm account</title>
</head>
<body>
    <h2>Hello, ${user.getName()}!</h2>
    <div>Please, confirm your account</div>
    <div><a href="http://localhost:1010/email_confirm/${confirmation_code}">localhost:1010/email_confirm/${confirmation_code}</a></div>
</body>
</html>