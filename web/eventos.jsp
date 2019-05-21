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
                        <label for="name">Nome do Evento</label>
                        <input name="nome" id="nome" class="pure-u-23-24" placeholder="Evento.." type="text" value="${eventos.nome}" 
                               required="yes" style="border-radius: 30px;">
                    </div>
                    
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="data">Data</label>
                        <input name="data" type="date"  id="data" class="pure-u-23-24" value="${eventos.data}" style="border-radius: 30px; height: 38px;">
                    </div>
                    
                    
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="desc">Descrição</label>
                        <select name="descricao" id="desc" class="pure-input-1-2" value="${eventos.descricao}" style="width: 95%; border-radius: 30px;">
                            
                                                        
                        </select>
                    </div>

                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="importancia">Importancia</label>
                        <select name="importancia" id="importancia" class="pure-input-1-2" value="${eventos.importancia}"  style="width: 95%; border-radius: 30px;">
                            
                            
                        </select>
                    </div>
                       
                        <div class="pure-u-1 pure-u-md-1-3">
                        <label for="local">Local</label>
                        <select name="local" id="local" class="pure-input-1-2" value="${eventos.local}"  style="width: 95%; border-radius: 30px;">
                          
                           
                        </select>
                    </div>
                    <div class="pure-u-1 pure-u-md-1-3">
                        <label for="responsavel">Funcionario</label>
                        <select name="responsavel" id="responsavel" class="pure-input-1-2" value="${eventos.responsavel}" style="width: 95%; border-radius: 30px;">
                          
                           
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

