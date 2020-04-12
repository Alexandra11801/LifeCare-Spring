<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-4 pb-4" action="/signup" enctype="multipart/form-data" method="post" novalidate>
            <div class="form-group">
                <label for="firstname"><@spring.message 'signup.page.name'/></label>
                <input type="text" class="form-control" id="name" name="name" placeholder="<@spring.message 'signup.page.name'/>">
            </div>
            <div class="form-group">
                <label for="lastname"><@spring.message 'signup.page.surname'/></label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder="<@spring.message 'signup.page.surname'/>">
            </div>
            <div class="form-group">
                <label for="email"><@spring.message 'signup.page.phone'/></label>
                <#if !incorrect_phone_number??>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="+7**********">
                <#else>
                    <input type="text" class="form-control  is-invalid" id="phone" name="phone" placeholder="+7**********">
                </#if>
                <div class="invalid-feedback">
                    <@spring.message 'signup.page.invalidPhone'/>
                </div>
            </div>
            <div class="form-group">
                <label for="email"><@spring.message 'signup.page.email'/></label>
                <#if !user_exists?? && !incorrect_email??>
                    <input type="email" class="form-control" id="email" name="email" placeholder="email@domain.com">
                <#else>
                    <input type="email" class="form-control  is-invalid" id="email" name="email" placeholder="email@domain.com">
                </#if>
                <div class="invalid-feedback">
                    <#if incorrect_email??>
                        <@spring.message 'signup.page.invalidEmail'/>
                    <#else>
                        <@spring.message 'signup.page.userExists'/>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="password"><@spring.message 'signup.page.password'/></label>
                <#if !incorrect_password?? && !password_invalid??>
                    <input type="password" class="form-control" id="password" name="password" placeholder="* * * * * * * *">
                <#else>
                    <input type="password" class="form-control is-invalid" id="password" name="password" placeholder="* * * * * * * *">
                </#if>
                <div class="invalid-feedback">
                    <#if password_invalid??>
                        <@spring.message 'signup.page.invalidPassword'/>
                    <#else>
                        <@spring.message 'signup.page.dontMatch'/>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="passwordconfirm"><@spring.message 'signup.page.repeat'/></label>
                <#if !incorrect_password??>
                    <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" placeholder="* * * * * * * *">
                <#else>
                    <input type="password" class="form-control is-invalid" id="repeatPassword" name="repeatPassword" placeholder="* * * * * * * *">
                </#if>
                <div class="invalid-feedback"><@spring.message 'signup.page.dontMatch'/></div>
            </div>

            <div class="form-group">
                <label for="avatar"><@spring.message 'signup.page.avatar'/></label>
                <input type="file" class="form-control" id="avatar" name="avatar">
            </div>

            <div class="form-group">
                <select class="form-control" id="confirmation" name="confirmation">
                    <option><@spring.message 'signup.page.emailConfirm'/></option>
                    <option><@spring.message 'signup.page.smsConfirm'/></option>
                </select>
            </div>

<#--            <div class="remember-me-check">-->
<#--                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="rememberMe">-->
<#--                <label class="form-check-label" for="defaultCheck1">-->
<#--                    Check me out-->
<#--                </label>-->
<#--            </div>-->

            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success"><@spring.message 'signup.page.signup'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'signup.page.title'/></#macro>

<@main/>