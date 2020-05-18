<#macro content></#macro>

<#macro title></#macro>

<#macro username></#macro>

<#macro main>
    <!DOCTYPE html>
    <html lang="en">
    <#import 'spring.ftl' as spring />
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/commentsStyle.css">

        <script type="application/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

        <title><@title/></title>
    </head>

    <body onload="if(typeof connect() == 'function'){connect()}">

    <div class="header container">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="/">LifeCare</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/search"><@spring.message 'header.search'/></a>
                    </li>
                    <#if authorizated == false>
                        <li class="nav-item">
                            <a class="nav-link" href="/signin"><@spring.message 'header.signin'/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/signup"><@spring.message 'header.signup'/></a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/chat"><@spring.message 'header.chat'/></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="/files/${current_user.getImageName()}" alt="" width="20" height="20">
                                ${current_user.getName()} ${current_user.getSurname()}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/profile/${current_user.getId()}"><@spring.message 'header.profile'/></a>
                                <a class="dropdown-item" href="/edit"><@spring.message 'header.edit'/></a>
                                <a class="dropdown-item" href="/revisions"><@spring.message 'header.revisions'/></a>
                                <a class="dropdown-item" href="/add"><@spring.message 'header.add'/></a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout"><@spring.message 'header.logout'/></a>
                            </div>
                        </li>
                    </#if>
                </ul>
            </div>
        </nav>
    </div>
    <@content/>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>