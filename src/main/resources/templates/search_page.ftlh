<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="form-inline mt-4 pb-4" accept-charset="utf-8" method="post">
            <input class="form-control rounded-0 w-75" id="title" name="title" type="text" placeholder="Search...">
            <button class="btn btn-info rounded-0 w-25" type="submit">Search</button>
<#--            <div class="form-group">-->
<#--                <label for="category">Cathegory</label>-->
<#--                <select class="form-control" id="category" name="category">-->
<#--                    <option>All categories</option>-->
<#--                    <#list categories as cat>-->
<#--                        <option>${cat}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
        </form>
    </div>


    <div class="container">
        <#list articles as article>
            <div class="border-top-0 border-left-0 border-right-0 border pt-3 pb-3">
                <h2 class="h3"><a href="/article?article=${article.getTitle()}" class="text-success">${article.getTitle()}</a></h2>
            </div>
        </#list>
    </div>

</#macro>

<#macro title>Search</#macro>

<@main/>