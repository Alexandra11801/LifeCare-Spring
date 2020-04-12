<#include "header.ftlh"/>

<#macro content>
    <div class="container">
        <form class="pt-5 pb-4" method="post">
            <div class="form-group">
                <label for="title"><@spring.message 'add.page.articleTitle'/></label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Title" required>
            </div>
            <div class="form-group">
                <label for="category"><@spring.message 'add.page.category' /></label>
                <select class="form-control" id="category" name="category">
                    <#list categories as cat>
                        <option>${cat.getCategory()}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="text"><@spring.message 'add.page.text'/></label>
                <textarea class="form-control" id="text" rows="3" name="text" required></textarea>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-success"><@spring.message 'add.page.add'/></button>
            </div>
        </form>
    </div>
</#macro>

<#macro title><@spring.message 'add.page.title'/></#macro>

<@main/>