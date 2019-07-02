<%-- 
    Document   : eventos
    Created on : Sep 20, 2018, 12:27:42 AM
    Author     : Jaime Muaqueia
--%>

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


        <link rel="icon" type="icon/png" href="img/ucm.png" />
        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="css/layouts/grids-responsive-min.css">
        <!--<![endif]-->

        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">

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
        <img src="img/grid.jpg" style="width: 100%; height: 190px; border-bottom: 10px #0078e7;">        
    </div>
    
    <center>
           
        <form class="pure-form pure-form-stacked" action="CriarEvento?act=1" method="post" style="width: 65%;">
            <fieldset>
                <div class="pure-g">
                    <div class="pure-u-1 pure-u-md-1-3">
                      
                        <input type="hidden" name="id" value="">
                        <label for="details">details</label>
                        <input name="details" id="details" class="pure-u-23-24" placeholder="details.." type="text" value="" 
                               required="yes" style="border-radius: 30px;">
                    </div>
                    
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="dateadded">dateadded</label>
                        <input name="dateadded" type="date"  id="dateadded" class="pure-u-23-24" value="" style="border-radius: 30px; height: 38px;">
                    </div>
                    
                      <div class="pure-u-1 pure-u-md-1-3">
                        <label for="starttime">starttime</label>
                        <input name="starttime" type="time"  id="dateadded" class="pure-u-23-24" value="" style="border-radius: 30px; height: 38px;">
                    </div>
                    
                     <div class="pure-u-1 pure-u-md-1-3">
                        <label for="endtime">endtime</label>
                        <input name="endtime" type="time"  id="dateadded" class="pure-u-23-24" value="" style="border-radius: 30px; height: 38px;">
                    </div>                    
                   <div class="pure-u-1 pure-u-md-1-3">
                        <label for="relevance">relevance</label>
                        <select name="relevance" id="relevance" class="pure-input-1-2" value=""  style="width: 95%; border-radius: 30px;">                                                        
                        </select>
                    </div>                     
                        <div class="pure-u-1 pure-u-md-1-3">
                        <label for="location">location</label>
                        <select name="location" id="location" class="pure-input-1-2" value="${eventos.local}"  style="width: 95%; border-radius: 30px;">
                                                
                        </select>
                    </div>
                </div>

                <hr width="98%">
                <button type="submit" class="pure-button pure-button-primary" style="width: 23%; float: right; border-radius: 30px;"  onclick="alerta()" title="Aceitar" >Criar</button>
                <button type="reset" class="pure-button pure-button-primary" style="background-color: red; width: 23%; float: left; border-radius: 30px;" title="Limpar Dados" class="pure-button ">Limpar</button>
            </fieldset>
        </form>
    </center>
</body>
</html>

