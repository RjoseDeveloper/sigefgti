<!doctype html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example that shows off a responsive product landing page.">
        <title>FGTI</title>

        <link rel="stylesheet" href="../css/layouts/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="icon" type="icon/png" href="../img/ucm.png" />
        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="../css/layouts/grids-responsive-min.css">
        <!--<![endif]-->

        <!--[if lte IE 8]>
            <link rel="stylesheet" href="css/layouts/marketing-old-ie.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="../css/layouts/marketing.css">
        <!--<![endif]-->
        <script>
            
             function sair() {
                if (confirm("Tem Certeza Que Pretende Sair?")) {
                    
                    window.location = "index.jsp";
                    return true;
                    
                } else {
                    return false;
                }
            }
        </script>
        
    </head>
    <body>
        <div class="header" style="top: 0; position: fixed; width: 100%">
            <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
                <a class="pure-menu-heading" href="">ucm - fgti</a>

                <ul class="pure-menu-list">
                    <li class="pure-menu-item "><a href="main.jsp" class="pure-menu-link" target="main" title="Página Inicial" style="border-radius: 30px;" >HOME</a></li>
                    <li class="pure-menu-item "><a href="listaEventos.jsp" class="pure-menu-link" target="main" title="Consultar" style="border-radius: 30px;" >CONSULTAS</a></li>
                     <li class="pure-menu-item "><a href="calendario.jsp" class="pure-menu-link" target="main" title="Eventos" style="border-radius: 30px;" >CALENDARIO</a></li>
    
                    <li class="pure-menu-item"><a  class="pure-menu-link" style="border-radius: 30px;" href="sair.jsp" onclick="return sair()" target="index">SAIR</a>
     
                </ul>
            </div>
        </div>
    </div>
</body>
</html>

