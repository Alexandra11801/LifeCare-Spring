<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-4 pb-4" method="post">
            <#if !error??>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="email@domain.com">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="* * * * * * * *">
                </div>
            <#else>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control is-invalid" id="email" name="email" placeholder="email@domain.com">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control is-invalid" id="password" name="password" placeholder="* * * * * * * *">
                    <div class="invalid-feedback">Email or password is invalid</div>
                </div>
            </#if>

<#--            <div class="remember-me-check">-->
<#--                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="rememberMe">-->
<#--                <label class="form-check-label" for="defaultCheck1">-->
<#--                    Check me out-->
<#--                </label>-->
<#--            </div>-->

            <div class="form-group">
                <button type="submit" class="mt-2 btn btn-success">Log in</button>
            </div>
        </form>
    </div>
</#macro>

<#macro title>Authorization</#macro>

<@main/>