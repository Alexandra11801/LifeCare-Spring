<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-5 pb-4" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Title">
            </div>
            <div class="form-group">
<#--                <label for="category">Cathegory</label>-->
<#--                <select class="form-control" id="category" name="category">-->
<#--                    <#list categories as cat>-->
<#--                        <option>${cat}</option>-->
<#--                    </#list>-->
<#--                </select>-->
            </div>
            <div class="form-group">
                <label for="text">Article text</label>
                <textarea class="form-control" id="text" rows="3" name="text"></textarea>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-success">Add article</button>
            </div>
        </form>
    </div>
</#macro>

<#macro title>New Article</#macro>

<@main/>