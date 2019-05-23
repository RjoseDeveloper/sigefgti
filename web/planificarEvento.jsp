<%-- 
    Document   : direcao
    Created on : Sep 20, 2018, 12:14:57 AM
    Author     : Jaime Muaqueia
--%>




<%@page import="java.util.List"%>

<!doctype html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example that shows off a responsive product landing page.">
        <title>FGTI</title>

        <link rel="stylesheet" href="css/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="css/layouts/base.css">
        <link rel="stylesheet" href="css/layouts/grids.css">
        <link rel="stylesheet" href="css/layouts/grids-responsive.css">
        <link rel="stylesheet" href="css/layouts/buttons.css">
        <link rel="stylesheet" href="css/layouts/forms.css">
        <link rel="stylesheet" href="css/layouts/tables.css">
        <script type="text/javascript" src="javascript/pesquisa.js"></script>
        <script type="text/javascript" src="javascript/jquery-3.3.1.min.js"></script>
        <script src="javascript/Vector.js" type="text/javascript"></script>
        <script src="javascript/clock.js" type="text/javascript"></script>
        <style>
            .search-box{
                position: absolute;
                top: 44%;
                left: 78%;
                transform: translate(-50%, -50%);
                background: #ccc;
                height: 40px;
                border-radius: 40px;
                padding: 10px;
            }

            .search-btn{
                color:#e84118;
                float: right;
                width: 40px;
                height: 20px;
                border-radius: 50%;
                background: #ccc;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .search-txt{
                border: none;
                background: none;
                outline: none;
                float: left;
                padding: 0;
                color: black;
                font-size: 16px;
                transition: 0.4s;
                line-height: 25px;
                width:240px
            }
            .tab {
                overflow: hidden;
                border: 0px solid #ccc;
                background-color: #f1f1f1;
                padding: 1px 1px 1px 1px;
                color: white;
            }

            /* Style the buttons inside the tab */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 4px 8px;
                transition: 0.3s;
                font-size: 17px;
                border-radius: 3px;
            }



            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #19306d;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color: #ccc;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }

            .content {
                text-align: justify;
                border: 1px solid #999;
                margin: 8px;
                padding: 14px;
                border-radius: 5px;
                color: #19306d;
            }
        </style>

        <script type="text/javascript" >
            function openCity(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }

            $().ready(function alerta() {
                setTimeout(function () {
                    $('#sucess').hide();
                }, 3000);
            });


        </script>

        <link rel="icon" type="icon/png" href="img/ucm.png" />
        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="css/layouts/grids-responsive-min.css">
        <!--<![endif]-->


        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/marketing-old-ie.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="css/layouts/marketing.css">
        <!--<![endif]-->

        <style>
            /* Style the tab */
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #ccccc;
                color: white;
            }

            /* Style the buttons that are used to open the tab content */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 8px 12px;
                transition: 0.3s;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ccc;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color:#19306d ;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;

            }

            .content {
                text-align: justify;
                border: 1px solid #999;
                margin: 10px;
                padding: 5px;
                border-radius: 5px;
                color: #19306d;
            }

            table{
                border-collapse: collapse;
            }

            th {
                background-color: whitesmoke;
                color: #19306d;
                height: 50px;
                padding: 15px;
                text-align: left;              
                border-collapse: collapse;
            }
            tr:hover {background-color: #ccffff;}
            td{
                color:#333333; 
            }


        </style>

    </head>
    <br><br>
</head>
<body>

    <div class="pure-g">

        <img src="img/direcao.jpg" style="width: 100%; height: 190px;"> 

        <hr style="height: 20px;">
    </div>

    <div class="container">
        <div class="tab">

            <button class="tablinks" onclick="openCity(event, 'user')">UTILIZADORES</button>
            <button class="tablinks" onclick="openCity(event, 'event')">EVENTO</button>
            <button class="tablinks" onclick="openCity(event, 'activity')">ACTIVIDADE</button>
            <button class="tablinks" onclick="openCity(event, 'department')">DEPARTAMENTO</button>
            <button class="tablinks" onclick="openCity(event, 'participant')">CHATS PARTICIPANTE</button>
            <button class="tablinks" onclick="openCity(event, 'logs')">HISTORICO DE ACESSO</button><!--
            -->         <button class="tablinks" onclick="openCity(event, 'roles')">PREVILEGIOS</button>

        </div>
        <center>
            <div id="sucess"><h4 style="color:green;">${sms}</h4></div>
        </center>

        <div id="user" class="tabcontent">
            /// user code here 
        </div>

        <div id="event" class="tabcontent">
            /// event code here
        </div>

        <div id="activity" class="tabcontent">
            <center>
                /// activity code here
            </center>
        </div>

        <div id="department" class="tabcontent">
            <center>
                /// department here 
            </center>
        </div>

        <div id="participant" class="tabcontent">
            <center>
                /// participant code here

            </center>
        </div>

        <div id="roles" class="tabcontent">
            <center>
                   <form class="pure-form pure-form-stacked" action="ProcessaRole" method="post" style="width: 65%;">
            <fieldset>
                <div class="pure-g">
                    
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="desc">Descrição do Role</label>
                        <input type="text" name="descricao" id="desc" class="pure-input-1-2" value="${eventos.descricao}" style="width: 95%; border-radius: 30px;"/>
                                                     
           
                    </div>

                </div>

                <hr width="98%">
                <button type="submit" class="pure-button pure-button-primary btn1" style="width: 23%; border-radius: 30px;" title="Aceitar" >Criar</button>
                <button type="reset" class="pure-button pure-button-primary btn2" style="background-color: red; width: 23%; border-radius: 30px;" title="Limpar Dados" class="pure-button ">Limpar</button>
            </fieldset>
        </form>

            </center>
        </div>

        <div id="logs" class="tabcontent">
            <center>
                /// log code here 
            </center>
        </div>

    </div>
</body>
</html>

