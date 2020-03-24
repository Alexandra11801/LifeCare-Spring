<#macro content></#macro>

<#macro title></#macro>

<#macro username></#macro>

<#macro main>
    <!DOCTYPE html>
    <html lang="ru">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/commentsStyle.css">

        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script type="application/javascript" src="/js/jquery-3.4.1.min.js"></script>

        <title><@title/></title>
    </head>

    <body>

    <div class="header container">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="/">LifeCare</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <#if authorizated == false>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/signin">Log in</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/signup">Registration</a>
                        </li>
                    </ul>
                </div>
            <#else>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/search">Search</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="/${current_user.getImagePath()}" alt="" width="20" height="20">
                                ${current_user.getName()} ${current_user.getSurname()}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/profile/${current_user.getId()}">Profile</a>
<#--                                <a class="dropdown-item" href="/edit">Edit</a>-->
<#--                                <a class="dropdown-item" href="/revisions">Corrections</a>-->
                                <a class="dropdown-item" href="/add">Create article</a>
<#--                                <div class="dropdown-divider"></div>-->
<#--                                <a class="dropdown-item" href="/logout">Log out</a>-->
                            </div>
                        </li>
                    </ul>
                </div>
            </#if>

        </nav>
    </div>
    <@content/>
    <script type="application/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </body>
    </html>
</#macro>