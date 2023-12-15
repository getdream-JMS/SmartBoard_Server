var ws;

function connect() {
    var username = document.getElementById("username").value;
    
    var host = document.location.host;
    var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" +host);

    ws.onmessage = function(event) {
    var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += event.data+ "\n";
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify(JSON.parse(content));
	console.log(content)

    ws.send(content);
}