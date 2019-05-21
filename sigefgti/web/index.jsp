<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A layout example that shows off a responsive product landing page.">
        <title>FGTI</title>

        <style>
            a{color: #cccccc}
        </style>


        <link rel="stylesheet" href="css/layouts/buttons.css">
        <link rel="stylesheet" href="css/layouts/pure-min.css">
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
    <body bgcolor="#19306d">

        <br><br>
    <center>
        <br><br><br><br>
        <h3 style="color: whitesmoke;">FACULDADE DE GESTÃO DE TURIMSO E INFÓRMATICA</h3><hr width="33%">
        <br>
    </center> 

    <center>

        <form class="pure-form" action="" method="">
            <input type="email" placeholder="Username" id="email" style="width: 350px; border-radius: 30px;"><br/>
            <input type="password" placeholder="Password" id="password" style="width: 350px; border-radius: 30px;" >
            <br/>
            <button type="submit" onclick="return validate()" class="pure-button pure-button-primary" style="width: 170px; border-radius: 20px;">Entrar</button>
            <button type="reset" class="pure-button pure-button-primary" style="width: 170px; border-radius: 20px; background-color: #ff3333;">Cancelar</button>
        </form>
        <br>

        <hr width="33%">

        <a href="home_admin.jsp">ADMIN</a> |
        <a href="home_visitante.jsp">VISITANTE</a>
    </center>


    <script>
        $().ready(function () {
            setTimeout(function () {
                $('#sucess').hide();
            }, 4000);
        });
    </script>
</body>
</html>

