<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Calendario</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
 <script>
     
window.onload = function(){
canvas = document.getElementById("calendario");
context = canvas.getContext("2d");
context.font = "40pt sans-serif";
context.fillStyle = "black";    

// alinhar o texto no centro horizontal
context.textAlign = "center";
// alinhar o texto no centro vertical
context.textBaseline = "middle";


function draw3dText(context, text, x, y, textDepth){
var n;
// draw bottom layers
for (n = 0; n < textDepth; n++) {
context.fillText(text, x - n, y - n);
}
// draw top layer with shadow casting over
// bottom layers
context.fillStyle = "#19306d";
context.shadowColor = "white";
context.shadowBlur = 8;
context.shadowOffsetX = textDepth + 4;
context.shadowOffsetY = textDepth + 2;
context.fillText(text, x - n, y - n);
}
</script>

    </head>
    <body>
        
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <br/><br/>
    <center>
        <canvas id="calendario" style=" 
                padding: 10px 5px 10px 5px;
                width: 650px;
                height: 118px;
                border-radius: 5px;
                ">
                    
        </canvas>
    </center>
                
        <div class="calendar" id="calendar-app">
            <div class="calendar--day-view" id="day-view">
                <span class="day-view-exit" id="day-view-exit">&times;</span>
                <span class="day-view-date" id="day-view-date">MAIO 29 DE 2016</span>
                <div class="day-view-content">

                </div>
            </div>
            <div class="calendar--view" id="calendar-view">
                <div class="cview__month">
                    <span class="cview__month-last" id="calendar-month-last">Abril</span>
                    <span class="cview__month-current" id="calendar-month">Maio</span>
                    <span class="cview__month-next" id="calendar-month-next">Junho</span>
                </div>
                <div class="cview__header">Dom</div>
                <div class="cview__header">Seg</div>
                <div class="cview__header">Ter</div>
                <div class="cview__header">Qua</div>
                <div class="cview__header">Qui</div>
                <div class="cview__header">Sex</div>
                <div class="cview__header">Sab</div>
                <div class="calendar--view" id="dates">
                </div>
            </div>
            <div class="footer">
                <span><span id="footer-date" class="footer__link">Hoje</span></span>    
            </div>
        </div>
        <script src="javascript/index.js" type="text/javascript"></script>

    </body>
</html>

