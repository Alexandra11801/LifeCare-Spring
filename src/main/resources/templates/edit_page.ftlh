<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-4 pb-4" enctype="multipart/form-data" method="post">
            <div class="form-group">
                <label for="name"><@spring.message 'edit.page.name'/></label>
                <input type="text" class="form-control" id="name" name="name" value="${user.getName()}">
            </div>
            <div class="form-group">
                <label for="surname"><@spring.message 'edit.page.surname'/></label>
                <input type="text" class="form-control" id="surname" name="surname" value="${user.getSurname()}">
            </div>
            <div class="form-group">
                <label for="phone"><@spring.message 'edit.page.phone'/></label>
                <input type="text" class="form-control" id="phone" name="phone" value="${user.getPhone()}">
            </div>
            <div class="form-group">
                <label for="email"><@spring.message 'edit.page.email'/></label>
                <input type="email" class="form-control" id="email" name="email" value="${user.getEmail()}">
            </div>

            <div class="form-group">
                <label for="avatar"><@spring.message 'edit.page.avatar'/></label>
                <input type="file" class="form-control" id="avatar" name="avatar">
            </div>

            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success"><@spring.message 'edit.page.edit'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'edit.page.title'/></#macro>

<@main/>