<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-4 pb-4" method="post">
            <#if !error??>
                <div class="form-group">
                    <label for="email"><@spring.message 'signin.page.email'/></label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="email@domain.com">
                </div>
                <div class="form-group">
                    <label for="password"><@spring.message 'signin.page.password'/></label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="* * * * * * * *">
                </div>
            <#else>
                <div class="form-group">
                    <label for="email"><@spring.message 'signin.page.email'/></label>
                    <input type="email" class="form-control is-invalid" id="email" name="email" placeholder="email@domain.com">
                </div>
                <div class="form-group">
                    <label for="password"><@spring.message 'signin.page.password'/></label>
                    <input type="password" class="form-control is-invalid" id="password" name="password" placeholder="* * * * * * * *">
                    <div class="invalid-feedback"><@spring.message 'signin.page.error'/></div>
                </div>
            </#if>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="remember-me-check">
                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="rememberMe">
                <label class="form-check-label" for="defaultCheck1">
                    <@spring.message 'signin.page.remember'/>
                </label>
            </div>

            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success"><@spring.message 'signin.page.signin'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'signin.page.title'/></#macro>

<@main/>