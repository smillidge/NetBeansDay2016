<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Page</title>
    </head>
    <body>
        <h1>Hello MVC World ${person.name}</h1>
        
        Hello ${person.name} the time pushed via SSE is 
        <div id="timediv"></div>
    </body>
</html>
<script type="text/javascript">


    if((typeof EventSource)==="undefined")
                {
                    $("#alertbox").css("display","");
                }

    var eventSource = new EventSource("sseexample");
    eventSource.onopen = function (e) {
        console.log("Waiting message..");
    };
    eventSource.onerror = function (e) {
        console.log("Error");
    };

    eventSource.addEventListener('time', function(e) {
        console.log(e);
        document.getElementById('timediv').innerHTML = e.data;
    },false);


</script>
