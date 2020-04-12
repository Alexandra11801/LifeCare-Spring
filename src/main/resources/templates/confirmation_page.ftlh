<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <#if confirmed == true>
            <h1><@spring.message 'confirmation.page.confirmed'/></h1>
        <#else>
            <h1><@spring.message 'confirmation.page.error'/></h1>
        </#if>
    </div>
</#macro>

<#macro title>
    <@spring.message 'confirmation.page.title'/>
</#macro>

<@main/>