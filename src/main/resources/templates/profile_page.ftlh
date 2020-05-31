<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <div class="text-center mt-3 mb-2">
            <div class="user-avatar">
                <img src="/files/${user.getImageName()}" width="128" height="128">
            </div>
            <h4 class="h4">${user.getName()} ${user.getSurname()}</h4>
            <#if user.getId() == current_user.getId()>
                <a href="/edit" class="btn-sm btn-info"><@spring.message 'profile.page.edit'/></a>
            </#if>
        </div>
        <div class="user-posts">
            <#list articles as article>
                <div class="user-post mt-3 mb-5">
                    <h2 class="h3"><a href="/article/${article.getTitle()}" class="text-success">${article.getTitle()}</a></h2>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#macro title>${user.getName()} ${user.getSurname()}</#macro>

<@main/>