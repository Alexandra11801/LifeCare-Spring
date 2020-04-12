<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-4 pb-4" method="post">
            <div class="form-group">
                <label for="code"><@spring.message 'smsConfirmation.page.code'/></label>
                <#if !error??>
                    <input type="text" class="form-control" id="code" name="code" placeholder="<@spring.message 'smsConfirmation.page.placeholder'/>">
                <#else>
                    <input type="text" class="form-control is-invalid" id="code" name="code" placeholder="<@spring.message 'smsConfirmation.page.placeholder'/>">
                </#if>
                <div class="invalid-feedback">
                    <@spring.message 'smsConfirmation.page.invalidCode'/>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success"><@spring.message 'smsConfirmation.page.confirm'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro  title>
    <@spring.message 'smsConfirmation.page.title'/>
</#macro>

<@main/>