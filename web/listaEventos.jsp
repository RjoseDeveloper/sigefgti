<%-- 
    Document   : Area de Estudantes FGTI
    Created on : Sep 20, 2018, 12:26:03 AM
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

        <link rel="stylesheet" href="css/layouts/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="css/layouts/base.css">
        <link rel="stylesheet" href="css/layouts/grids.css">
        <link rel="stylesheet" href="css/layouts/grids-responsive.css">
        <link rel="stylesheet" href="css/layouts/buttons.css">
        <link rel="stylesheet" href="css/layouts/forms.css">
        <script type="text/javascript" src="javascript/pesquisa.js"></script>
        <script src="javascript/clock.js" type="text/javascript"></script>
        
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

        </script>

        <style> 
           /* Style the tab */
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
             .search-box{
                position: absolute;
                top: 45%;
                left: 72%;
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
                color: #003333;
                font-size: 16px;
                transition: 0.4s;
                line-height: 25px;
                width: 240px;
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
                border: none; 
                          
            }
            td{
                color: #332130;                   
            }            
            tr:hover {background-color: whitesmoke;}
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
            
             $().ready(function alerta () {
                setTimeout(function () {
                    $('#sucess').hide(); 
                }, 3000); 
            });
            
            
            //canvas code here
           
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
    </head>
    <body>
              
        <br><br>

        <div class="pure-g">
            <img src="img/sala.jpg" style="width: 100%; height: 190px;">
        </div>

        <div class="container">
            <div class="tab">&nbsp;</div>
             
                           
            <div id="ii" class="tabcontents">
                <center>
                    <div class="search-box">
                        <input class="search-txt" type="text" id="complete-field" type="search" placeholder="Pesquisar.." onkeyup="doCompletion()" placeholder="Digite para pesquisar">
                        <a class="search-btn" href="#">
                            <img src="img/common/Search_25px.png">
                        </a>
                    </div>
                    
                    <br> <br>  <br>
                    
                    
                    
                    <table id="customers" class="pure-table pure-table-horizontal"
                           style="border: none; width: 80%;">
                        <thead>
                            <tr>
                                <th>Evento</th>
                                <th>Descrição</th>
                                <th>Local-Realização</th>
                                <th>Data</th>
                                <th>Operacoes</th>
                            </tr>
                        </thead>
                        <tbody>
                          
                        </tbody>
                    </table>
                </center>
            </div>
         
    </body>
</html>

