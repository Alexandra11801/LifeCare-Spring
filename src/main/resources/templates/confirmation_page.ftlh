<!doctype html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport"
             content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
       <meta http-equiv="X-UA-Compatible" content="ie=edge">
       <title><@spring.message 'confirmation.page.title'/></title>
   </head>
   <body>
   <#if confirmed == true>
       <h1><@spring.message 'confirmation.page.confirmed'/></h1>
   <#else>
       <h1><@spring.message 'confirmation.page.error'/></h1>
   </#if>
   </body>
   </html>
