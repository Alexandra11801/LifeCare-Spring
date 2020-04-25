<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="form-inline mt-4 pb-4" accept-charset="utf-8" method="post">
            <input class="form-control rounded-0 w-75" id="title" name="title" type="text" placeholder="Search...">
            <button class="btn btn-info rounded-0 w-25" type="submit"><@spring.message 'chat.page.send'/></button>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>

    <div class="messages">
        <#list messages as message>
<#--        <div class="media-left">-->
<#--             <a href="#">-->
<#--                  <img class="media-object img-rounded" src="${comment.getSenderAvatar()}" alt="..."  width="20" height="20">-->
<#--              </a>-->
<#--        </div>-->
            <div class="media-body">
                <div class="media-heading">
                    <div class="author">${message.getSenderName()}</div>
                    <div class="metadata">
                        <span class="date">${message.getDate()}</span>
                    </div>
                </div>
                <div class="media-text text-justify">${message.getText()}</div>
                <div class="footer-comment">
<#--                 <span class="comment-reply">-->
<#--                       <button class="reply btn btn-secondary" onclick="reply(this, '${comment.getSenderName()}')">Reply<span name="comm-id" id="comm-id" style="display: none">${comment.getId()}</span></button>-->
<#--                 </span>-->
                </div>
            </div>
        </#list>
    </div>
</#macro>

<#macro title><@spring.message 'chat.page.title'/></#macro>

<@main/>