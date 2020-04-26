<#include "header.ftlh"/>

<#macro content>
    <div class="container" >
        <input class="form-control rounded-0 w-100" id="message" name="message" type="text">
        <button class="btn btn-info rounded-0 w-25" onclick="sendMessage( $('#message').val())">
            <@spring.message 'chat.page.send'/>
        </button>

<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    </div>

    <div class="container">
        <ul id="messages">
            <#list messages as message>
                <div class="media-body">
                    <div class="media-heading">
                        <div class="author"><strong>${message.getSenderName()}</strong></div>
                    </div>
                    <div class="media-text text-justify">${message.getText()}</div>
                </div>
            </#list>
        </ul>
    </div>

    <script type="application/javascript">
        var webSocket;

        function connect() {
            webSocket = new WebSocket('ws://localhost:1010/sockets');
            document.cookie = 'X-Authorization=' + '123456' + ';path=/';
            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messages').prepend('<div class="media-body">' +
                                      '<div class="media-heading">' +
                                      '<div class="author"><strong>' + json['senderName'] + '</strong></div>' +
                                      '</div>' +
                                      '<div class="media-text text-justify">'+ json['text'] + '</div>' +
                                      '</div>');
            }
        }

        function sendMessage(text) {
            $('#message').val("");
            let message = {
                "senderName": '${current_user.getName()} ${current_user.getSurname()}',
                "text": text,
            };
            webSocket.send(JSON.stringify(message));
        }

    </script>

</#macro>

<#macro title><@spring.message 'chat.page.title'/></#macro>

<@main/>