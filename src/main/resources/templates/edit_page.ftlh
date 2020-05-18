<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <style>
            .error {
                color: #ff0000;
            }
        </style>
        <@spring.bind "editDto"/>
        <form class="pt-4 pb-4" enctype="multipart/form-data" method="post">
            <div class="form-group">
                <label for="name"><@spring.message 'edit.page.name'/></label>
                <@spring.formInput "editDto.name"/>
                <@spring.showErrors "", "error"/>
<#--                <input type="text" class="form-control" id="name" name="name" value="${current_user.getName()}">-->
            </div>
            <div class="form-group">
                <label for="surname"><@spring.message 'edit.page.surname'/></label>
                <@spring.formInput "editDto.surname"/>
                <@spring.showErrors "", "error"/>
<#--                <input type="text" class="form-control" id="surname" name="surname" value="${current_user.getSurname()}">-->
            </div>
            <div class="form-group">
                <label for="phone"><@spring.message 'edit.page.phone'/></label>
                <@spring.formInput "editDto.phone"/>
                <@spring.showErrors "", "error"/>
<#--                <input type="text" class="form-control" id="phone" name="phone" value="${current_user.getPhone()}">-->
            </div>
            <div class="form-group">
                <label for="email"><@spring.message 'edit.page.email'/></label>
                <@spring.formInput "editDto.email"/>
                <@spring.showErrors "", "error"/>
<#--                <input type="email" class="form-control" id="email" name="email" value="${current_user.getEmail()}">-->
            </div>

            <div class="form-group">
                <label for="avatar"><@spring.message 'edit.page.avatar'/></label>
                <input type="file" class="form-control" id="avatar" name="avatar">
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success"><@spring.message 'edit.page.edit'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'edit.page.title'/></#macro>
,
<@main/>