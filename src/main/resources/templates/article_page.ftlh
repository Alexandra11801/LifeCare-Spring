<#include "header.ftlh"/>

<#macro content>
    <div class="container" xmlns="http://www.w3.org/1999/html">

        <h1 class="h1 mt-3 mb-3">${article.getTitle()}</h1>
        <div class="description">
            ${article.getText()}
        </div>
        <#if authorizated == true>
            <div class="mt-3 mb-3">
                <a href="/suggest-revision?title=${article.getTitle()}"><@spring.message 'article.page.suggest'/></a>
            </div>
        </#if>
        <div class="row">
            <div class="col">
                <a href="/profile/${author.getId()}">${author.getName()} ${author.getSurname()}</a>
            </div>
            <#if authorizated == true>
                <div class="col">
                    <div class="like-dislike text-right">
                        <button class="btn-sm btn-success" id="likes" onclick="like()">+ ${article.getLikes()}</button>
                        <button class="btn-sm btn-danger" id="dislikes" onclick="dislike()">- ${article.getDislikes()}</button>
                    </div>
                </div>
            </#if>
        </div>

        <div class="comment">
            <form class="pt-5" method="post" accept-charset="utf-8">

                <div class="form-group">
                    <textarea class="form-control" id="text" name="text" rows="3" name="text">
                    </textarea>
                </div>

                <input type="text" name="receiver" id="receiver-id-field" style="display: none" value="">
                <input type="text" name="articleId" id="articleId" style="display: none" value="${article.getId()}">

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <div class="form-group">
                    <button type="submit" class="btn btn-success"><@spring.message 'article.page.comment'/></button>
                </div>
            </form>

            <div class="comments">
                <h3 class="title-comments"><@spring.message 'article.page.comments'/></h3>
                <#list comments as comment>
                    <div class="media-body">
                        <div class="media-heading">
                            <div class="author">${comment.getSenderName()}</div>
                            <div class="metadata">
                                <span class="date">${comment.getDate()}</span>
                            </div>
                        </div>
                        <div class="media-text text-justify">${comment.getText()}</div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

    <script type="application/javascript">
        function like(){
            $.ajax({
                url: "/article/like",
                data: {
                    "article": "${article.getTitle()}",
                },
                dataType: "json",
                scriptCharset: "utf-8",
                success: function (msg) {
                    $("#likes").text("+ " + msg.toString());
                }
            })
        }

        function dislike(){
            $.ajax({
                url: "/article/dislike",
                data: {
                    "article": "${article.getTitle()}",
                },
                dataType: "json",
                scriptCharset: "utf-8",
                success: function (msg) {
                    $("#dislikes").text("- " + msg.toString());
                }
            })
        }

        function reply(comm, name){
            console.log(comm.querySelector('#comm-id').innerText)
            document.getElementById("receiver-id-field").value = comm.querySelector('#comm-id').innerText;
            console.log(document.getElementById("comm-text").innerText);
            document.getElementById("comm-text").insertAdjacentText('afterBegin', name + ', ');
            console.log(document.getElementById("comm-text").innerText);
        }
    </script>
</#macro>

<#macro title>${article.getTitle()}</#macro>

<@main/>