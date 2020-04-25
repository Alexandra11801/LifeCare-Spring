<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-3 mt-3 pb-4" method="post" accept-charset="utf-8">

            <div class="form-group">
                <label for="text"><@spring.message 'revise.page.text'/></label>
                <textarea class="form-control" id="text" rows="3" name="text" >${article.getText()}</textarea>
            </div>

            <div class="form-group">
                <label for="description"><@spring.message 'revise.page.description'/></label>
                <textarea class="form-control" id="description" rows="2" name="description"></textarea>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <button type="submit" class="btn btn-success"><@spring.message 'revise.page.suggest'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'revise.page.title'/></#macro>

<@main/>